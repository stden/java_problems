package alm;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestEquals extends Assert {
    @Test
    public void testStr() throws Exception {
        String s1 = "a";
        String s2 = "a";
        String s3 = new String("a");
        assertTrue(s1 == s2);
        assertFalse(s1 == s3);
        assertTrue(s1.equals(s2));
        assertTrue(s1.equals(s3));
    }

    @Test
    public void testLng() throws Exception {
        Long l1 = Long.valueOf(127);
        Long l2 = Long.valueOf(127);
        Long l3 = new Long(127);
        assertTrue(l1 == l2);  // Should be true due to cache
        assertFalse(l1 == l3); // Should be false as new Long always creates new object
        assertTrue(l1.equals(l2));
        assertTrue(l1.equals(l3));
    }

    @Test
    public void testListSet() throws Exception {
        List<Integer> l = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        l.add(1);
        l.add(1);
        l.add(2);
        s.add(1);
        s.add(1);
        s.add(2);
        assertEquals("[1, 1, 2]", l.toString());
        assertEquals("[1, 2]", s.toString());
    }

    /**
     * 1 2 3 can be shown in any order
     */
    @Test
    public void testThreads() throws Exception {
        Thread t1 = new Thread(() -> System.out.println("1"));
        Thread t2 = new Thread(() -> System.out.println("2"));
        t1.start();
        t2.start();
        System.out.println("3");
    }

}
