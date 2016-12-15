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

    /**
     * @param s Строка со скобками для проверки
     * @return Является ли строка скобок валидной?
     */
    public static Result isValid(String s) {
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
                    return new Result(String.format("Нет открывающей скобки для '%s' в позиции %d", c, i));
                }
                // Получаем открывающую скобку
                BracketPair pair = stack.pop();
                // Если открывающая скобка не соответствует закрывающей => невалидная
                char open = pair.open; // Открывающая скобка
                char expected = types.get(open); // Ожидаемая закрывающая
                if (c != expected) {
                    return new Result(String.format("'%s' закрывающая '%s' в %d не соответствует открывающей '%s' в %d",
                            s, c, i, open, pair.openPos));
                }
                // Сохраняем информацию о закрывающей скобке
                pair.closePos = i;
                pair.close = c;
                // Добавляем в список для вывода если последовательность валидная
                list.add(pair);
            } else {
                return new Result(String.format("Неверный символ %s в позиции %d", c, i));
            }
        }
        // Проверяем баланс скобок
        if (!stack.isEmpty()) {
            BracketPair p = stack.pop();
            return new Result(String.format("Нет закрывающей '%s' для '%s' в позиции %d", types.get(p.open), p.open, p.openPos));
        }
        // Список всех скобок с позициями открывающей и закрывающей
        return new Result(String.format("Список всех скобок с позициями открывающей и закрывающей для строки '%s'%n", s),
                list);
    }

    public static class Result {
        public final boolean isValid;
        private final String result;
        private final List<BracketPair> list;

        Result(String result) {
            isValid = false;
            this.result = result;
            this.list = null;
        }

        Result(String result, List<BracketPair> list) {
            isValid = true;
            this.result = result;
            this.list = list;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(result);
            if (list != null)
                for (BracketPair p : list)
                    sb.append(p).append(System.lineSeparator());
            return sb.toString();
        }

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
