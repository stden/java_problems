package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegExpMatcher {
    // Число с плавающей точкой
    private static String FLOAT = "((\\d*\\.?\\d+)|(\\d+\\.))";
    // Число с плавающей точкой и знаком спереди
    private static String FLOAT_SIGN = "[-+]?" + FLOAT;

    // 2.3+3.11i   2.5     4.5i
    private static String COMPLEX_NUMBER =
            "((" + FLOAT_SIGN + "[-+])?" + FLOAT + "i)|(" + FLOAT_SIGN + "([-+]" + FLOAT + "i)?)";

    // 2+3i,122i,22
    static String COMPLEX_LIST = "((" + COMPLEX_NUMBER + ")\\,)*" + COMPLEX_NUMBER;

    private static boolean match(String s, String regExp) {
        Pattern p = Pattern.compile("^" + regExp + "$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    static boolean isFloat(String s) {
        return match(s, FLOAT_SIGN);
    }

    static boolean isComplexNumber(String s) {
        return match(s, COMPLEX_NUMBER);
    }

    static boolean isComplexList(String s) {
        return match(s, COMPLEX_LIST);
    }
}
