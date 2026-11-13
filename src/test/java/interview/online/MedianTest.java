package interview.online;

import org.junit.Assert;
import org.junit.Test;

public class MedianTest extends Assert {
    // a <= b <= c  - b   // X
    // a <= c <= b  - c   // Z
    // b <= a <= c  - a   // Y
    // b <= c <= a  - c   // Z
    // c <= a <= b  - a   // Y
    // c <= b <= a  - b   // X
    private long median(long a, long b, long c) {
        if ((a <= b && b <= c) || (c <= b && b <= a)) return b;  // X
        if ((b <= a && a <= c) || (c <= a && a <= b)) return a;  // Y
        return c; // Z
    }

    @Test
    public void testMedian() {
        // Перестановки из 3-х элементов
        assertEquals(2, median(1, 2, 3));
        assertEquals(2, median(1, 3, 2));
        assertEquals(2, median(2, 1, 3));
        assertEquals(2, median(2, 3, 1));
        assertEquals(2, median(3, 1, 2));
        assertEquals(2, median(3, 2, 1));
        // 2 равных элемента
        assertEquals(7, median(8, 7, 7));
        assertEquals(7, median(7, 8, 7));
        assertEquals(7, median(7, 7, 8));
        // Все элементы равны
        assertEquals(7, median(7, 7, 7));
    }
}
