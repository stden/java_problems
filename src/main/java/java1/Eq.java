package java1;

import java.io.IOException;
import java.util.Scanner;

// Задача "Сравнение"
// Сумма двух чисел с плавающей точкой с учётом погрешности
// Условие:
//   Даны три действительных числа: $a$, $b$, $c$ (floating point).
//   Проверьте, выполняется ли равенство $a + b = c$?
public class Eq {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        // В первой строке разделённые пробелами 3 числа:
        // a, b и c - действительные, не превосходят по модулю 1000
        // и заданы не более чем с 7 знаками после десятичной точки.
        double a = scan.nextDouble(), b = scan.nextDouble(), c = scan.nextDouble();
        System.out.println(solve(a, b, c));
    }

    static String solve(double a, double b, double c) {
        return Math.abs(a + b - c) < 1e-8 ? "YES" : "NO";
    }
}
