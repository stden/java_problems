package regexp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringReplaceAllTest {

    @Test
    public void testReplaceDoubleWords() throws Exception {
        String s = "Goodbye bye bye world world world";
        assertEquals("Goodbye bye world", s.replaceAll("\\b(\\w+)\\b(\\s+(\\1))+", "$1"));
    }


}
