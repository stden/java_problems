package interview;

import org.junit.Assert;
import org.junit.Test;

public class Sums3Test extends Assert {

    public static void main(String[] args) {
        // Даны три массива
        int[] a = {1};
        int[] b = {2, 2};
        int[] c = {3, 3, 3};

        // Сколькими способами можно получить заданную сумму sum
        int x = 6;

        System.out.println("calc(a,b,c,x) = " + calc(a, b, c, x));

    }

    private static int calc(int[] a, int[] b, int[] c, int x) {
        int count = 0; // Количество способов
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

                } else if (s < sum) {

                }
            }
        }
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
    }
}
