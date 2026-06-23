package doubly;

public class DoublyNode<T> implements DoublyNodeInterface<T> {
    private T data;
    private DoublyNode<T> next;
    private DoublyNode<T> prev;

    public DoublyNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * Returns data stored within the node
     * @return T data
     */
    @Override
    public T data() {
        return data;
    }

    /**
     * Fetches the node stored in next
     * @return DoublyNode<T>
     */
    @Override
    public DoublyNode<T> next() {
        return next;
    }

    /**
     * Fetches the node stored in prev
     * @Return DoublyNode<T>
     */
    @Override
    public DoublyNode<T> prev() {
        return prev;
    }

    /**
     * Overrites data stored in a node
     */
    @Override
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Sets the node's next; points to the next node
     */
    @Override
    public void setNext(DoublyNode<T> other) {
        next = other;
    }

    /**
     * Sets the node's previous; points to the previous node
     */
    @Override
    public void setPrev(DoublyNode<T> other) {
        prev = other;
    }
}