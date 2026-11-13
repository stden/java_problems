import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Как продолжить работу со сканером после ошибки?
 */
public class ScannerRestoreAfterError {
    public static void main(String[] args) {
        System.out.println("Программа суммирует вводимые числа, " +
                "для выхода введите quit");
        Scanner scan = new Scanner(System.in);
        int sum = 0; // Сумма
        while (true) {
            try {
                System.out.print("Введите целое число: ");
                int number = scan.nextInt();
                sum += number;
                System.out.println("Сумма: " + sum);
            } catch (InputMismatchException ex) {
                String userInput = scan.nextLine();
                if (userInput.equals("quit"))
                    break; // Выходим из цикла
                System.out.println("\"" + userInput + "\" - НЕ целое число");
            }
        }
    }
}
