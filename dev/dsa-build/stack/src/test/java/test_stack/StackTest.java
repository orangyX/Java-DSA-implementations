package test_stack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import stack.Stack;
import stack.StackEmptyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import java.util.Random;

    public class StackTest {
        public static final class Item {
            final int id;
            public Item(int id) {
                this.id = id;
            }
        }

        private Stack<Item> stack;
        private Random random;

        @BeforeEach
        void setUp() {
            stack = new Stack<>();
            random = new Random();
        }

        @Test
        @DisplayName("Stack starts empty")
        void stackStartsEmpty() {
            assertEquals(0, stack.size());
            assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("Push expands the stack")
        void stackIncreasesInSize() {
            for (int i = 0; i < 11; i++) {
                stack.push(new Item(random.nextInt(1, 100)));
            }

            assertEquals(stack.size(), 11);
            assertFalse(stack.isEmpty());
        }

        @Test
        @DisplayName("Popped stack is empty")
        void poppedStackIsEmpty() {
            for (int i = 0; i < 11; i++) {
                stack.push(new Item(random.nextInt(1, 100)));
            }

            for (int i = 0; i < 11; i++) {
                stack.pop();
            }

            assertEquals(0, stack.size());
            assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("Stack shrinks in size")
        void stackShrinksUponMany() {
            for (int i = 0; i < 1000; i++) {
                stack.push(new Item(random.nextInt(1, 1000)));
            }

            assertTrue(stack.getCapacity() > 999);

            for (int i = 0; i < 990; i++) {
                stack.pop();
            }

            assertTrue(stack.getCapacity() < 25);
        }

        @Test
        @DisplayName("Pop from an empty stack throws exception")
        void popFromEmptyStackThrowsException() {
            assertThrows(StackEmptyException.class, () -> stack.pop());
        }

        @Test
        @DisplayName("Peek on an empty stack throws exception")
        void peekOnEmptyThrowsException() {
            assertThrows(StackEmptyException.class, () -> stack.peek());
        }
    }