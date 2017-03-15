import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class SumArrayGenTests extends Assert {
    private static File inputDir = new File("work/sumarray/input");
    private static File outputDir = new File("work/sumarray/output");
    Random gen = new Random();
    private int test;

    @BeforeClass
    public static void createDirs() {
        System.out.println("Create input directory: " + inputDir.mkdirs());
        System.out.println("Create output directory: " + outputDir.mkdirs());
    }

    @Test
    public void genTests() throws Exception {
        test = 0;
        genTest(5, 3, 2, 9, 2, 3);

        for (int test = 2; test < 20; test++) {
            int[] a = new int[test * test + 10];
            for (int i = 0; i < a.length; i++) {
                a[i] = gen.nextInt(test * test + 100);
            }
            genTest(a);
        }
    }

    private void genTest(int... array) throws IOException {
        try (PrintWriter in = new PrintWriter(String.format("%s/input%02d.txt", inputDir, test))) {
            in.println(array.length);
            for (int x : array) {
                in.print(x + " ");
            }
            in.println();
        }
        try (PrintWriter out = new PrintWriter(String.format("%s/output%02d.txt", outputDir, test))) {
            out.println(sumArray(array));
        }
        test++;
    }

    private int sumArray(int[] array) {
        int sum = 0;
        for (int x : array) {
            sum += x;
        }
        return sum;
    }
}
