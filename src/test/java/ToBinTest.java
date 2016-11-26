import org.junit.Assert;
import org.junit.Test;

public class ToBinTest extends Assert {

    @Test
    public void testBin() {
        assertEquals("0", ToBin.toBin(0));
        assertEquals("1", ToBin.toBin(1));
        assertEquals("10", ToBin.toBin(2));
        assertEquals("11", ToBin.toBin(3));
        assertEquals("1010", ToBin.toBin(10));
        assertEquals("1011", ToBin.toBin(11));
        assertEquals("1100", ToBin.toBin(12));
        assertEquals("1101", ToBin.toBin(13));
        assertEquals("1110", ToBin.toBin(14));
        assertEquals("1111", ToBin.toBin(15));
        assertEquals("1111100111", ToBin.toBin(999));
        assertEquals("11111010100", ToBin.toBin(2004));
        assertEquals("11111100000", ToBin.toBin(2016));
        assertEquals("11111100001", ToBin.toBin(2017));
        assertEquals("111010110111100110100010101", ToBin.toBin(123456789));
        assertEquals("100000000000000000000000000000000", ToBin.toBin(1L << 32));
        assertEquals("100000000000000000000000000000000000000000000000000",
                ToBin.toBin(1L << 50));
        assertEquals("10110101", ToBin.toBin(0b10110101));
    }
}
