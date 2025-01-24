package yaml;

import java.io.*;
import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

// Разбор и сохранение в текстовый файл на Yaml
public class Yaml<T> {
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
    // Имя файла для хранения информации
    private String fileName;

    public Yaml(String fileName) {
        this.fileName = fileName;
    }

    public void save(T obj) throws FileNotFoundException, IllegalAccessException {
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        try (PrintWriter w = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), UTF_8))) {
            Class<?> cls = obj.getClass();
            if (cls.isPrimitive() || simpleWrite(obj))
                w.println(obj.toString());
            else if (obj instanceof Date) {
                w.printf("%s%n", dateTimeFormat.format(obj));
            } else if (cls.isArray()) {
                int len = Array.getLength(obj);
                for (int i = 0; i < len; i++) {
                    w.printf("- %s%n", formatValue(Array.get(obj, i)));
                }
            } else {
                for (Field f : cls.getDeclaredFields()) {
                    f.setAccessible(true);
                    w.printf("%s: %s%n", f.getName(), formatValue(f.get(obj)));
                }
            }
        }
    }

    private boolean simpleWrite(T obj) {
        return (obj instanceof Byte) || (obj instanceof Short) ||
                (obj instanceof Integer) || (obj instanceof Long) ||
                (obj instanceof Float) || (obj instanceof Double) ||
                (obj instanceof Boolean) || (obj instanceof Character) ||
                (obj instanceof String);
    }

    private String formatValue(Object value) throws IllegalAccessException {
        if (value instanceof Date)
            return dateTimeFormat.format(value);
        return value.toString();
    }

    @SuppressWarnings("unchecked")
    public T load(Class<T> cls) throws Exception {
        try (Scanner sc = new Scanner(new File(fileName), "UTF-8")) {
            sc.useLocale(Locale.ENGLISH);
            if (cls.equals(Byte.class))
                return (T) new Byte(sc.nextByte());
            if (cls.equals(Short.class))
                return (T) new Short(sc.nextShort());
            if (cls.equals(Integer.class))
                return (T) new Integer(sc.nextInt());
            if (cls.equals(Long.class))
                return (T) new Long(sc.nextLong());
            if (cls.equals(Float.class))
                return (T) new Float(sc.nextFloat());
            if (cls.equals(Double.class))
                return (T) new Double(sc.nextDouble());
            if (cls.equals(Character.class))
                return (T) new Character(sc.next().charAt(0));
            if (cls.equals(Boolean.class))
                return (T) Boolean.valueOf(sc.nextBoolean());
            if (cls.equals(String.class))
                return (T) sc.nextLine().trim();
            if (cls.equals(Date.class))
                return (T) dateTimeFormat.parse(sc.nextLine());
            if (cls.isArray()) {
                ArrayList<String> list = new ArrayList<>();
                while (sc.hasNext()) {
                    String s = sc.nextLine();
                    if (s.startsWith("-")) {
                        s = s.substring(1).trim();
                        list.add(s);
                    }
                }
                Class<?> type = cls.getComponentType();
                T obj = (T) Array.newInstance(type, list.size());
                for (int i = 0; i < list.size(); i++) {
                    Array.set(obj, i, parseValue(type, list.get(i)));
                }
                return obj;
            }
            // Создаём экзепляр класса
            T obj = cls.newInstance();
            // Считываем свойства и их значения
            while (sc.hasNext()) {
                String s = sc.nextLine().trim();
                int pos = s.indexOf(":");
                String name = s.substring(0, pos);
                String value = s.substring(pos + 1).trim();

                Field field = cls.getDeclaredField(name);
                field.setAccessible(true);
                Class<?> t = field.getType();
                // Не список ли это?
                if (t.equals(List.class)) {
                    parseList(obj, field, value);
                } else {
                    field.set(obj, parseFieldValue(field, value));
                }
            }
            return obj;
        }
    }

    private void parseList(T obj, Field field, String value) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, ParseException {
        String ss = value.trim();
        // Получаем тип элементов в списке
        Type type = field.getGenericType();
        Type genericType = Object.class;
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            genericType = pt.getActualTypeArguments()[0];
        }
        // Список вида: [1, 2, 3]
        Object list = field.get(obj);
        Method add = List.class.getDeclaredMethod("add", Object.class);
        if (ss.startsWith("[") && ss.endsWith("]")) {
            // "Откусываем" скобки '[]' с обеих сторон
            ss = ss.substring(1, ss.length() - 1);
            // Разделяем значения по запятым
            String[] values = ss.split(",");
            for (String v : values) {
                add.invoke(list, parseValue((Class) genericType, v.trim()));
            }
        }
    }

    private Object parseFieldValue(Field field, String value) throws ParseException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> t = field.getType();
        // Обработка простых значений (примитивных типов, строк)
        return parseValue(t, value);
    }

    private Object parseValue(Class<?> t, String value) throws ParseException {
        // целые типы
        if (t.equals(Byte.class) || t.equals(Byte.TYPE))
            return Byte.valueOf(value);
        if (t.equals(Short.class) || t.equals(Short.TYPE))
            return Short.valueOf(value);
        if (t.equals(Integer.class) || t.equals(Integer.TYPE))
            return Integer.valueOf(value);
        if (t.equals(Long.class) || t.equals(Long.TYPE))
            return Long.valueOf(value);
        // floating point types
        if (t.equals(Float.class) || t.equals(Float.TYPE))
            return Float.valueOf(value);
        if (t.equals(Double.class) || t.equals(Double.TYPE))
            return Double.valueOf(value);
        // символ и логический тип boolean
        if (t.equals(Character.class) || t.equals(Character.TYPE))
            return value.trim().charAt(0);
        if (t.equals(Boolean.class) || t.equals(Boolean.TYPE))
            return Boolean.valueOf(value);
        // Если это дата
        if (t.equals(Date.class))
            return dateTimeFormat.parse(value);
        // Если ни один тип не подошёл
        return value;
    }
}
