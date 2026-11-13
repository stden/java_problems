import java.util.Scanner;

public class ToHex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt())
            System.out.println(toHex(scanner.nextInt()));
    }

    /**
     * @param x число в десятичной системе счисления
     * @return x в шестнадцатеричной системе счисления
     */
    static String toHex(long x) {
        return Long.toHexString(x).toUpperCase();
    }

}
