import org.junit.Assert;
import org.junit.Test;

public class ABCStringTest extends Assert {

    /**
     * Find whether it contains the alphabet, in order, as subsequence
     */
    @Test
    public void testABC() throws Exception {
        assertFalse(containsAlphabetInOrder("abcdefghijklmnopqrstuvxzy"));
        assertTrue(containsAlphabetInOrder("la nbunclidue gfslgihuniaj kgllimunnoagpr lqinr setgluivn ewgwxzlyiuzzs li"));
    }

    /**
     * Find shortest substring which contains the alphabet, in order, as subsequence
     * <pre>
     * ..a..b..c.a.d.b..e.c.....d.a.e.b..c..d.e.......................
     *   |--|--|---|----|         |---|--|--|-|
     *           |---|----|-----|---|
     * </pre>
     */
    @Test
    public void testAlphabetShortest() throws Exception {
        assertEquals("Min length = 13 27..39", shortestAE("..a..b..c.a.d.b..e.c.....d.a.e.b..c..d.e......................."));
        assertEquals("String doesn’t contains a..e", shortestAE("..a..e..b..c.a.d.b..c.....d.a.b..c..d......................."));
    }

    private String shortestAE(String s) {
        int minLength = Integer.MAX_VALUE;
        int minStart = -1, minEnd = -1;
        Pos[] pos = new Pos[26]; // 0..25
        for (int i = 0; i < 26; i++) pos[i] = new Pos();
        // Time Complexity: O(n)   n = s.length() 
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'e') {
                int idx = c - 'a';
                if (c == 'a') {
                    pos[idx].posA = i;
                    pos[idx].end = i;
                } else {
                    if (pos[idx - 1].posA != -1) {
                        pos[idx].posA = pos[idx - 1].posA;
                        pos[idx].end = i;
                        if (c == 'e') {
                            int len = pos[idx].end - pos[idx].posA + 1;
                            if (len < minLength) {
                                minLength = len;
                                minStart = pos[idx].posA;
                                minEnd = pos[idx].end;
                            }
                        }
                    }
                }
            }
        }
        if (minLength == Integer.MAX_VALUE)
            return "String doesn’t contains a..e";
        else
            return "Min length = " + minLength + " " + minStart + ".." + minEnd;
    }

    private boolean containsAlphabetInOrder(String s) {
        char curChar = 'a' - 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == (curChar + 1)) {
                curChar = c;
                if (curChar == 'z') break; // optimization
            }
        }
        return curChar == 'z';
    }

    static class Pos {
        int posA = -1;
        int end = -1;
    }

}
