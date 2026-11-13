package java1;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class LinearEquation {
    public static void main(String... args) {
        System.out.println("Решение линейного уравнения");
        System.out.println("ax + b = 0");
        Scanner scan = new Scanner(System.in);
        scan.useLocale(Locale.US);
        double a = 0; // Нужно значение по-умолчанию
        boolean OK; // Верно ли пользователь ввёл?
        do { // Требуем вводить число пока пользователь не введёт правильно!
            System.out.print("Введите a: ");
            try {
                a = scan.nextDouble(); // Пытаемся ввести double
                OK = true; // Уфф! Ввёл правильно! Молодец :)
            } catch (InputMismatchException ex) { // Пользователь ввёл что-то не то :)
                // Ругаем пользователя и уходим на новую итерацию цикла ввода
                System.out.println("Ожидается число в формате 3.14, " +
                        "а вы ввели: \"" + scan.nextLine() + "\"");
                OK = false; // Ну когда же он наконец-то поймёт что надо вводить?!!
            }
        } while (!OK);
        System.out.print("Введите b: ");
        double b = scan.nextDouble();
        double x = -b / a;
        System.out.println("x = " + x);
    }
}
