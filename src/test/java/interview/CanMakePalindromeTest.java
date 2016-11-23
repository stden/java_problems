package interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CanMakePalindromeTest extends Assert {
    @Test
    public void testPalindrome() {
        assertFalse(canMakePalindrome("test"));
        assertTrue(canMakePalindrome("aab"));
        assertTrue(canMakePalindrome("eeeff"));
        assertTrue(canMakePalindrome("aabbccdd"));
        assertFalse(canMakePalindrome("hello"));
    }

    /**
     * Можно ли превратить заданную строку в палиндром перестановкой букв?
     *
     * @param s Строка для проверки
     * @return можно ли превратить?
     */
    private boolean canMakePalindrome(String s) {
        // Считаем количество букв в строке
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            m.put(c, m.getOrDefault(c, 0) + 1);
        }
        // Проверка что всех букв чётно, что нечётных <= 1
        int odd = 0;
        for (char c : m.keySet()) {
            int n = m.get(c);
            if (n % 2 == 1) odd++;
        }
        return odd <= 1;
    }
}
