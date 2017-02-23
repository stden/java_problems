import org.junit.Assert;
import org.junit.Test;

public class ABCStringTest extends Assert {

    /**
     * Given a text, find whether it contains the alphabet, in order, as subsequence
     */
    @Test
    public void testABC() throws Exception {
        assertFalse(containsAlphabetInOrder("abcdefghijklmnopqrstuvxzy"));
        assertTrue(containsAlphabetInOrder("la nbunclidue gfslgihuniaj kgllimunnoagpr lqinr setgluivn ewgwxzlyiuzzs li"));
    }

    /**
     * Given a text find the shortest substring which contains the alphabet, in order, as subsequence
     * <pre>
     * ,,a,,b,,c,a,d,b,,e,c,,,,,d,a,e,b,,c,,d,e,,,,,,,,,,,,,,,,,,,,,,,
     *   |--|--|---|----|         |---|--|--|-|
     *           |---|----|-----|---|
     * </pre>
     */
    @Test
    public void testxx() throws Exception {
        shortestSubstring(",,a,,b,,c,a,d,b,,e,c,,,,,d,a,e,b,,c,,d,e,,,,,,,,,,,,,,,,,,,,,,,");
    }

    private void shortestSubstring(String s) {
        int minLength = Integer.MAX_VALUE;
        int minStart = -1, minEnd = -1;
        Pos[] pos = new Pos[26]; // 0..25
        for (int i = 0; i < 26; i++) pos[i] = new Pos();
        // Time Complexity: O(n)   n = s.length() 
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'e') {
                int idx = (int) (c - 'a');
                if (c == 'a') {
                    pos[idx].start = i;
                    pos[idx].end = i;
                } else {
                    if (pos[idx - 1].start != -1) {
                        pos[idx].start = pos[idx - 1].start;
                        pos[idx].end = i;
                        if (c == 'e') {
                            int len = pos[idx].end - pos[idx].start + 1;
                            if (len < minLength) {
                                minLength = len;
                                minStart = pos[idx].start;
                                minEnd = pos[idx].end;
                            }
                        }
                    }
                }
            }
        }
        if (minLength == Integer.MAX_VALUE)
            System.out.println("s - doesn’t contains a..z");
        else
            System.out.println("Answer = " + minLength + " from " + minStart + " to " + minEnd);
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
        int start = -1;
        int end = -1;
    }

}
