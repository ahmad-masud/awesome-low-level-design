public class DoublyLinkedList<K, V> {
    private final Node<K, V> head;
    private final Node<K, V> tail;

    public DoublyLinkedList() {
        head = new Node<K, V>(null, null);
        tail = new Node<K, V>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(Node<K, V> node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public void remove(Node<K, V> node) {
        Node<K, V> prev = node.prev;
        Node<K, V> next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    public Node<K, V> removeLast(Node<K, V> node) {
        if (head.next == tail) {
            return null;
        }

        Node<K, V> last = tail.prev;
        remove(last);
        return last;
    }

    public void moveToFront(Node<K, V> node) {
        remove(node);
        addFirst(node);
    }
}
