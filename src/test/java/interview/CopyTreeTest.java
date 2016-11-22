package interview;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CopyTreeTest extends Assert {
    private TreeCopy rec, it;

    @Before
    public void setUp() {
        rec = new RecurCopy();
        it = new ItCopy();
    }

    @Test
    public void testNull() {
        assertNull(rec.copy(null));
        assertNull(it.copy(null));
    }

    /**
     * Один узел указываюший, обоими концами сам на себя
     */
    @Test
    public void testOneNodeCycled() {
        checkOneCycle(rec);
        checkOneCycle(it);
    }

    @Test
    public void testComplexTree() {
        checkComplexTree(rec);
        checkOneCycle(it);
    }

    private void checkComplexTree(TreeCopy c) {
        Node n = new Node(1,
                new Node(2),
                new Node(3,
                        new Node(5, null, new Node(6)),
                        null
                )
        );
        ZNode zn = c.copy(n);
        assertEquals(1, zn.value);
        assertEquals(2, zn.left.value);
        assertEquals(3, zn.right.value);

        assertNull(zn.left.left);
        assertNull(zn.left.right);

        assertEquals(5, zn.right.left.value);
        assertNull(zn.right.right);

        assertNull(zn.right.left.left);
        assertEquals(6, zn.right.left.right.value);
    }

    private void checkOneCycle(TreeCopy c) {
        Node n = new Node(5);
        n.left = n;
        n.right = n;

        ZNode zn = c.copy(n);
        assertEquals(n.value, zn.value);
        assertEquals(zn, zn.left);
        assertEquals(zn, zn.right);
    }

    interface TreeCopy {
        ZNode copy(Node node);
    }

    private static class RecurCopy implements TreeCopy {
        public ZNode copy(Node node) {
            if (node == null)
                return null;
            return copyX(node, new HashMap<>());
        }

        private ZNode copyX(Node node, Map<Node, ZNode> m) {
            if (node == null)
                return null;
            if (m.containsKey(node))
                return m.get(node);

            ZNode zn = new ZNode();
            m.put(node, zn); // Добавляем созданный узел
            zn.value = node.value;
            zn.left = copyX(node.left, m);
            zn.right = copyX(node.right, m);
            return zn;
        }
    }

    private static class ItCopy implements TreeCopy {
        public ZNode copy(Node node) {
            if (node == null)
                return null;
            Map<Node, ZNode> m = new HashMap<>();
            Stack<Node> s = new Stack<>();
            Node cur = node; // Текущий узел
            while (!m.containsKey(cur)) {
                ZNode zn = new ZNode();
                m.put(cur, zn);
                zn.value = cur.value;
                if (cur.left != null) {
                    if (cur.right != null && !m.containsKey(cur.right)) {
                        s.push(cur.right); // Правое поддерево для обхода
                    }
                    cur = cur.left;
                    continue;
                } else {
                    if (cur.right != null) {
                        cur = cur.right;
                        continue;
                    }
                }
                if (!s.isEmpty()) {
                    cur = s.pop();
                } else {
                    break;
                }
            }
            // Восстанавливаем ссылки
            for (Node n : m.keySet()) {
                ZNode zn = m.get(n);
                zn.left = m.get(n.left);
                zn.right = m.get(n.right);
            }
            return m.get(node);
        }
    }

    private static class ZNode {
        int value;
        ZNode left;
        ZNode right;
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
