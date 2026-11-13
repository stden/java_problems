import org.junit.Assert;
import org.junit.Test;

public class ToAnyTest extends Assert {
    @Test
    public void testAny() {
        assertEquals("1010", ToAny.toAny(10, 2));
        assertEquals("1101", ToAny.toAny(13, 2));
        assertEquals("0", ToAny.toAny(0, 16));
        assertEquals("1", ToAny.toAny(1, 8));
        assertEquals("10", ToAny.toAny(5, 5));
        assertEquals("B", ToAny.toAny(11, 14));
        assertEquals("C", ToAny.toAny(12, 17));
        assertEquals("14", ToAny.toAny(14, 10));
        assertEquals("F", ToAny.toAny(15, 16));
        assertEquals("10", ToAny.toAny(16, 16));
        assertEquals("11", ToAny.toAny(17, 16));
        assertEquals("13", ToAny.toAny(19, 16));
        assertEquals("14", ToAny.toAny(20, 16));
        assertEquals("SY", ToAny.toAny(1042, 36));
        assertEquals("7BE", ToAny.toAny(1982, 16));
        assertEquals("7D4", ToAny.toAny(2004, 16));
        assertEquals("1K0", ToAny.toAny(2016, 36));
        assertEquals("DENIS", ToAny.toAny(22518676, 36));
    }
}
