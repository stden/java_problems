package codility;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MinValue2 extends Assert {

    int solution(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        Arrays.sort(A);
        Arrays.sort(B);
        int i = 0;
        for (int k = 0; k < n; k++) {
            while (i < m - 1 && B[i] < A[k])
                i += 1;
            if (A[k] == B[i])
                return A[k];
        }
        return -1;
    }

    @Test
    public void testSimple() throws Exception {
        int[] A = new int[4];
        int[] B = new int[5];
        A[0] = 1;
        B[0] = 4;
        A[1] = 3;
        B[1] = 2;
        A[2] = 2;
        B[2] = 5;
        A[3] = 1;
        B[3] = 3;
        B[4] = 2;
        assertEquals(2, solution(A, B));
    }

    @Test
    public void test2() throws Exception {
        int[] A = new int[2];
        int[] B = new int[2];
        A[0] = 2;
        A[1] = 1;
        B[0] = 3;
        B[1] = 3;
        assertEquals(-1, solution(A, B));
    }


    @Test
    public void test3() throws Exception {
        assertEquals(-1, solution(new int[]{2}, new int[]{1}));
        assertEquals(1, solution(new int[]{1, 2},
                new int[]{-5, -4, -3, 1}));
    }
}
