package codility;

import org.junit.Assert;
import org.junit.Test;

public class EquilibriumTest extends Assert {

    public int solution(int[] A) {
        // write your code in Java SE 8
        long fullSum = 0;
        for (int x : A) fullSum += x;
        long sum = 0;
        for (int i = 0; i < A.length; i++) {
            long rest = fullSum - sum - A[i];
            if (sum == rest) return i;
            sum += A[i];
        }
        return -1;
    }

    @Test
    public void testEquilibrium() throws Exception {
        int[] A = new int[8];
        A[0] = -1;
        A[1] = 3;
        A[2] = -4;
        A[3] = 5;
        A[4] = 1;
        A[5] = -6;
        A[6] = 2;
        A[7] = 1;
        assertEquals(1, solution(A));

        //assertEquals( X());
    }
}
