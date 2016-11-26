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
     * @param x    число в десятичной системе счисления
     * @param base основание системы счисления
     * @return x в системе счисления base
     */
    static String toAny(long x, int base) {
        if (base < 2 || base > 36)
            throw new IllegalArgumentException("base = " + base + "  [2..36]");
        if (x == 0)
            return "0";
        String s = "";
        while (x > 0) {
            int d = (int) (x % base);
            s = Character.forDigit(d, base) + s;
            x /= base;
        }
        return s.toUpperCase();
    }


}
