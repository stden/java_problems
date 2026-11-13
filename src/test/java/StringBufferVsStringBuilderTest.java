import org.junit.*;
import org.junit.rules.TestName;

/**
 * Разобраться почему StringBuilder быстрее чем StringBuffer?
 * ----------------------------------------------------------
 * Методы StringBuilder не синхронизованы
 * Очень важное и недооцениваемое следствие из этого - в однопоточном использовании
 * (например, при работе с локальными переменными)
 * StringBuilder практически всегда в 1.2-1.5 раза быстрее, чем StringBuffer.
 */
public class StringBufferVsStringBuilderTest extends Assert {
    private static final int ITERATIONS = 4000000;
    private static String sBuilder;
    private static String sBuffer;
    @Rule
    public TestName name = new TestName();
    private long start;

    @AfterClass
    public static void afterClass() {
        if (sBuffer == null) return;
        if (sBuilder == null) return;
        assertEquals(sBuffer, sBuilder);
    }

    @Test
    public void testStringBuffer() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < ITERATIONS; i++) {
            buffer.append(i);
        }
        sBuffer = buffer.toString();
        assertNotNull(sBuffer);
    }

    @Test
    public void testStringBuilder() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ITERATIONS; i++) {
            builder.append(i);
        }
        sBuilder = builder.toString();
        assertNotNull(sBuilder);
    }

    @Before
    public void start() {
        start = System.currentTimeMillis();
    }

    @After
    public void end() {
        System.out.println("Test " + name.getMethodName() + " -> " + (System.currentTimeMillis() - start) + " ms");
    }
}
