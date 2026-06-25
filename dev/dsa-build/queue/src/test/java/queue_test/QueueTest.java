package queue_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import queue.EmptyQueueException;
import queue.Queue;

public class QueueTest {
    public static class Item<T> {
        T item;

        public Item(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }
    }

    @Test
    @DisplayName("Queue starts empty")
    void queueStartsEmpty() {        
        Queue<String> q = new Queue<>();

        assertEquals(q.size(), 0);
        assertTrue(q.isEmpty());
    }

    @Test
    @DisplayName("Queue accepts to capacity")
    void queueAcceptsToCapacity() {
        Queue<Item<Integer>> q = new Queue<>();

        for (int i = 0; i < 10; i++) {
            q.enqueue(new Item<>(i));
        }

        assertEquals(q.size(), 10);
        assertFalse(q.isEmpty());
    }

    @Test
    @DisplayName("Queue increases in size")
    void queueIncreasesInSize() {
        Queue<Item<Integer>> q = new Queue<>();

        for (int i = 0; i < 11; i++) {
            q.enqueue(new Item<>(i));
        }

        assertTrue(q.size() > 10);
    }

    @Test
    @DisplayName("Dequeue from empty throws exception")
    void dequeueFromEmptyThrowsException() {
        Queue<Item<Integer>> q = new Queue<>();

        assertThrows(EmptyQueueException.class, () -> { 
            q.dequeue();
        });
    }

    @Test
    @DisplayName("Front from empty queue throws exception")
    void frontFromEmptyQueueThrowsException() {
        Queue<Item<Integer>> q = new Queue<>();

        assertThrows(EmptyQueueException.class, () -> {
            q.front();
        });
    }

    @Test
    @DisplayName("Queue shrinks in size")
    void queueShrinksInSize() {
        Queue<Item<Integer>> q = new Queue<>();

        for (int i = 0; i < 1000; i++) {
            q.enqueue(new Item<>(i));
        }

        for (int i = 0; i < 990; i++) {
            q.dequeue();
        }

        assertTrue(q.getCapacity() == 20);
    }
}