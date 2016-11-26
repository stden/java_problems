import org.junit.Assert;
import org.junit.Test;

public class LookAndSayTest extends Assert {
    @Test
    public void testSimple() {
        assertEquals("11", LookAndSay.next("1"));
        assertEquals("21", LookAndSay.next("11"));
        assertEquals("1211", LookAndSay.next("21"));
        assertEquals("111221", LookAndSay.next("1211"));

        assertEquals("19", LookAndSay.next("9"));
    }

    @Test
    public void testSpeed() {
        // Производительность
        String s = "1";
        for (int i = 0; i < 30; i++) {
            s = LookAndSay.next(s);
        }
    }
}
