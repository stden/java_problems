package codility;

import org.junit.Assert;
import org.junit.Test;

public class Task1 extends Assert {
    public String solution(String S) {
        char[] s = S.toCharArray();
        int N = s.length;
        int start = -1; // Word start
        for (int end = 0; end < N; end++) {
            char c = s[end];
            if (c >= 'a' && c <= 'z') {
                if (start == -1) // Word start
                    start = end;
                // Word end => reserve word: start..end
                if (end == s.length - 1 || s[end + 1] == ' ') {
                    for (int i = start, j = end; i < j; i++, j--) {
                        char temp = s[i];
                        s[i] = s[j];
                        s[j] = temp;
                    }
                    start = -1;
                }
            }
        }
        return new String(s);
    }

    @Test
    public void name() throws Exception {
        assertEquals("ew tset sredoc", solution("we test coders"));
        assertEquals(" i love ", solution(" i evol "));


    }
}
