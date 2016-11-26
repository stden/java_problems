import java.util.Scanner;

public class LookAndSay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // В первой строке записана начальная цифра X - первая цифра последовательности.
        String X = scanner.nextLine().trim();
        // Во второй строке записано число N - сколько членов последовательности вам надо вывести.
        int N = scanner.nextInt();
        System.out.println(X);
        for (int i = 1; i < N; i++) {
            X = next(X); // Следующая строка в последовательности
            System.out.println(X);
        }
    }

    /**
     * Следующая строка в последовательности Look and Say
     *
     * @param s текущая строка
     * @return следующая строка
     */
    static String next(String s) {
        if (s.length() == 0)
            throw new IllegalArgumentException("Строчка не должна быть пустой");
        StringBuilder result = new StringBuilder();
        char lastChar = s.charAt(0); // Текущая цифра
        int count = 1; // он один
        // И начинаем со второго
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != lastChar) { // Если цифра меняется
                result.append(count).append(lastChar);
                count = 1;
                lastChar = c;
            } else {
                count++;
            }
        }
        result.append(count).append(lastChar);
        return result.toString();
    }
}
