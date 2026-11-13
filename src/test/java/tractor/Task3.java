package tractor;

public class Task3 {
    public int solution(int[] A) {
        int maxLen = 0;
        int P = 0;
        int N = A.length;
        for (int Q = 0; Q < N; Q++) {
            boolean alternatingSlice;
            do {
                int a = (Q - 2 >= P) ? A[Q - 2] : 0;
                int b = (Q - 1 >= P) ? A[Q - 1] : 0;
                int c = A[Q];
                alternatingSlice = (a >= 0 && b <= 0 && c >= 0) ||
                        (a <= 0 && b >= 0 && c <= 0);
                if (!alternatingSlice) P++;
            } while (!alternatingSlice);
            System.out.println(P + ".." + Q);
            maxLen = Math.max(maxLen, Q - P + 1);
        }
        return maxLen;
    }
}
