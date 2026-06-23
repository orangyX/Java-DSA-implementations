package hashmap_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import hashmap.Hashmap;
// import hashmap.KeyException;

import java.util.Random;
import java.lang.StringBuilder;

public class HashmapTest {
    private class Key<K> {
        K key;

        Key(K key) {
            this.key = key;
        }

        public K key() {
            return key;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object other) {
            if (this == other) { return true; }
            else if (other == null || getClass() != other.getClass()) { return false; }

            K castedOther = (K) other;

            return key.equals(castedOther);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }

    private class Value<V> {
        V value;

        Value(V value) {
            this.value = value;
        }

        public V value() {
            return value;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object other) {
            if (this == other) { return true; }
            else if (other == null || getClass() != other.getClass()) { return false; }

            V castedOther = (V) other;

            return castedOther.equals(value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }

    Hashmap<Key, Value> hashmap;
    Random random;

    @BeforeEach
    void setUp() {
        hashmap = new Hashmap<>();
        random = new Random();
    }

    @Test
    @DisplayName("Hash map starts empty")
    void hashmapStartsEmpty() {
        assertEquals(hashmap.size(), 0, "Case 1 failed");
    }

    @Test
    @DisplayName("First push succeeds")
    void firstPushSucceeds() {
        Key k = new Key<>(0);
        Value v = new Value<>("A");

        hashmap.put(k, v);

        assertTrue(hashmap.contains(k), "Case 1 failed");
        assertEquals(hashmap.get(k), v, "Case 2 failed");
    }

    @Test
    @DisplayName("null on non-existent key")
    void nullOnNonExistentKey() {
        assertEquals(hashmap.get(new Key<>(random.nextInt(10000))), null);
    }

    @Test
    @DisplayName("Hashmap expands in size")
    void hashmapExpandsInSize() {
        for (int i = 0; i < 11; i++) {
            hashmap.put(new Key<>(i), new Value<>(random.nextInt(1, 10000)));
        }

        assertTrue(hashmap.getCapacity() > 10, "Case 1 failed");
        assertEquals(hashmap.getCapacity(), 20, "Case 2 failed");
    }

    // @Test
    // @DisplayName("Hashmap throws on get() for invalid key")
    // void throwsOnInvalidKey() {
    //     assertThrows(KeyException.class, () -> {
    //         hashmap.get(new Key<>(1));
    //     }, "Case 1 failed");
    // }

    @Test
    @DisplayName("Many pushes does not break map")
    void manyPushesBreaksMap() {
        int numEntries = random.nextInt(10, 10000);
        Key[] keys = new Key[numEntries];
        Value[] values = new Value[numEntries];
        
        for (int i = 0; i < numEntries; i++) {
            int strLength = random.nextInt(1, 8);
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < strLength; j++) {
                char charChoice = (char) ('A' + random.nextInt(26));
                sb.append(charChoice);
            }

            String str = sb.toString() + i;

            keys[i] = new Key<>(str);
            values[i] = new Value<>(random.nextInt(100000));

            hashmap.put(keys[i], values[i]);
        }

        int randomIndex = random.nextInt(numEntries);
        Key randomKey = keys[randomIndex];
        Value randomValue = values[randomIndex];
        
        assertTrue(hashmap.contains(randomKey), "Case 1 failed");
        assertTrue(hashmap.contains(keys[random.nextInt(numEntries)]), "Case 2 failed");
        assertEquals(hashmap.get(keys[randomIndex]), values[randomIndex], "Case 3 failed");
        assertEquals(hashmap.size(), numEntries, "Case 4 failed");
    }
}
