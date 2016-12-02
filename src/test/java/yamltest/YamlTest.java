package yamltest;

import org.junit.Assert;
import org.junit.Test;
import yaml.Yaml;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class YamlTest extends Assert {
    Random gen = new Random();

    // Сохранение и загрузка простых типов
    @Test
    public void testString() throws Exception {
        // Строка
        String s = "Привет :) Это тестовая строка";
        Yaml<String> yaml = new Yaml<>("yaml/string.yml");
        yaml.save(s);
        assertEquals(s, yaml.load(String.class));
    }

    @Test
    public void testByte() throws Exception {
        byte b = (byte) gen.nextInt();
        Yaml<Byte> yaml = new Yaml<>("yaml/byte.yml");
        yaml.save(b);
        assertEquals(b, yaml.load(Byte.class).byteValue());
    }

    @Test
    public void testShort() throws Exception {
        short s = (short) gen.nextInt();
        Yaml<Short> yaml = new Yaml<>("yaml/short.yml");
        yaml.save(s);
        assertEquals(s, yaml.load(Short.class).shortValue());
    }

    @Test
    public void testInt() throws Exception {
        int i = gen.nextInt();
        Yaml<Integer> yaml = new Yaml<>("yaml/int.yml");
        yaml.save(i);
        assertEquals(i, yaml.load(Integer.class).intValue());
        // New value
        i = gen.nextInt();
        yaml.save(i);
        assertEquals(i, yaml.load(Integer.class).intValue());
    }

    @Test
    public void testLong() throws Exception {
        long s = gen.nextLong();
        Yaml<Long> yaml = new Yaml<>("yaml/long.yml");
        yaml.save(s);
        assertEquals(s, yaml.load(Long.class).longValue());
    }

    @Test
    public void testFloat() throws Exception {
        Yaml<Float> yaml = new Yaml<>("yaml/float.yml");
        float f = gen.nextFloat();
        yaml.save(f);
        assertEquals(f, yaml.load(Float.class), 1e-10f);
    }

    @Test
    public void testDouble() throws Exception {
        Yaml<Double> yaml = new Yaml<>("yaml/double.yml");
        double d = gen.nextDouble();
        yaml.save(d);
        assertEquals(d, yaml.load(Double.class), 1e-16);
    }

    @Test
    public void testChar() throws Exception {
        Yaml<Character> yaml = new Yaml<>("yaml/char.yml");
        char c = (char) gen.nextInt();
        yaml.save(c);
        assertEquals(c, yaml.load(Character.class).charValue());
    }

    @Test
    public void testBoolean() throws Exception {
        boolean b = gen.nextBoolean();
        Yaml<Boolean> yaml = new Yaml<>("yaml/boolean.yml");
        yaml.save(b);
        assertEquals(b, yaml.load(Boolean.class));
    }

    @Test
    public void testSimpleClass() throws Exception {
        // Создаём точку
        Point p = new Point();
        p.x = gen.nextInt();
        p.y = gen.nextInt();
        // Сохраняем её в файл
        Yaml<Point> yaml = new Yaml<>("yaml/point.yml");
        yaml.save(p);
        Point p2 = yaml.load(Point.class);
        assertEquals(p.x, p2.x);
        assertEquals(p.y, p2.y);
    }

    @Test
    public void testAllPrimitiveCases() throws Exception {
        // Создаём объект
        AllPrimitives e = new AllPrimitives();
        e.publicInt = gen.nextInt();
        e.protectedInt = gen.nextInt();
        e.setPrivateInt(gen.nextInt());
        e.aByte = (byte) gen.nextInt();
        e.aByte2 = (byte) gen.nextInt();
        e.aShort = (short) gen.nextInt();
        e.aInt = gen.nextInt();
        e.aLong = gen.nextLong();
        e.aFloat = gen.nextFloat();
        e.aDouble = gen.nextDouble();
        e.aBoolean = gen.nextBoolean();
        e.aChar = (char) (33 + gen.nextInt(100));
        e.str = "И ещё строчку проверим";
        e.date = new Date();
        // Сохраняем её в файл
        Yaml<AllPrimitives> yaml = new Yaml<>("yaml/primitives.yml");
        yaml.save(e);
        AllPrimitives a = yaml.load(AllPrimitives.class);
        assertEquals(e.publicInt, a.publicInt);
        assertEquals(e.protectedInt, a.protectedInt);
        assertEquals(e.getPrivateInt(), a.getPrivateInt());
        assertEquals(e.aByte, a.aByte);
        assertEquals(e.aByte2, a.aByte2);
        assertEquals(e.aShort, a.aShort);
        assertEquals(e.aInt, a.aInt);
        assertEquals(e.aLong, a.aLong);
        assertEquals(e.aFloat, a.aFloat, 1e-10);
        assertEquals(e.aDouble, a.aDouble, 1e-10);
        assertEquals(e.aBoolean, a.aBoolean);
        assertEquals(e.str, a.str);
        assertEquals(e.date, a.date);
    }

    @Test
    public void testDate() throws Exception {
        Date date = new Date();
        Yaml<Date> yaml = new Yaml<>("yaml/date.yml");
        yaml.save(date);
        Date date1 = yaml.load(Date.class);
        assertEquals(date, date1);
    }

    @Test
    public void testIntArray() throws Exception {
        int[] a = new int[8];
        for (int i = 0; i < a.length; i++) {
            a[i] = gen.nextInt();
        }
        Yaml<int[]> yaml = new Yaml<>("yaml/array.yml");
        yaml.save(a);
        assertArrayEquals(a, yaml.load(int[].class));
    }

    @Test
    public void testDoubleArray() throws Exception {
        double[] a = new double[8];
        for (int i = 0; i < a.length; i++) {
            a[i] = gen.nextDouble();
        }
        Yaml<double[]> yaml = new Yaml<>("yaml/array_double.yml");
        yaml.save(a);
        assertArrayEquals(a, yaml.load(double[].class), 1e-10);
    }

    @Test
    public void testIntArrayList() throws Exception {
        ComplexObject obj = new ComplexObject();
        for (int i = 0; i < 10; i++) {
            obj.ints.add(gen.nextInt());
            obj.strings.add("str" + gen.nextInt(1000));
        }
        Yaml<ComplexObject> yaml = new Yaml<>("yaml/array_list.yml");
        yaml.save(obj);
        ComplexObject res = yaml.load(ComplexObject.class);
        assertEquals(obj.ints.size(), res.ints.size());
        assertEquals(obj.ints, res.ints);
        assertEquals(obj.strings.size(), res.strings.size());
        assertEquals(obj.strings, res.strings);
    }

    public static class Point {
        int x;
        int y;
    }

    public static class ComplexObject {
        List<Integer> ints = new ArrayList<>();
        List<String> strings = new ArrayList<>();
    }
}
