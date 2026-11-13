package algo;

import org.junit.Assert;
import org.junit.Test;

import static java.lang.Math.floor;
import static java.lang.Math.random;

// Поиск k-ой порядковой статистики
// k-ой порядковой статистикой набора элементов линейно упорядоченного множества
// называется такой его элемент, который является k-ым элементом набора в порядке сортировки
public class FindOrderStatisticTest extends Assert {
    private static int selectIterative(int[] array, int n) {
        return iterative(array, 0, array.length - 1, n);
    }

    private static int iterative(int[] a, int l, int r, int k) {
        if (l == r) {
            return a[l];
        }
        for (; ; ) {
            int mid = partition(a, l, r);
            if (k == mid) {
                return a[k];
            } else if (k < mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
    }

    /**
     * In quicksort, we recursively sort both branches, leading to best-case
     * Ω(n log n) time. However, when doing selection, we already know which
     * partition our desired element lies in, since the pivot is in its final
     * sorted position, with all those preceding it in an unsorted order and
     * all those following it in an unsorted order. Therefore, a single
     * recursive call locates the desired element in the correct partition, and
     * we build upon this for quickselect.
     */
    private static int selectRecursive(int[] array, int n) {
        return recursive(array, 0, array.length - 1, n);
    }

    /**
     * @param a Массив
     * @param l Левая граница
     * @param r Правая граница
     * @param k k-ая порядковая статистика
     * @return k-порядковая статистика
     */
    private static int recursive(int[] a, int l, int r, int k) {
        if (l == r) { // If the list contains only one element,
            return a[l]; // return that element
        }
        // выбираем разделяющий элемент
        int mid = partition(a, l, r);
        // The pivot is in its final sorted position
        if (k == mid) {
            return a[k];
        } else if (k < mid) {
            return recursive(a, l, mid - 1, k);
        } else {
            return recursive(a, mid + 1, r, k);
        }
    }

    /**
     * In quicksort, there is a subprocedure called partition that can, in
     * linear time, group a list (ranging from indices l to r) into two
     * parts, those less than a certain element, and those greater than or
     * equal to the element. Here is pseudocode that performs a partition about
     * the element list[pivotIndex]
     */
    private static int partition(int[] a, int l, int r) {
        int mid = randomPivot(l, r);
        int v = a[mid];
        swap(a, mid, r); // move pivot to end
        int storeIdx = l;
        for (int i = l; i < r; i++) {
            if (a[i] < v) {
                swap(a, storeIdx, i);
                storeIdx++;
            }
        }
        swap(a, r, storeIdx); // Move pivot to its final place
        return storeIdx;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static int randomPivot(int l, int r) {
        return l + (int) floor(random() * (r - l + 1));
    }

    @Test
    public void testSimple() {
        int[] a = {9, 8, 7, 6, 5, 0, 1, 2, 3, 4};
        for (int i = 0; i < a.length; i++) {
            assertEquals(i, selectIterative(a, i));
            assertEquals(i, selectRecursive(a, i));
        }
    }
}
