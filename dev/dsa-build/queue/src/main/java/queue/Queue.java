package queue;
public class Queue<T> implements QueueOperations<T> {
    private final int INITIAL_CAPACITY = 10;
    private int capacity = INITIAL_CAPACITY;
    @SuppressWarnings("unchecked")
    private T[] queue = (T[]) new Object[capacity];
    private int size = 0;
    private int front = 0;
    private int rear = 0;

    public Queue() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public T front() {
        if (isEmpty()) {
            throw new EmptyQueueException("Cannot get front from an empty queue");
        }

        return queue[front];
    }

    @Override
    public void enqueue(T item) {
        if (isFull()) {
            resize(capacity * 2);
        }

        queue[rear] = item;
        rear = (rear + 1) % capacity;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException("Cannot dequeue from an empty queue");
        }

        T dequeued = queue[front];
        queue[front] = null;
        front = (front + 1) % capacity;
        size--;

        if (capacity > INITIAL_CAPACITY && size() <= capacity / 4) {
            int max = INITIAL_CAPACITY < capacity / 2 ? capacity / 2 : INITIAL_CAPACITY;
            shrink(max);
        }

        return dequeued;
    }

    public int getCapacity() {
        return capacity;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCap) {
        T[] newQueue = (T[]) new Object[newCap];

        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(front + i) % capacity];
        }

        front = 0;
        rear = size;
        capacity = newCap;
        queue = newQueue;
    }

    @SuppressWarnings("unchecked")
    private void shrink(int newCap) {
        T[] newQueue = (T[]) new Object[newCap];

        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(front + i) % capacity];
        }
        
        front = 0;
        rear = size;
        capacity = newCap;
        queue = newQueue;
    }
}