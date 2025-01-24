/*
 * You are given a non-empty string S consisting of N characters.
 * S can be divided into words by splitting it
 * at the spaces and removing them. We want to reverse every word in S.
 * For example, given S = “we test coders”,
 * there are three words: “we”, “test” and “coders”. Reversing the
 * words given “we”, “tset”, “sredoc”.
 * The goal is return a string with every word in string S reversed and separated by spaces,
 * so the result in
 * the above example should be “ew tset sredoc”.
 */
package tractor;

public class Task1 {
    public String solution(String S) {
        if (S.length() <= 1) return S;
        char[] s = S.toCharArray();
        int N = s.length;
        int start = -1; // Word start
        for (int end = 0; end < N; end++) {
            char c = s[end];
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
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
}