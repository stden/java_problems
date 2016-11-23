package interview.online;

import org.junit.Assert;
import org.junit.Test;

// Яндекс-online
public class CalcStrTest extends Assert {
    // Есть только "+" и "*"
    // 2*3+23+1*223+10+6+5*2    3.434
    private long calc(String s) {
        // Разделить по знакам "+"
        String[] muls = s.split("\\+");
        // Считаем каждое произведение в отдельности и суммируем их
        long sum = 0;
        for (String mm : muls) {
            String[] m = mm.split("\\*");
            // Получаем каждое число в отдельности
            long mul = 1;
            for (String x : m) {
                // Получаем очередное число и умножаем на него
                mul *= Integer.valueOf(x);
            }
            // Добавляем к общей сумме
            sum += mul;
        }
        // Возвращаем результат
        return sum;
    }

    @Test
    public void testCalc() {
        assertEquals(10, calc("10"));
        assertEquals(5, calc("2+3"));
        assertEquals(2 + 3 * 5, calc("2+3*5"));
        assertEquals(239 + 366, calc("239+366"));
        assertEquals(13 * 2 + 2 * 4 * 5 + 56 * 2, calc("13*2+2*4*5+56*2"));
    }

}
