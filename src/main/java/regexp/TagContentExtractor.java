package regexp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagContentExtractor {
    public static final String REGEX = "<([^>]+)>([^<]+)</(\\1)>";
    private static Pattern pattern = Pattern.compile(REGEX);

    public static List<String> extract(String s) {
        List<String> list = new ArrayList<>();
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            list.add(matcher.group(2));
        }
        return list;
    }
}
