package list;

import list.ListExample.Node;
import org.junit.Assert;
import org.junit.Test;

import static list.ListExample.reverseList;

/**
 * Развернуть односвязный список в другую сторону
 */
public class ReverseListTest extends Assert {
    @Test
    public void test0() {
        assertNull(reverseList(null));
    }

    @Test
    public void test1() {
        Node root = new Node(1, null);
        Node result = reverseList(root);
        assertEquals(1, result.value);
        assertNull(result.next);
    }

    @Test
    public void test2() {
        Node root = new Node(1, new Node(2, null));
        Node result = reverseList(root);
        assertEquals(2, result.value);
        assertEquals(1, result.next.value);
        assertNull(result.next.next);
    }

    @Test
    public void test3() {
        Node root = new Node(1, new Node(2, new Node(3, null)));
        Node result = reverseList(root);
        assertEquals(3, result.value);
        assertEquals(2, result.next.value);
        assertEquals(1, result.next.next.value);
        assertNull(result.next.next.next);
    }
}
