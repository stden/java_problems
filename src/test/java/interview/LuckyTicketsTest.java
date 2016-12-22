package interview;

import org.junit.Assert;
import org.junit.Test;

/**
 * Проверка билета на счастливость :)
 */
public class LuckyTicketsTest extends Assert {

    /**
     * Позитивные тесты
     */
    @Test
    public void testPositive() {
        assertTrue(isMyTicketLucky("123213"));
        assertTrue(isMyTicketLucky("123321"));
    }

    @Test
    public void testNegative() {
        assertFalse(isMyTicketLucky("123223"));
        assertFalse(isMyTicketLucky("34"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionForEmptyString() {
        isMyTicketLucky("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionForString() {
        isMyTicketLucky("1");
    }

    /**
     * Является ли билет счастливым? Длина номера билета должна быть чётной
     *
     * @param number номер билета
     * @return true - если является
     */
    private boolean isMyTicketLucky(String number) {
        // Если строка пустая или длина нечётна
        if (number.isEmpty() || (number.length() % 2 != 0))
            throw new IllegalArgumentException("Номер не должен быть пустым и длина номера должна быть чётна " + number);
        int n = number.length() / 2; // Половина длины номера
        // Суммы цифр половинок номера
        int left = 0; // Сумма цифр левой половины
        int right = 0; // Сумма цифр правой половины
        for (int i = 0; i < n; i++) {
            left += number.charAt(i);
            right += number.charAt(i + n);
        }
        // Сравниваем половинки
        return left == right;
    }
}
