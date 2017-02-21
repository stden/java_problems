package merge;

import org.junit.Test;

import java.util.List;

import static merge.MergeListsProblem.merge;
import static merge.MergeListsProblem.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MergeListsProblemTest {
    private static final double EPS = 1e-10;

    @Test
    public void testMergeTwoLists() throws Exception {
        List<Item> list1 = toList(new Item(4, 1.0), new Item(2, 2.0), new Item(3, 4.5));
        assertSortedByWeight(list1);
        List<Item> list2 = toList(new Item(2, 2.5), new Item(3, 4.5));
        assertSortedByWeight(list2);
        List<Item> list3 = merge(list1, list2);
        assertSortedByWeight(list3);
        assertEquals(4, list3.get(0).ID);
        assertEquals(1.0, list3.get(0).weight, EPS);
        assertEquals(2, list3.get(1).ID);
        assertEquals(4.5, list3.get(1).weight, EPS);
    }

    private void assertSortedByWeight(List<Item> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i).weight <= list.get(i + 1).weight);
        }
    }
}
