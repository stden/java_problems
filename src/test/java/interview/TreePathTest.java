package interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TreePathTest {
    // Найти путь от вершины a до вершины b
    List<Node> path(Node a, Node b) {
        List<Node> list = new ArrayList<>();
        Set<Node> ccc = new TreeSet<>();
        // Поднимаемся от каждой из вершин, ищем общего предка

        return list;
    }

    @Test
    public void testTree() {
        // Пример дерева
    }

    // Узел дерева
    static class Node {
        Node parent;
        Node left;
        Node right;
    }
}
