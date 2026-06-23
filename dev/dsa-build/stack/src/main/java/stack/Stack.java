package stack;
public final class Stack<T> implements StackOperations<T> {
    private final int INITIAL = 10;
    private int capacity = INITIAL;
    @SuppressWarnings("unchecked")
    private T[] stack = (T[]) new Object[capacity];
    private int size = 0;

    /**
     * Denotes num. elements resident within the stack
     * @return size - size == 0 denotes empty
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Denotes stack emptiness
     * @return boolean - true indicates empty, false indicates non-empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Push an item onto the stack
     */
    @Override
    public void push(T item) {
        // Resize operation; return a new stack, of capacity sized up by a factor of 2
        if (size == capacity) {
            resize(capacity * 2);
        }

        stack[size] = item;
        size++;
    }

    /**
     * Remove the most recently inserted item from the top of the stack
     * @return item - data on stack
     */
    @Override
    public T pop() {
        if (size == 0) {
            throw new StackEmptyException("Cannot pop from an empty stack");
        }

        size--;
        T item = stack[size];
        stack[size] = null;

        // Shrink: select the upper-bound of capacity / 2, or INITIAL; cannot shrink below INITIAL capacity
        if (capacity > INITIAL && size <= capacity / 4) {
            int max = INITIAL < (capacity / 2) ? capacity / 2: INITIAL;
            shrink(max);
        }

        return item;
    }

    /**
     * Returns the most recently inserted element from the top of the stack; does not remove it
     * @return stack[size - 1] - data sitting atop of the stack
     */
    @Override
    public T peek() {
        if (size == 0) {
            throw new StackEmptyException("Cannot peek on an empty stack");
        }

        return stack[size - 1];
    }

    /**
     * Returns the stack capacity (THIS IS A TEST CASE FUNCTION)
     * @return capacity - the amount of elements the stack can store before resize is triggered
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Resize operation when the size of the stack reaches capacity
     * @param newSize - the size of the new stack (following scalar increase by 2)
     */
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        T[] newStack = (T[]) new Object[newSize];

        for (int i = 0; i < size; i++) {
            newStack[i] = stack[i];
        }

        capacity = newSize;
        stack = newStack;
    }

    /**
     * Shrink operation; when the size of the stack falls far below its capacity
     * Done in order to preserve memory
     * @param newSize - 1/2 of the capacity, or initial size
     */
    @SuppressWarnings("unchecked")
    private void shrink(int newSize) {
        T[] newStack = (T[]) new Object[newSize];

        for (int i = 0; i < size; i++) {
            newStack[i] = stack[i];
        }

        capacity = newSize;
        stack = newStack;
    }
}