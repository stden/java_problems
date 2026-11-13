import java.util.Scanner;

public class MaxMul {

    /**
     * G. Наибольшее произведение
     * Дано N целых чисел. Требуется выбрать из них три таких числа, произведение которых максимально.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // N - количество чисел в последовательности
        int N = scanner.nextInt();
        // Далее записана сама последовательность
        long[] a = new long[N];
        for (int i = 0; i < N; i++) {
            a[i] = scanner.nextLong();
        }
        maxMul3(a);
    }

    /**
     * G. Наибольшее произведение. Простейшее решение: просто перебираем тройки чисел,
     * считаем их произведение и выводим максимальное из произведений.
     * Оптимизация: можно рассматривать 3 максимальных элемента, если один
     *
     * @param a Массив для анализа
     */
    private static void maxMul3(long[] a) {
        long maxValue = a[0] * a[1] * a[2];
        int mi = 0, mj = 1, mk = 2;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                for (int k = j + 1; k < a.length; k++) {
                    long value = a[i] * a[j] * a[k];
                    if (value > maxValue) {
                        maxValue = value;
                        mi = i;
                        mj = j;
                        mk = k;
                    }
                }
            }
        }
        System.out.println(a[mi] + " " + a[mj] + " " + a[mk]);
    }
}
