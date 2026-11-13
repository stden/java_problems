package java1;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static java.lang.Math.abs;


public class EqTest extends Assert {
    private Random rnd = new Random();

    private static String fileToStr(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.forName("UTF-8")).trim();
    }

    @Test
    public void testSample() {
        assertEquals("NO", Eq.solve(2, 3, 7));
        assertEquals("YES", Eq.solve(0.3, 0.4, 0.7));
        assertTrue(sol(0.3, 0.4, 0.7));
        assertTrue("Иногда нам везёт и округление верное", solWA(0.3, 0.4, 0.7));

        assertTrue(sol(13.9, 16.8, 30.7));
        assertFalse(solWA(13.9, 16.8, 30.7));
    }

    @Test
    public void testBig() {
        for (int t = 1; t <= 20; t++)
            gen(t);
    }

    // Генератор тестов для задачи
    private void gen(int t) {
        // d - digits - количество знаков после '.'
        System.out.printf("Test %02d%n", t);
        int d = 10;
        if (t > 5) d = 100;
        if (t > 8) d = 1000;
        if (t > 10) d = 10000;
        if (t > 12) d = 100000;
        if (t > 15) d = 1000000;
        if (t > 17) d = 10000000;

        long f = 200;
        if (t <= 3)
            f = t * t + 6;
        if (t > 9)
            f = random(10000) * random(d) + 100;

        double a, b, c;
        do {
            long a1 = random(f);
            long b1 = random(f);
            long c1 = a1 + b1;
            a = a1 / (double) d;
            b = b1 / (double) d;
            c = c1 / (double) d;
        } while (solWA(a, b, c) == sol(a, b, c));
        // Тесты с ответом "NO"
        if (t == 1) {
            a = 2;
            b = 3;
            c = 7;
        }
        if ((t > 10) && (t < 13)) {
            c = c + random(100) / 10000.0;
        }
        assertTrue("a = " + a, 0 <= a && a <= 1000);
        assertTrue("b = " + b, 0 <= b && b <= 1000);
        assertTrue("c = " + c, 0 <= c && c <= 1000);

        assertEquals(sol(a, b, c) ? "YES" : "NO", Eq.solve(a, b, c));
    }

    // Решение
    private boolean sol(double a, double b, double c) {
        return abs(a + b - c) < 1e-14;
    }

    // Решение с WrongAnswer
    private boolean solWA(double a, double b, double c) {
        return (a + b) == c;
    }

    // Случайные числа в пределах: 0..bound
    private long random(long bound) {
        return Math.abs(rnd.nextLong()) % (bound + 1);
    }

    @Test
    public void testFiles() throws IOException {
        String dir = "tests/eq";
        String inFileName = String.format("%s/%02d", dir, 1);
        String ansFileName = String.format("%s/%02d.a", dir, 1);
        System.out.printf("%s => %s%n", inFileName, ansFileName);

        // Save input/output
        InputStream saveIn = System.in;
        PrintStream saveOut = System.out;

        System.setIn(new FileInputStream(inFileName));
        ByteArrayOutputStream s = new ByteArrayOutputStream();
        try (PrintStream ps = new PrintStream(s)) {
            System.setOut(ps);
            Eq.main(new String[]{});
        }
        String output = s.toString().trim();
        assertEquals("NO", output);
        assertEquals(fileToStr(ansFileName), output);

        System.setIn(saveIn);
        System.setOut(saveOut);
    }
}
