package brackets;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Нужно распарсить строчку из скобок: фигурные, квадратные, круглые
 */
public class BracketsParserTest extends Assert {
    /**
     * Примеры из условия
     */
    @Test
    public void testSample() {
        // Валидные строки "{{}}[]" или "[{}{}]"
        assertTrue(BracketsParser.isValid("{{}}[]"));
        assertTrue(BracketsParser.isValid("[{}{}]"));
        // Невалидные "{[}]"
        assertFalse(BracketsParser.isValid("{[}]"));
    }

    /**
     * Валидные строки
     */
    @Test
    public void testBigValid() {
        Random gen = new Random();
        // Количество пар скобок
        int pairs = 100000;
        // Соберём из всех в этой строке
        StringBuilder builder = new StringBuilder();
        int open = 0; // Открывающих скобок поставлено
        int close = 0; // Закрывающих скобок поставлено
        while (open < pairs || close < pairs) {
            if (open < pairs) { // Можем ставить открывающие
                if (close < open) { // И закрывающие можем
                    if (gen.nextInt(2) == 0) {
                        builder.append('(');
                        open++;
                    } else {
                        builder.append(')');
                        close++;
                    }
                } else {
                    builder.append('(');
                    open++;
                }
            } else {
                builder.append(')');
                close++;
            }
        }
        assertEquals(pairs, open);
        assertEquals(pairs, close);
        BracketsParser.setPrintResult(false);
        assertTrue(BracketsParser.isValid(builder.toString()));
    }

    /**
     * Невалидные строки: нет закрывающей для открывающей
     */
    @Test
    public void testInvalidNoClose() {
        assertFalse(BracketsParser.isValid("{"));
        assertFalse(BracketsParser.isValid("[["));
        assertFalse(BracketsParser.isValid("((("));
        assertFalse(BracketsParser.isValid("{}{[]"));
    }

    /**
     * Невалидные строки: нет открывающей для закрывающей
     */
    @Test
    public void testInvalidNoOpen() {
        assertFalse(BracketsParser.isValid("[]]"));
        assertFalse(BracketsParser.isValid("[]}"));
        assertFalse(BracketsParser.isValid(")"));
        assertFalse(BracketsParser.isValid("()(()())]"));
    }

    /**
     * Открывающая не соотвествует закрывающей
     */
    @Test
    public void testInvalidMismatch() {
        assertFalse(BracketsParser.isValid("{[}]"));
        assertFalse(BracketsParser.isValid("{]"));
        assertFalse(BracketsParser.isValid("(}}"));
    }

    /**
     * Неверные символы в строке
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWrongSymbol() {
        BracketsParser.isValid("{x}");
    }
}
