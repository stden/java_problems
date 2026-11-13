package bracketstest;

import brackets.BracketsParser;
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
        assertTrue(BracketsParser.isValid("{{}}[]").isValid);
        assertTrue(BracketsParser.isValid("[{}{}]").isValid);
        // Невалидные "{[}]"
        assertFalse(BracketsParser.isValid("{[}]").isValid);
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
        assertTrue(BracketsParser.isValid(builder.toString()).isValid);
    }

    /**
     * Невалидные строки: нет закрывающей для открывающей
     */
    @Test
    public void testInvalidNoClose() {
        assertFalse(BracketsParser.isValid("{").isValid);
        assertEquals("Нет закрывающей '}' для '{' в позиции 0", BracketsParser.isValid("{").toString());
        assertEquals("Нет закрывающей ']' для '[' в позиции 1", BracketsParser.isValid("[[").toString());
        assertEquals("Нет закрывающей ')' для '(' в позиции 2", BracketsParser.isValid("(((").toString());
        assertEquals("Нет закрывающей '}' для '{' в позиции 2", BracketsParser.isValid("{}{[]").toString());
    }

    /**
     * Невалидные строки: нет открывающей для закрывающей
     */
    @Test
    public void testInvalidNoOpen() {
        assertFalse(BracketsParser.isValid("[]]").isValid);
        assertEquals("Нет открывающей скобки для '}' в позиции 2", BracketsParser.isValid("[]}").toString());
        assertEquals("Нет открывающей скобки для ')' в позиции 0", BracketsParser.isValid(")").toString());
        assertEquals("Нет открывающей скобки для ']' в позиции 8", BracketsParser.isValid("()(()())]").toString());
    }

    /**
     * Открывающая не соотвествует закрывающей
     */
    @Test
    public void testInvalidMismatch() {
        assertFalse(BracketsParser.isValid("{[}]").isValid);
        assertEquals("'{]' закрывающая ']' в 1 не соответствует открывающей '{' в 0", BracketsParser.isValid("{]").toString());
        assertEquals("'(}}' закрывающая '}' в 1 не соответствует открывающей '(' в 0", BracketsParser.isValid("(}}").toString());
    }

    /**
     * Неверные символы в строке
     */
    @Test
    public void testWrongSymbol() {
        assertEquals("Неверный символ x в позиции 1", BracketsParser.isValid("{x}").toString());
    }
}
