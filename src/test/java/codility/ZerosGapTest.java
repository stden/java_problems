package codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ZerosGapTest {

    public int solution(int N) {
        int max = 0;
        int count = -1000;
        while (N > 0) {
            if (N % 2 == 0) {
                count++;
            } else {
                if (count > max)
                    max = count;
                count = 0;
            }
            N = N / 2;
        }
        return max;
    }

    @Test
    public void testNests() throws Exception {
        assertEquals(5, solution(1041));
        assertEquals(1, solution(0b0010100));
    }
}
