package codility;

import org.junit.Assert;
import org.junit.Test;

public class PasswordCheck extends Assert {

    public int solution(String S) {
        int maxLen = -1;
        int N = S.length();
        for (int i = 0; i < N; i++) {
            // i..j
            boolean uppercase = false;
            for (int j = i; j < N; j++) {
                char c = S.charAt(j);
                if (c >= 'A' && c <= 'Z') uppercase = true;
                if (c >= '0' && c <= '9') break;
                if (uppercase) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    @Test
    public void name() throws Exception {
        assertEquals(-1, solution("a0bb"));
        assertEquals(2, solution("a0Ba"));
        assertEquals(3, solution("aBa"));
        assertEquals(12, solution("LongPassword2aba"));
        assertEquals(1, solution("longpassword2B3aba"));
        assertEquals(8, solution("Password"));
        assertEquals(8, solution("78976Password59"));
    }
}
