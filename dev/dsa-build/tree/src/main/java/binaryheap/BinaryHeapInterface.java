package binaryheap;
public interface BinaryHeapInterface<T> {
    int size();
    T peek();
    void put(T item);
    T removeMin();
}
