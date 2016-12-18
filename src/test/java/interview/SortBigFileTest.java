package interview;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;

import static util.IntArrayUtils.*;

/**
 * Алгоритм сортировки больших файлов (которые не умещаются в памяти)
 * Идея:
 * - Разделяем файл на много мелких которые умешаются в памяти,
 * загружаем кусочки в память, сортируем их по отдельности в памяти и сохраняем на диск
 * Сложность сортировки: O(n*log(n))
 * - Сливаем файлы с диска алгоритмом MargeSort
 */
public class SortBigFileTest extends Assert {
    private Random gen = new Random();

    private int[] genRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = gen.nextInt(); // Генерируем новое число
        }
        return array;
    }

    /**
     * Тестируем как работает слияние 2-х файлов
     */
    @Test
    public void testMerge() throws IOException {
        int[] a1 = genRandomArray(5 + gen.nextInt(20));
        int[] a2 = genRandomArray(5 + gen.nextInt(20));
        Arrays.sort(a1);
        Arrays.sort(a2);

        File file1 = new File("work/a1.txt");
        File file2 = new File("work/a2.txt");
        File result = new File("work/res.txt");

        saveArray(a1, file1);
        saveArray(a2, file2);
        mergeFiles(file1, file2, result);

        // Ожидаем массив 0 - результат слияния отсортированный результат слияния
        int[] expected = concat(a1, a2);
        Arrays.sort(expected);

        assertArrayEquals(expected, readFile(result));
    }

    @Test
    public void testSortAndCheck() throws FileNotFoundException, UnsupportedEncodingException {
        final int TOTAL_SIZE = 1000 + gen.nextInt(1000); // Суммарный размер массива
        final int PART = 100 + gen.nextInt(100); // Один маленький кусочек который мы будем сортировать в памяти
        assertTrue("Часть должна быть меньше целого", PART < TOTAL_SIZE);
        File bigFile = new File("work/bigFile.txt");
        // Большой массив для проверки правильности работы
        int[] bigArray = genRandomArray(TOTAL_SIZE);
        // Создание файла
        saveArray(bigArray, bigFile);
        // Сортируем на файлах
        File result = mergeSortOnFiles(bigFile, PART);
        // Считываем обратно огромный отсортированный файл для контроля
        int[] array = readFile(result);
        // Сортируем большой массив чтобы потом уже сверять с ним
        Arrays.sort(bigArray);
        // Сравниваем результат с bigArray
        assertArrayEquals(bigArray, array);
    }


}
