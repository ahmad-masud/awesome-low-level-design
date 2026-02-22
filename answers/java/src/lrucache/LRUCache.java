import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private int cap;
    private Map<K, Node<K, V>> map;
    private DoublyLinkedList<K, V> list;

    public LRUCache(int cap) {
        this.cap = cap;
        map = new HashMap<>();
        list = new DoublyLinkedList<>();
    }

    public synchronized V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }

        Node<K, V> node = map.get(key);
        list.moveToFront(node);
        return node.val;
    }

    public synchronized void remove(K key) {
        if (!map.containsKey(key)) {
            return;
        }

        Node<K, V> node = map.get(key);
        list.remove(node);
        map.remove(key);
    }

    public synchronized void put(K key, V val) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            node.val = val;
            list.moveToFront(node);
        } else {
            if (map.size() == cap) {
                Node<K, V> lastNode = list.removeLast();
                if (lastNode != null) { 
                    map.remove(lastNode.key);
                }
            }

            Node<K, V> newNode = new Node<>(key, val);
            list.addFirst(newNode);
            map.put(key, newNode);
        }
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
