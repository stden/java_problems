import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


public class LeapYearTests extends Assert {
    private static File inputDir = new File("work/leap/input");
    private static File outputDir = new File("work/leap/output");
    private int test;

    @BeforeClass
    public static void createDirs() {
        System.out.println("Create input directory: " + inputDir.mkdirs());
        System.out.println("Create output directory: " + outputDir.mkdirs());
    }

    @Test
    public void genTests() throws Exception {
        test = 0;
        genTest(2016, 366);
        genTest(2017, 365);
        genTest(1600, 366);
        genTest(2000, 366);
        genTest(1999);
        genTest(2020, 366);

        genTest(2024, 366);
        Random gen = new Random();
        for (int i = 0; i < 20; i++) {
            genTest(1950 + gen.nextInt(100));
        }
    }

    private void genTest(int... A) throws IOException {
        int year = A[0];
        if (A.length > 1)
            assertEquals(A[1], solve(year));
        try (PrintWriter in = new PrintWriter(String.format("%s/input%02d.txt", inputDir, test))) {
            in.println(year);
        }
        try (PrintWriter out = new PrintWriter(String.format("%s/output%02d.txt", outputDir, test))) {
            out.println(solve(year));
        }
        test++;
    }

    private int solve(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0) ? 366 : 365;
    }
}
