package brackets;

import java.util.*;

/**
 * Обработкаи строчки из скобок: фигурные, квадратные, круглые
 */
public class BracketsParser {
    // Типы скобок
    private static final Map<Character, Character> types = new HashMap<Character, Character>() {{
        put('{', '}'); // Фигурные скобки
        put('[', ']'); // Квадратные скобки
        put('(', ')'); // Круглые скобки
    }};

    private static boolean printResult = true;

    /**
     * @param s Строка со скобками для проверки
     * @return Является ли строка скобок валидной?
     */
    public static boolean isValid(String s) {
        // Стек для проверки корректности скобочной последовательности
        Stack<BracketPair> stack = new Stack<>();
        // Список с результатами
        List<BracketPair> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); // Текущий символ
            if (types.containsKey(c)) { // Если это открывающая скобка
                // Добавляем её в стек
                stack.push(new BracketPair(c, i));
            } else if (types.containsValue(c)) {
                // Если в стеке ничего нет => невалидная последовательсность
                if (stack.isEmpty()) {
                    System.out.printf("Нет открывающей скобки для '%s' в позиции %d%n", c, i);
                    return false;
                }
                // Получаем открывающую скобку
                BracketPair pair = stack.pop();
                // Если открывающая скобка не соответствует закрывающей => невалидная
                char open = pair.open; // Открывающая скобка
                char expected = types.get(open); // Ожидаемая закрывающая
                if (c != expected) {
                    System.out.printf("'%s' закрывающая '%s' в %d не соответствует открывающей '%s' в %d%n",
                            s, c, i, open, pair.openPos);
                    return false;
                }
                // Сохраняем информацию о закрывающей скобке
                pair.closePos = i;
                pair.close = c;
                // Добавляем в список для вывода если последовательность валидная
                list.add(pair);
            } else {
                String msg = String.format("Неверный символ %s в позиции %d", c, i);
                System.out.println(msg);
                throw new IllegalArgumentException(msg);
            }
        }
        // Проверяем баланс скобок
        if (!stack.isEmpty()) {
            BracketPair p = stack.pop();
            System.out.printf("Нет закрывающей '%s' для '%s' в позиции %d%n", types.get(p.open), p.open, p.openPos);
            return false;
        }
        // Список всех скобок с позициями открывающей и закрывающей
        if (printResult) {
            System.out.println("Список всех скобок с позициями открывающей и закрывающей");
            System.out.printf("для строки '%s'%n", s);
            for (BracketPair p : list)
                System.out.println(p);
        }
        return true;
    }

    static void setPrintResult(boolean printResult) {
        BracketsParser.printResult = printResult;
    }

    /**
     * Пара скобок: открывающая и закрывающая с их позициями
     */
    private static class BracketPair {
        private final char open; // Открывающая скобка (фигурная, круглая, квадратная...)
        private final int openPos; // Позиция открывающей скобки
        char close = ' '; // Закрывающая скобка (фигурная, круглая, квадратная...)
        int closePos = -1; // Позиция закрывающей скобки

        BracketPair(char open, int openPos) {
            this.open = open;
            this.openPos = openPos;
        }

        /**
         * @return Строка для печати: типы скобок + их позиции
         */
        @Override
        public String toString() {
            return String.format("%s%s %d-%d", open, close, openPos, closePos);
        }
    }
}
