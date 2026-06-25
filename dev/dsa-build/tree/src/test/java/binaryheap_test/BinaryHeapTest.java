package binaryheap_test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import binaryheap.BinaryHeap;

public class BinaryHeapTest {
    Random random;

    @BeforeEach
    void setUp() {
        random = new Random();
    }

    @Test
    @DisplayName("Heap starts empty")
    void heapMustStartEmpty() {
        BinaryHeap<?> heap = new BinaryHeap<>();
        assertEquals(heap.size(), 0);
    }

    @Test
    @DisplayName("Remove min n times drains heap of n size")
    void removeMinNTimes() {
        int size = random.nextInt(5, 50);
        BinaryHeap<Integer> heap = new BinaryHeap<>();

        for (int i = 0; i < size; i++) {
            heap.put(random.nextInt(1, 100));
        }

        assertEquals(heap.size(), size);

        for (int j = 0; j < size; j++) {
            heap.removeMin();
        }

        assertEquals(heap.size(), 0);
    }

    @Test
    @DisplayName("removeMin() yields sorted list")
    void removeMinYieldsSorted() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        int size = random.nextInt(5, 50);
        Integer[] sortedList = new Integer[size];

        for (int i = 0; i < size; i++) {
            heap.put(random.nextInt(1, 1000));
        }

        for (int j = 0; j < size; j++) {
            sortedList[j] = heap.removeMin();
        }

        boolean isSorted = true;

        for (int k = 0; k < size - 1; k++) {
            if (sortedList[k] > sortedList[k + 1]) { 
                isSorted = false; 
                break;
            }
        }

        assertTrue(isSorted);
    }

    @Test
    @DisplayName("The minimum must sit at the root")
    void minSitsAtHeapRoot() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        int size = random.nextInt(5, 50);
        int[] sortedList = new int[size];

        for (int i = 0; i < size; i++) {
            heap.put(random.nextInt(1, 1000));
        }

        int min = heap.peek();

        for (int j = 0; j < size; j++) {
            sortedList[j] = heap.removeMin();
        }

        assertEquals(min, sortedList[0]);
    }
}