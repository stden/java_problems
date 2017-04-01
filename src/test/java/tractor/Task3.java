package tractor;

public class Task3 {
    public int solution(int[] A) {
        int maxLen = 1;
        int P = 0;
        for (int Q = 0; Q < A.length; Q++) {
            boolean OK;
            do {
                int x0 = (Q - 2 >= P) ? A[Q - 2] : 0;
                int x1 = (Q - 1 >= P) ? A[Q - 1] : 0;
                int x2 = A[Q];
                OK = (x0 >= 0 && x1 <= 0 && x2 >= 0) ||
                        (x0 <= 0 && x1 >= 0 && x2 <= 0);
                if (!OK) P++;
            } while (!OK);
            int len = Q - P + 1;
            if (len > maxLen)
                maxLen = len;
        }
        return maxLen;
    }
}
