package interview;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Random;

// Сортировка массивов
public class SortArraysTest extends Assert {

    public static void main(String[] args) {
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
        }
        // Сортируем каждый массив в отдельности
        for (int i = 0; i < n; i++) {
            Arrays.sort(a2[i]);
        }
        //  Сливаем с помощью кучи

    }

    static class Array {
    }
}
