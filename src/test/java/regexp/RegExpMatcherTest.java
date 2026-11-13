package regexp;

import org.junit.Assert;
import org.junit.Test;

import static regexp.RegExpMatcher.*;


public class RegExpMatcherTest extends Assert {
    /**
     * Регулярное выражение для числа с плавающей точкой
     */
    @Test
    public void testFloat() {
        assertTrue(isFloat("1.233"));
        assertTrue(isFloat("24"));
        assertTrue(isFloat(".233"));
        assertTrue(isFloat(".233"));
        assertTrue(isFloat("+0.233"));
        assertTrue(isFloat("-0.233"));
        assertTrue(isFloat("5."));
        assertTrue(isFloat("000.1"));

        assertFalse(isFloat("-0.2.33"));
        assertFalse(isFloat("--0.2"));
        assertFalse(isFloat("++0.2"));
        assertFalse(isFloat("0.2x"));
        assertFalse(isFloat(""));
    }

    @Test
    public void testComplexNumber() {
        assertTrue(isComplexNumber("2+3i"));
        assertTrue(isComplexNumber("2i"));
        assertTrue(isComplexNumber("3-2i"));
        assertTrue(isComplexNumber("3"));
        assertTrue(isComplexNumber("15.43+8i"));
        assertTrue(isComplexNumber("15.43-8i"));
        assertTrue(isComplexNumber("42.21-4.12i"));
    }

    @Test
    public void testComplexList() {
        assertTrue(isComplexList("2+3i,2i"));
        assertTrue(isComplexList("2i"));
        assertTrue(isComplexList("15.43+8i,32.3211231-22.333i"));
        assertTrue(isComplexList("2i,3-2i"));
        assertTrue(isComplexList("3-2i,2i"));
        System.out.println(RegExpMatcher.COMPLEX_LIST);
    }
}
