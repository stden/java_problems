package list;

/**
 * Развернуть односвязный список в другую сторону
 */
class ListExample {
    /**
     * Перевернуть список
     *
     * @param src Корень списка
     * @return перевернутый список
     */
    static Node reverseList(Node src) {
        Node dst = null;
        while (src != null) {
            // Запоминаем первый элемент из исходного списка n
            Node n = src;
            // Двигаемся по исходному списку дальше
            src = src.next;
            // Перевешиваем к перевернутому списку
            n.next = dst;
            dst = n;
        }
        return dst;
    }

    static class Node {
        final int value;
        Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
