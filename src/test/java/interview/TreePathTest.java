package interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TreePathTest extends Assert {
    // Найти путь от вершины a до вершины b в бинарном дереве
    private List<Node> path(Node a, Node b) {
        // Ищем
        // Поднимаемся от первой вершины и помещаем всех до корня в set
        Set<Node> aa = new HashSet<>();
        Node cur = a;
        while (cur != null) {
            aa.add(cur); // Добавляем очередную вершину
            cur = cur.parent; // Идём к её предку
        }
        // Поднимаемся от b - ищем общего предка
        Node ca = b;
        Stack<Node> s = new Stack<>();
        while (!aa.contains(ca)) {
            s.push(ca);
            ca = ca.parent;
        }
        // ca - предок a и b - общий предок
        // Строим путь
        List<Node> list = new ArrayList<>();
        cur = a;
        while (cur != ca) {
            list.add(cur);
            cur = cur.parent;
        }
        list.add(ca);
        while (!s.isEmpty()) {
            list.add(s.pop());
        }

        return list;
    }

    @Test
    public void testTree() {
        // Пример дерева
        //       a
        //     /  \
        //    b   c
        //   /     \
        //  d      e
        // / \
        // f  g
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        Node d = new Node("d", f, g);
        Node b = new Node("b", d, null);
        Node c = new Node("c", null, e);
        Node a = new Node("a", b, c);

        checkPath("dbac", path(d, c));
        checkPath("fdbace", path(f, e));
        checkPath("a", path(a, a));
        checkPath("f", path(f, f));
        checkPath("db", path(d, b));
        checkPath("ce", path(c, e));
    }

    private void checkPath(String expected, List<Node> list) {
        assertEquals(expected.length(), list.size());
        for (int i = 0; i < expected.length(); i++) {
            assertEquals(String.format("Expected %s at %d", expected.charAt(i), i),
                    String.format("%s", expected.charAt(i)), list.get(i).name);
        }
    }

    // Узел дерева
    private static class Node {
        Node parent;
        Node left;
        Node right;
        private String name;

        Node(String name) {
            this(name, null, null);
        }

        Node(String name, Node left, Node right) {
            this.name = name;
            this.left = left;
            this.right = right;
            // Указываем родителя для дочерних узлов
            if (left != null)
                left.parent = this;
            if (right != null)
                right.parent = this;
        }
    }
}
