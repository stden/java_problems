import org.junit.Assert;
import org.junit.Test;

public class ToHexTest extends Assert {

    @Test
    public void testHex() {
        assertEquals("0", ToHex.toHex(0));
        assertEquals("1", ToHex.toHex(1));
        assertEquals("2", ToHex.toHex(2));
        assertEquals("3", ToHex.toHex(3));
        assertEquals("A", ToHex.toHex(10));
        assertEquals("B", ToHex.toHex(11));
        assertEquals("C", ToHex.toHex(12));
        assertEquals("D", ToHex.toHex(13));
        assertEquals("E", ToHex.toHex(14));
        assertEquals("F", ToHex.toHex(15));
        assertEquals("3E7", ToHex.toHex(999));
        assertEquals("7D4", ToHex.toHex(2004));
        assertEquals("7E0", ToHex.toHex(2016));
        assertEquals("75BCD15", ToHex.toHex(123456789));
        assertEquals("100000000", ToHex.toHex(1L << 32));
        assertEquals("4000000000000", ToHex.toHex(1L << 50));
    }
}
