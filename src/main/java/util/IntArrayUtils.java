package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Утилиты для работы с массивами целых чисел и файлами
 */
public class IntArrayUtils {
    private static final boolean DEBUG = false;

    public static int[] readFile(File file) throws FileNotFoundException {
        ArrayList<Integer> list = new ArrayList<>();
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextInt()) {
                list.add(scan.nextInt());
            }
        }
        return intListToArray(list);
    }

    /**
     * Сохраняем массив целых чисел в файл
     *
     * @param a    массив
     * @param file файл для сохранения
     * @throws FileNotFoundException        не найден путь к файлу или нет прав
     * @throws UnsupportedEncodingException если не поддерживается кодировка UTF-8
     */
    public static void saveArray(int[] a, File file) throws UnsupportedEncodingException, FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(file, "UTF-8")) {
            for (int x : a) {
                pw.println(x);
            }
        }
    }

    private static int[] intListToArray(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i);
        }
        return ret;
    }

    public static int[] concat(int[] a, int[] b) {
        int aLen = a.length;
        int bLen = b.length;
        int[] c = new int[aLen + bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    /**
     * @param srcFile Исходный файл с числами
     * @param PART    размер части которую можно загрузить в память
     * @return Имя отсортированного файла
     * @throws FileNotFoundException        Входной файл не найден или пути для создания файлов
     * @throws UnsupportedEncodingException если не поддерживается кодировка UTF-8
     */
    public static File mergeSortOnFiles(File srcFile, int PART) throws UnsupportedEncodingException, FileNotFoundException {
        // Загружаем огромный файл по кусочкам,
        // сортируем каждый кусочек
        Deque<FilePart> parts = new LinkedList<>();
        // Открываем большой файл
        try (Scanner scan = new Scanner(srcFile)) {
            while (scan.hasNextInt()) {
                // Считываем очередную часть
                int[] array = readPart(scan, PART);
                // Сортируем часть
                Arrays.sort(array);
                // Сохраняем в файл
                FilePart part = new FilePart();
                saveArray(array, part.getFile());
                parts.addLast(part);
            }
        }
        // Потом сливаем вместе
        while (parts.size() >= 2) {
            FilePart p1 = parts.pollFirst();
            FilePart p2 = parts.pollLast();
            FilePart merged = new FilePart();
            mergeFiles(p1.getFile(), p2.getFile(), merged.getFile());
            parts.addLast(merged);
        }

        // Последняя часть - это итоговый файл
        if (parts.size() != 1)
            throw new AssertionError("Сейчас в Deque parts должен был остаться один файл");
        FilePart result = parts.poll();
        if (parts.size() != 0)
            throw new AssertionError("Мы только что забрали последнюю часть из дека");
        return result.getFile();
    }

    /**
     * Слияние файлов с целыми числами
     *
     * @param file1  Первый файл
     * @param file2  Второй файл
     * @param result Результат
     */
    public static void mergeFiles(File file1, File file2, File result) throws FileNotFoundException, UnsupportedEncodingException {
        if (DEBUG)
            System.out.println("merge: " + file1 + " + " + file2 + " => " + result);
        try (Scanner s1 = new Scanner(file1);
             Scanner s2 = new Scanner(file2);
             PrintWriter pw = new PrintWriter(result, "UTF-8")) {
            int v1 = -1, v2 = -1;
            boolean hasNext1 = s1.hasNextInt();
            boolean hasNext2 = s2.hasNextInt();
            if (hasNext1)
                v1 = s1.nextInt();
            if (hasNext2)
                v2 = s2.nextInt();
            while (hasNext1 || hasNext2) {
                boolean use1 = !hasNext2 || hasNext1 && v1 < v2;
                if (use1) {
                    pw.println(v1);
                    hasNext1 = s1.hasNextInt();
                    if (hasNext1) v1 = s1.nextInt();
                } else {
                    pw.println(v2);
                    hasNext2 = s2.hasNextInt();
                    if (hasNext2) v2 = s2.nextInt();
                }
            }
        }
        // Удаляем файлы за собой
        if (!file1.delete()) System.out.println("Can't delete: " + file1);
        if (!file2.delete()) System.out.println("Can't delete: " + file2);
    }

    /**
     * Читаем partSize чисел
     *
     * @param scan     Сканер для считывания частей
     * @param partSize Размер части (количество чисел)
     * @return Считанный массив чисел
     */
    private static int[] readPart(Scanner scan, int partSize) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < partSize; i++) {
            // Если числа кончились => прерываем эту часть
            if (!scan.hasNextInt())
                break;
            list.add(scan.nextInt());
        }
        // Преобразуем в массив
        return intListToArray(list);
    }

    private static class FilePart {
        static int count = 0;
        private final int id;

        FilePart() {
            id = ++count;
        }

        File getFile() {
            return new File(String.format("work/%d.txt", id));
        }
    }
}
