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
        char[] str = S.toCharArray();
        int from = -1; // Word start
        for (int to = 0; to < str.length; to++) {
            char c = str[to];
            if (c >= 'a' && c <= 'z') {
                // Word start
                if (from == -1)
                    from = to;
                // Word end => reserve word: start..end
                if (to == str.length - 1 || str[to + 1] == ' ') {
                    for (int i = from, j = to; i < j; i++, j--) {
                        char temp = str[i];
                        str[i] = str[j];
                        str[j] = temp;
                    }
                    from = -1;
                }
            }
        }
        return new String(str);
    }
}