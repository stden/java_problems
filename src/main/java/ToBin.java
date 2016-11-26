import java.util.Scanner;

public class ToBin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tests = scanner.nextInt(); // Количество тестов
        for (int i = 0; i < tests; i++) { // Обрабатываем каждый тест
            System.out.println(toBin(scanner.nextLong()));
        }
    }

    /**
     * @param x число в десятичной системе счисления
     * @return x в двоичной системе счисления
     */
    static String toBin(long x) {
        return Long.toBinaryString(x);
    }

}
