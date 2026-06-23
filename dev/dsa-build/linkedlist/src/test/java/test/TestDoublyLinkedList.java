package test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import doubly.DoublyLinkedList;
import doubly.DoublyNode;
import exceptions.NodeAbsenceException;


public class TestDoublyLinkedList {
    private final class Entry<T> {
        private final T entry;

        public Entry(T entry) {
            this.entry = entry;
        }

        public T get() {
            return entry;
        }
    }

    DoublyLinkedList<Entry<?>> doublyLinked;
    Random random;

    @BeforeEach
    void setUp() {
        doublyLinked = new DoublyLinkedList<>();
        random = new Random();
    }

    @Test
    @DisplayName("Linked list starts empty")
    void linkedListStartsEmpty() {
        assertEquals(doublyLinked.size(), 0, "Case 1 failed");
    }

    @Test
    @DisplayName("Head and trailer are null")
    void headAndTrailerAreNull() {
        assertTrue(doublyLinked.getFirst().data() == null, "Case 1 failed");
        assertTrue(doublyLinked.getLast().data() == null, "Case 2 failed");
    }

    @Test
    @DisplayName("Insertion at header updates head and size")
    void insertAtHeadNextIsNotNull() {
        doublyLinked.insertAtHead(new Entry<>("A"));
        assertTrue(doublyLinked.getFirst().data().get().equals("A"), "Case 1 failed");
        assertEquals(doublyLinked.size(), 1, "Case 2 failed");
    }

    @Test
    @DisplayName("Insertion at trailer is non-null")
    void insertAtTrailerPrevIsNotNull() {
        doublyLinked.insertAtTrail(new Entry<>(1));
        assertTrue(doublyLinked.getLast().data().get().equals(1));
        assertEquals(doublyLinked.size(), 1);
    }

    @Test
    @DisplayName("Produce an arbitrarily long linked list")
    void createArbitrarilyLongLinkedList() {
        int size = random.nextInt(9990) + 10;
        Entry<?>[] entryList = new Entry<?>[size];

        for (int i = 0; i < size; i++) {
            entryList[i] = new Entry<>(i);
            doublyLinked.insertAtTrail(entryList[i]);
        }

        int randomIndex = random.nextInt(size);

        assertTrue(doublyLinked.contains(entryList[randomIndex]));
        assertEquals(doublyLinked.get(entryList[randomIndex]).data().get(), entryList[randomIndex].get());
        assertEquals(doublyLinked.getFirst().data().get(), entryList[0].get());
        assertEquals(doublyLinked.getLast().data().get(), entryList[size - 1].get());
        assertEquals(doublyLinked.size(), size);
    }

    @Test
    @DisplayName("List removal logic")
    void listRemovalRemovesNode() {
        Entry<Integer> entry = new Entry<>(random.nextInt(999) + 1);

        doublyLinked.insertAtHead(entry);

        assertEquals(doublyLinked.size(), 1, "Case 1 failed");
        assertTrue(doublyLinked.getFirst().data().get().equals(entry.get()), "CAse 2 failed");

        doublyLinked.remove(entry);

        assertEquals(doublyLinked.size(), 0, "Case 3 failed");
        assertTrue(doublyLinked.getFirst().data() == null, "Case 4 failed");
    }

    @Test
    @DisplayName("Throws exception on get() on non-existent node")
    void throwsOnGetNull() {
        doublyLinked.insertAtHead(new Entry<>(random.nextInt(899) + 101));

        assertThrows(NodeAbsenceException.class, () -> {
            doublyLinked.get(new Entry<>(random.nextInt(99) + 1));
        }, "Case 1 failed");
    }

    @Test
    @DisplayName("Removal given a node removes")
    void removeOnNode() {
        DoublyNode<Entry<?>> nodeEntry = doublyLinked.insertAtHead(new Entry<>(random.nextInt(1, 1000)));

        assertEquals(doublyLinked.size(), 1);

        doublyLinked.removeNode(nodeEntry);

        assertEquals(doublyLinked.size(), 0);
    }

    @Test
    @DisplayName("Detatch node and move to head")
    @SuppressWarnings("unchecked")
    void nodeDetachesAndBecomesHead() {
        int size = random.nextInt(9990) + 10;
        DoublyNode<Entry<?>>[] entryNodes = new DoublyNode[size];

        for (int i = 0; i < size; i++) {
            entryNodes[i] = doublyLinked.insertAtHead(new Entry<>(i));
        }

        int randomIndex = random.nextInt(0, size - 1);

        DoublyNode<Entry<?>> prevNode = doublyLinked.getFirst();
        DoublyNode<Entry<?>> thisNode = entryNodes[randomIndex];

        doublyLinked.moveToFront(thisNode);

        assertNotEquals(doublyLinked.getFirst(), prevNode);
        assertEquals(doublyLinked.getFirst(), thisNode);
    }
}