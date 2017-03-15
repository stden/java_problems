package tf;

public class ListNodeTest {
    static int findMiddle(ListNode root) {
        // Calculate number of elements
        int count = 0;
        ListNode cur = root;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        // Find middle
        return count / 2;
    }

    static ListNode findMiddle2(ListNode root) {
        // Calculate number of elements
        int count = 0;
        ListNode cur = root;
        ListNode middle = root;
        while (cur != null) {
            count++;
            cur = cur.next;
            if (count % 2 == 0)
                middle = middle.next;
        }
        return middle;
    }

    static class ListNode {
        ListNode next;
        Object value;
    }


}
