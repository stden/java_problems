package interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class Sums3Test extends Assert {

    private static boolean DEBUG = false;
    private Random gen = new Random();

    public static void main(String[] args) {
        // Даны три массива
        int[] a = {1};
        int[] b = {2, 2};
        int[] c = {3, 3, 3};

        // Сколькими способами можно получить заданную сумму sum
        int x = 6;

        System.out.println("calc(a,b,c,x) = " + calc(a, b, c, x));
    }

    /**
     * Заданная сумма x из 3-х массивов: a,b,c.
     * Первое слагаемое из a, второе - из b, третье из c
     *
     * @param a первый массив
     * @param b второй массив
     * @param c третий массив
     * @param x заданная сумма
     * @return Количество вариантов получить x
     */
    private static int calc(int[] a, int[] b, int[] c, int x) {
        int count = 0; // Количество способов
        // Сортируем массивы
        Arrays.sort(b);
        Arrays.sort(c);
        if (DEBUG) {
            System.out.println(Arrays.toString(a));
            System.out.println(Arrays.toString(b));
            System.out.println(Arrays.toString(c));
            System.out.println("x = " + x);
        }
        for (int ax : a) {
            int sum = x - ax; // Оставшаяся сумма
            int j = 0; // Указатель в массиве b
            int k = c.length - 1; // Указатель в массиве c

            while (j < b.length && k >= 0) { // Пока массивы не кончились
                int s = b[j] + c[k]; // Текущая сумма b[j] и c[k]
                if (sum == s) {
                    int bb = b[j], cc = c[k];
                    int bn = 0, cn = 0;
                    while (j < b.length && b[j] == bb) {
                        j++;
                        bn++;
                    }
                    while (k >= 0 && c[k] == cc) {
                        k--;
                        cn++;
                    }
                    count += bn * cn;
                } else if (s > sum) {
                    k--;
                } else if (s < sum) {
                    j++;
                }
            }
        }
        if (DEBUG) System.out.println("count = " + count);
        return count;
    }

    @Test
    public void testSimple() {
        // Даны три массива
        int[] a = {1};
        int[] b = {2, 2};
        int[] c = {3, 3, 3};

        // Сколькими способами можно получить заданную сумму
        assertEquals("6 можно получить взяв любую из 2-х 2-ек и любую из 3-х 3-ек",
                2 * 3, calc(a, b, c, 6));
        assertEquals("Сравниваем прямое решение с нашим",
                bruteForce(a, b, c, 6), calc(a, b, c, 6));
    }

    @Test
    public void testRandom() {
        for (int test = 0; test < 30; test++) {
            // 3 случайных массива
            int[] a = genArray(3 + gen.nextInt(8));
            int[] b = genArray(3 + gen.nextInt(8));
            int[] c = genArray(3 + gen.nextInt(8));
            int x = a[gen.nextInt(a.length)] + b[gen.nextInt(b.length)] + c[gen.nextInt(c.length)];

            // a.clone() чтобы не менять исходные массивы
            assertEquals(bruteForce(a, b, c, x), calc(a.clone(), b.clone(), c.clone(), x));
        }
    }

    /**
     * Лобовое решение для проверки корректности остальных решений
     */
    private int bruteForce(int[] a, int[] b, int[] c, int x) {
        int count = 0;
        for (int ax : a)
            for (int bx : b)
                for (int cx : c)
                    if (ax + bx + cx == x) {
                        count++;
                    }
        return count;
    }

    private int[] genArray(int size) {
        int[] a = new int[size];
        for (int i = 0; i < a.length; i++) {
            a[i] = gen.nextInt(20);
        }
        return a;
    }
}
