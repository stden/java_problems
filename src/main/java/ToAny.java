import java.util.Scanner;

public class ToAny {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLong()) {
            long value = scanner.nextLong(); // Число в десятичной системе счисления
            int base = scanner.nextInt(); // Основание системы счисления
            System.out.println(toAny(value, base));
        }
    }

    /**
     * @param x     число в десятичной системе счисления
     * @param radix основание системы счисления
     * @return x в системе счисления radix
     */
    static String toAny(long x, int radix) {
        // Проверяем ограничения
        if (x < 0)
            throw new IllegalArgumentException("x = " + x + " < 0");
        if (radix < 2 || radix > 36)
            throw new IllegalArgumentException("radix = " + radix + "  [2..36]");
        String s = ""; // Сюда поместим результат
        do {
            int digit = (int) (x % radix); // Цифра числа
            // Для перевода одной цифры можно написать свою функцию или использовать стандартную
            s = Character.forDigit(digit, radix) + s; // Добавяем её в результат
            x /= radix;
        } while (x > 0);
        return s.toUpperCase();
    }
}
