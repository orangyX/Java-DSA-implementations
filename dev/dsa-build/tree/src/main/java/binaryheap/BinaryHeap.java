package binaryheap;

public class BinaryHeap<T extends Comparable<T>> implements BinaryHeapInterface<T>{
    private final int INITIAL = 10;
    private int capacity = 10;
    private int size = 0;
    private T[] heap = (T[]) new Comparable[capacity];

    public BinaryHeap() {}

    @Override
    public int size() {
        return size;
    }

    @Override
    public T peek() {
        return heap[0];
    }

    @Override
    public void put(T item) {
        if (size == capacity) {
            resize();
        }

        heap[size] = item;
        upHeap(size);
        size++;
        }

    @Override
    public T removeMin() {
        T removed = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        if (size > 0) { downHeap(0); }

        return removed;
    }

    private void upHeap(int childIndex) {
        int parentIndex = (childIndex - 1) / 2;

        while (childIndex > 0 && heap[childIndex].compareTo(heap[parentIndex]) < 0) {
            swap(childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
    }

    private void downHeap(int parentIndex) {
        while (hasLeft(parentIndex)) {
            int minChildIndex = getMinChildIndex(parentIndex);

            if (heap[minChildIndex].compareTo(heap[parentIndex]) < 0) {
                swap(minChildIndex, parentIndex);
                parentIndex = minChildIndex;
            }
            else { break; } // Terminate if the parent value > children values
        }
    }

    private int getMinChildIndex(int parentIndex) {
        if (!hasLeft(parentIndex)) { throw new ChildException("Parent does not have children"); }

        int leftChildIdx = parentIndex * 2 + 1;

        if (!hasRight(parentIndex)) { return leftChildIdx; } // Only has a left child

        int rightChildIdx = parentIndex * 2 + 2; // Both left, and right; must compute the minimum

        if (heap[leftChildIdx].compareTo(heap[rightChildIdx]) < 0) {
            return leftChildIdx;
        }

        return rightChildIdx;
    }
        
    private void swap(int firstIdx, int secondIdx) {
        T temp = heap[firstIdx];
        heap[firstIdx] = heap[secondIdx];
        heap[secondIdx] = temp;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        T[] newHeap = (T[]) new Comparable[newCapacity];

        for (int i = 0; i < size; i++) {
            newHeap[i] = heap[i];
        }

        capacity = newCapacity;
        heap = newHeap;
    }

    private boolean hasLeft(int parentIndex) {
        return parentIndex * 2 + 1 < size;
    }

    private boolean hasRight(int parentIndex) {
        return parentIndex * 2 + 2 < size;
    }
}