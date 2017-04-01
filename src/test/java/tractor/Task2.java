package tractor;

/**
 * Given two strings S and T consisting of N and M characters,
 * respectively, determines whether string
 * T can be obtained from string S by at most one insertion or deletion of a character,
 * or by swapping two adjacent characters once.
 */
public class Task2 {
    public String solution(String S, String T) {
        int N = S.length();
        int M = T.length();
        // "NOTHING" if no operation is needed (strings T and S are equal)
        if (S.equals(T))
            return "NOTHING";
        // Find diffs
        int diffs = 0;
        int i1 = 0, i2 = 0;
        for (int i = 0; i < N && i < M; i++) {
            if (S.charAt(i) != T.charAt(i)) {
                diffs++;
                if (diffs == 1) i1 = i;
                if (diffs == 2) i2 = i;
            }
        }
        if (N == M &&
                diffs == 2 &&
                S.charAt(i1) == T.charAt(i2) &&
                S.charAt(i2) == T.charAt(i1)) {
            return "SWAP " + S.charAt(i1) + " " + S.charAt(i2);
        }
        if (N == M + 1 &&
                T.equals(S.substring(0, i1) + S.substring(i1 + 1))) {
            return "DELETE " + S.charAt(i1);
        }
        if (N + 1 == M &&
                S.equals(T.substring(0, i1) + T.substring(i1 + 1))) {
            return "INSERT " + T.charAt(i1);
        }
        return "IMPOSSIBLE";
    }
}
