import java.util.Scanner;

import static java.lang.Integer.min;

public class Decomposition {

    private int n;

    // Слагаемые
    private int[] A;

    private Decomposition(int n) {
        this.n = n;
        A = new int[n];
        sum(n, n, 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Вводим число N
        int N = scanner.nextInt();
        // Вывод разложения на слагаемые
        new Decomposition(N);
    }

    /**
     * Разложение на слагаемые с помощью рекурсии со следующими параметрами:
     *
     * @param N    оставшееся число для разложения
     * @param high последнее слагаемое в сумме (из уже имеющихся слагаемых)
     * @param K    количество слагаемых
     */
    private void sum(int N, int high, int K) {
        // Если от суммы ничего не осталось
        // => окончание рекурсии
        //  выводим ответ.
        if (N == 0) {
            System.out.print(n + " = " + A[0]);
            // все слагаемые кроме последнего
            // с ' + '
            // последнее слагаемое с переводом строки
            for (int i = 1; i < K; ++i)
                System.out.print(" + " + A[i]);
            System.out.println();
            // Вывели ответ => дальше нечего делать в этой ветке рекурсии => выходим
            return;
        }

        // N - сумма, которую надо разложить
        // high - верхнее ограничение на слагаемое
        //   5 = 2 + ...   и ещё есть 4
        //    тогда следующее слагаемое 2, 1
        //   5 = 4 + ...   ещё есть 1
        //    следующее слагаемое максимум 1
        for (int x = min(N, high); x >= 1; --x) {
            // x - текущее слагаемое
            A[K] = x;
            // Сумма уменьшилась на x
            // Максимальное слагаемое - x
            // Идём вглубь рекурсии
            sum(N - x, x, K + 1);
        }
    }
}
