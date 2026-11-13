import org.junit.Assert;
import org.junit.Test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerTest extends Assert {
    @Test
    public void testParseInts() throws Exception {
        Scanner scan = new Scanner("1 2 test 3");
        assertEquals(1, scan.nextInt());
        assertEquals(2, scan.nextInt());
        try {
            scan.nextInt();
            fail("Должно быть исключение т.к. test - не число");
        } catch (InputMismatchException e) {
            assertNull(e.getMessage());
            assertEquals("test", scan.next());
        }
        assertEquals(3, scan.nextInt());
    }
}
