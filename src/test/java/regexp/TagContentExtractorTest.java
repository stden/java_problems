package regexp;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static regexp.TagContentExtractor.extract;

public class TagContentExtractorTest {

    @Test
    public void testExtract() throws Exception {
        checkArray("<h1>Test</h1>", "Test");
        checkArray("<h1><h1>Test</h1></h1>", "Test");
        checkArray("<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>",
                "Sanjay has no watch", "So wait for a while");
    }

    private void checkArray(String html, String... expected) {
        List<String> list = extract(html);
        String[] array = list.toArray(new String[0]);
        assertArrayEquals(expected, array);
    }


}
