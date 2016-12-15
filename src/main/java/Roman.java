import java.util.ArrayList;
import java.util.Scanner;

public class Roman {
    // Все соответствия добавим в массив
    private static ArrayList<RN> romans = new ArrayList<>();

    static {
        int I = 1;
        romans.add(new RN("I", I));      // 1
        int V = 5;
        romans.add(new RN("IV", V - I)); // 4
        romans.add(new RN("V", V));      // 5
        int X = 10;
        romans.add(new RN("IX", X - I)); // 9
        romans.add(new RN("X", X));      // 10
        int L = 50;
        romans.add(new RN("XL", L - X)); // 40
        romans.add(new RN("L", L));      // 50
        int C = 100;
        romans.add(new RN("XC", C - X)); // 90
        romans.add(new RN("C", C));      // 100
        int D = 500;
        romans.add(new RN("CD", D - C)); // 400
        romans.add(new RN("D", D));      // 500
        int M = 1000;
        romans.add(new RN("CM", M - C)); // 900
        romans.add(new RN("M", M));      // 1000
        // Сортируем по убыванию, сначала большие
        romans.sort((a, b) -> b.value - a.value);
    }

    /**
     * @param x число в десятичной системе счисления
     * @return x число в римской системе счисления
     */
    static String toRoman(int x) {
        String roman = "";
        for (RN r : romans) {
            while (r.value <= x) {
                x -= r.value;
                roman += r.roman;
            }
        }
        return roman;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tests = scanner.nextInt();
        for (int t = 0; t < tests; t++) {
            System.out.println(toRoman(scanner.nextInt()));
        }
    }

    // Соответствие числа в римской и в десятичной системе счисления
    static class RN {
        String roman; // Число в римской
        int value; // Число в десятичной

        // Конструктор для удобного создания
        RN(String roman, int value) {
            this.roman = roman;
            this.value = value;
        }
    }
}
