import java.util.Arrays;
import java.util.Scanner;

public class Sort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // N - количество чисел в последовательности
        int N = scanner.nextInt();
        // Далее записана сама последовательность
        long[] a = new long[N];
        for (int i = 0; i < N; i++) {
            a[i] = scanner.nextLong();
        }
        // Сортируем
        Arrays.sort(a);
        // Выводим
        for (int i = 0; i < N - 1; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println(a[N - 1]);
    }
}
