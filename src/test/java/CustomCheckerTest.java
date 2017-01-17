import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Проверка: CustomChecker для https://www.hackerrank.com
 */
public class CustomCheckerTest extends Assert {
    @Test
    public void floatFormat() {
        assertEquals("3.14", CustomChecker.f2s(3.14));
        assertEquals("2.1234567890123457", CustomChecker.f2s(2.12345678901234567890));
    }

    @Test
    public void testChecker() {
        TestStruct t = new TestStruct();
        String dir = "tests/sqreq";

        for (int test = 1; ; test++) {
            t.testcase_input_path = String.format(dir + "/%02d", test);
            // Если входного файла нет => прерываем тестирование
            if (!new File(t.testcase_input_path).exists())
                break;
            t.testcase_output_path = String.format(dir + "/%02d.a", test);
            t.testcase_expected_output_path = t.testcase_output_path;

            ResultStruct r = new ResultStruct();
            CustomChecker.run_custom_checker(t, r);

            switch (test) {
                case 1:
                    assertEquals("0 = 0  roots: -1", r.message);
                    break;
                case 2:
                    assertEquals("0.2*x^2+0.2*x-0.6 = 0  D = 0.52  roots: 2  -2.3027756377 1.3027756377", r.message);
                    break;
            }
            assertTrue(r.result);
            assertEquals(1, r.score, 1e-10);

            CustomChecker.write_result_json(r);
        }
    }
}
