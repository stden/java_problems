package codility;

import org.junit.Assert;
import org.junit.Test;

public class Task2 extends Assert {
    public String solution(String S, String T) {
        int N = S.length();
        int M = T.length();

        if (S.equals(T)) return "NOTHING";

        // Analyze diffs
        int diffCount = 0;
        int diff1 = 0, diff2 = 0;
        for (int i = 0; i < N && i < M; i++) {
            if (S.charAt(i) != T.charAt(i)) {
                diffCount++;
                if (diffCount == 1) diff1 = i;
                if (diffCount == 2) diff2 = i;
            }
        }
        // Lengths is equal, 2 diffs
        if (N == M &&
                diffCount == 2 &&
                S.charAt(diff1) == T.charAt(diff2) &&
                S.charAt(diff2) == T.charAt(diff1)) {
            return "SWAP " + S.charAt(diff1) + " " + S.charAt(diff2);
        }
        if (N == M + 1 &&
                T.equals(S.substring(0, diff1) + S.substring(diff1 + 1))) {
            return "DELETE " + S.charAt(diff1);
        }
        if (N + 1 == M &&
                S.equals(T.substring(0, diff1) + T.substring(diff1 + 1))) {
            return "INSERT " + T.charAt(diff1);
        }
        return "IMPOSSIBLE";
    }

    @Test
    public void name() throws Exception {
        assertEquals("INSERT e", solution("nice", "niece"));
        assertEquals("DELETE e", solution("niece", "nice"));
        assertEquals("SWAP o r", solution("form", "from"));
        assertEquals("IMPOSSIBLE", solution("o", "odd"));
        assertEquals("IMPOSSIBLE", solution("nieced", "nice"));
    }
}
