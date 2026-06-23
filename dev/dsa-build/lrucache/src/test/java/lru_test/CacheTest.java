package test;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import lru.Cache;
import hashmap.Hashmap;

public class CacheTest {
    Random random;

    @BeforeEach
    void setUp() {
        random = new Random();
    }

    @Test
    @DisplayName("LRU eviction removes LRU, not LRI")
    void evictsLRUNotLRI() {
        Cache<Integer, Integer> cache = new Cache<>(3);
    
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 999);
        cache.get(1);
        cache.put(4, 4);

        assertNull(cache.get(2));
        assertEquals(cache.get(3), 999);
        assertEquals(cache.get(4), 4);
    }
}   