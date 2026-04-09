package PracLes4;

import java.util.LinkedHashMap;
import java.util.Map;

class SimpleCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public SimpleCache(int capacity) {
        // accessOrder = true — порядок по доступу (LRU)
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public V put(K key, V value) {
        return super.put(key, value);
    }

    public V get(Object key) {
        return super.get(key);
    }

    public boolean containsKey(Object key) {
        return super.containsKey(key);
    }

    public int size() {
        return super.size();
    }

    public void clear() {
        super.clear();
    }
}

public class CacheDemo {
    public static void main(String[] args) {
        SimpleCache<String, Integer> cache = new SimpleCache<>(3);

        System.out.println("Добавляем a=1, b=2, c=3:");
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        printCacheState(cache);

        System.out.println("\nДобавляем d=4 (вытеснение наименее используемого):");
        cache.put("d", 4);
        printCacheState(cache);

        System.out.println("\nПроверяем get(\"a\"): " + cache.get("a"));
        System.out.println("containsKey(\"a\"): " + cache.containsKey("a"));
    }

    private static void printCacheState(SimpleCache<String, Integer> cache) {
        System.out.println("Размер кэша: " + cache.size());
        System.out.print("Содержимое: ");
        cache.forEach((k, v) -> System.out.print(k + "=" + v + " "));
        System.out.println();
    }
}
