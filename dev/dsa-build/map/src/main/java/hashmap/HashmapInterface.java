package hashmap;
public interface HashmapInterface<K, V> {
    // int hashFunction(); // These capabilities are private; we do not want outside callers to access these methods
    // int compressionFunction(); // Thus, requiring a private access modifier, these are not specified in the interface
    int size();
    void put(K key, V value);
    V remove(K key);
    V get(K key);
    boolean contains(K key);
}