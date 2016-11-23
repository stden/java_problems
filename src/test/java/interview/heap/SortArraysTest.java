package interview.heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

// Сортировка массивов
public class SortArraysTest extends Assert {

    @Test
    public void testResult() {
        Random random = new Random();
        int n = 3; // Кол-во массивов
        int m = 4; // Размер каждого массива

        // 2D-массив
        int[][] a2 = new int[n][m];
        // Заполняем случайными числами
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a2[i][j] = random.nextInt(20);
            }
            // Сортируем каждый массив в отдельности
            Arrays.sort(a2[i]);
        }
        assertSorted(merge(a2));
    }

    // Убеждаемся что массив отсортирован
    private void assertSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            assertTrue(a[i - 1] <= a[i]);
        }
    }

    private int[] merge(int[][] a2) {
        int n = a2.length; // Строк
        int m = a2[0].length; // Столбцов
        // Проверяем что все строки равны по длине
        for (int[] a1 : a2) {
            assertEquals(m, a1.length);
        }
        //  Сливаем с помощью кучи
        PriorityQueue<Array> heap = new PriorityQueue<>();
        for (int[] a1 : a2) {
            heap.add(new Array(a1));
        }
        int[] result = new int[n * m];
        for (int pos = 0; pos < result.length; pos++) { // Позиция в выходном массиве
            Array a = heap.poll(); // Получаем минимальный элемент
            result[pos] = a.current();
            a.index++;
            if (a.index < a.data.length) // Если ещё не кончился массив
                heap.offer(a); // Добавляем обратно
        }
        return result;
    }

    // Один 1-мерный массив
    private static class Array implements Comparable<Array> {
        int index;
        int[] data;

        Array(int[] data) {
            this.data = data;
            index = 0;
        }

        @Override
        public int compareTo(Array o) {
            return Integer.compare(current(), o.current());
        }

        int current() {
            return data[index];
        }
    }
}
