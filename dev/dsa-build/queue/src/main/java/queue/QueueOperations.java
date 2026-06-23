package queue;
public interface QueueOperations<T> {
    void enqueue(T item);
    T dequeue();
    T front();
    int size();
    boolean isEmpty();
    boolean isFull();
}