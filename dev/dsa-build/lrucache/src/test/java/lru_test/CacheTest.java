package lru_test;

import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lru.Cache;

public class CacheTest {
    Random random;
    Cache<Key<?>, Value<?>> cache;

    private class Key<K> {
        private K key;

        Key(K key) { this.key = key; }

        @Override
        public boolean equals(Object other) {
            if (this == other) { return true; }
            else if (other == null || getClass() != other.getClass()) { return false; }
            Key<?> otherKey = (Key<?>) other;
            return Objects.equals(key, otherKey);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key);
        }
    }

    private class Value<V> {
        private V value;

        Value(V value) { this.value = value; }
    }

    @BeforeEach
    void setUp() {
        random = new Random();
        cache = new Cache<>(5);
    }

    @Test
    @DisplayName("Cache starts empty")
    void cacheStartsEmpty() {
        assertEquals(cache.size(), 0);
    }

    @Test
    @DisplayName("Get moves to the front")
    void getMovesNodeToFront() {
        Key<?> firstKey = new Key<>(0);
        Value<?> firstValue = new Value<>(random.nextInt(1, 1000));

        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                cache.put(firstKey, firstValue);
            }
            else {
                cache.put(new Key<>(i), new Value<>(random.nextInt(1, 1000)));                
            }
        }
        cache.get(firstKey);

        for (int j = 0; j < 4; j++) {
            cache.put(new Key<>(j + 5), new Value<>(random.nextInt(1, 1000)));
        }

        assertNotNull(cache.get(firstKey));
    }

    @Test
    @DisplayName("LRU eviction removes LRU, not LRI")
    void evictsLRUNotLRI() {
        Key<?>[] keyMap = new Key[6];
        Value<?>[] valueMap = new Value[6];

        keyMap[0] = new Key<>(1);
        keyMap[1] = new Key<>(2);
        keyMap[2] = new Key<>(3);
        keyMap[3] = new Key<>(4);
        keyMap[4] = new Key<>(5);
        keyMap[5] = new Key<>(6);

        valueMap[0] = new Value<>("A");
        valueMap[1] = new Value<>("B");
        valueMap[2] = new Value<>("C");
        valueMap[3] = new Value<>("D");
        valueMap[4] = new Value<>("E");
        valueMap[5] = new Value<>("F");

        cache.put(keyMap[0], valueMap[0]);
        cache.put(keyMap[1], valueMap[1]);
        cache.put(keyMap[2], valueMap[2]);
        cache.put(keyMap[3], valueMap[3]);
        cache.put(keyMap[4], valueMap[4]);
        cache.get(keyMap[0]);
        cache.put(keyMap[5], valueMap[5]);

        assertEquals(cache.get(keyMap[0]), valueMap[0]);
        assertTrue(cache.get(keyMap[1]) == null);

        cache.put(new Key<>(7), new Value<>("G"));

        assertTrue(cache.get(keyMap[2]) == null);
    }
}   