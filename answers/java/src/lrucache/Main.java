public class Main {
    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(4);

        for (int i = 0; i < 10; i++) {
            System.out.println(cache);
            cache.put(i, i * 2);
        }
    }
}
