import java.util.Scanner;

public class Roman {
    private static int[] numbers = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    private static String[] strings = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tests = scanner.nextInt();
        for (int t = 0; t < tests; t++) {
            System.out.println(toRoman(scanner.nextInt()));
        }
    }

    /**
     * @param x число в десятичной системе счисления
     * @return x число в римской системе счисления
     */
    static String toRoman(int x) {
        String roman = "";
        for (int k = numbers.length - 1; k >= 0; k--) {
            while (numbers[k] <= x) {
                x -= numbers[k];
                roman += strings[k];
            }
        }
        return roman;
    }
}
