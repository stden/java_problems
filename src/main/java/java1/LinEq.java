package java1;

import java.util.Scanner;

import static java.lang.Math.abs;

/**
 * Линейное уравнение
 */
public class LinEq {
    private static final double EPS = 1e-15;

    public static void main(String[] args) {
        // ax + b = 0
        Scanner in = new Scanner(System.in);
        double a = in.nextDouble();
        double b = in.nextDouble();
        System.out.println(solve(a, b));
    }

    public static String solve(double a, double b) {
        if (abs(a) < EPS) {
            return abs(b) < EPS ? "x - any" : "No solutions";
        }
        if (Double.isInfinite(a) || Double.isInfinite(b) ||
                Double.isNaN(a) || Double.isNaN(b)) {
            return "Incorrect input";
        }
        double x = -b / a;
        return "x = " + (x + 0.0);
    }
}
