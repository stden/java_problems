import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import static java.lang.Double.NaN;
import static java.lang.Double.POSITIVE_INFINITY;
import static java1.LinEq.solve;

public class LinEqTest extends Assert {
    private static File inputDir = new File("work/lineq/input");
    private static File outputDir = new File("work/lineq/output");
    private int test;

    @BeforeClass
    public static void createDirs() {
        System.out.println("Create input directory: " + inputDir.mkdirs());
        System.out.println("Create output directory: " + outputDir.mkdirs());
    }

    /**
     * Варианты вывода:
     * - **x - any** - если подходит любое значение x, например $0x + 0 = 0$
     * - **No solutions** - если ни одно значение x не подходит, например $0x + 1 = 0$
     * - **x = число** - решение уравнения
     * - **Incorrect input** - если уравнение невозможно решить, например $\infty{x} + \infty = 0$
     */
    @Test
    public void genTests() throws Exception {
        test = 0;
        genTest(0, 0, "x - any");
        genTest(0, 1, "No solutions");
        genTest(POSITIVE_INFINITY, 0, "Incorrect input");
        genTest(1, 1, "x = -1.0");
        genTest(7, 1.5, "x = -0.21428571428571427");
        genTest(POSITIVE_INFINITY, POSITIVE_INFINITY, "Incorrect input");
        genTest(NaN, NaN, "Incorrect input");

        Random gen = new Random();
        for (int i = 0; i < 5; i++) {
            genTest(gen.nextInt(10 + i * i), gen.nextInt(10 + i * i), null);
        }
        for (int i = 0; i < 10; i++) {
            double size = 2 << i;
            genTest(gen.nextDouble() * size, gen.nextDouble() * size, null);
        }
    }

    private void genTest(double a, double b, String expected) throws IOException {
        if (expected != null)
            assertEquals(expected, solve(a, b));

        try (PrintWriter in = new PrintWriter(String.format("%s/input%02d.txt", inputDir, test))) {
            in.println(a + " " + b);
        }
        try (PrintWriter out = new PrintWriter(String.format("%s/output%02d.txt", outputDir, test))) {
            out.println(solve(a, b));
        }
        test++;
    }
}
