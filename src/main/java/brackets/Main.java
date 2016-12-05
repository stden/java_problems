package brackets;

import static brackets.BracketsParser.isValid;

public class Main {
    /**
     * Использование из командной строки
     *
     * @param args Аргументы программы
     */
    public static void main(String[] args) {
        // Если пользователь не указал аргументы командной строки, то выводим помощь
        if (args.length < 1) {
            System.out.println("Проверка правильности строчки из скобок: фигурные, квадратные, круглые");
            System.out.println("Примеры корректных строк: '{{}}[]', '[{}{}]', '[]'");
            System.out.println("Пример некорректной строки: '{[}]'");
            System.out.println("Скобочную последовательность для проверки передавайте как аргумент в командной строке");
            System.out.println("Пример использования программы: ");
            System.out.println("  java " + BracketsParser.class.getCanonicalName() + " {{}}[]");
            return;
        }
        // Считаем что первый агрумент - строка для проверки
        try {
            String s = args[0];
            System.out.printf("Строка для проверки: '%s'%n", s);
            System.out.println(isValid(s) ? "  -- валидная" : "  -- невалидная");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
