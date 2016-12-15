// Задача "В двоичную систему счисления"
// Дано число в десятичной системе счисления,
// нужно вывести его в двоичной системе счисления.

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
        String s = ""; // Число в двоичной системе счисления
        do {
            s = (x % 2) + s; // Очередно двоичный разряд
            x /= 2; // Делим число на 2
        } while (x > 0);
        return s;
    }

    static String toBin2(long x) {
        return Long.toBinaryString(x);
    }
}
