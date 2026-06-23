package doubly;

public interface DoublyNodeInterface<T> {
    T data();
    DoublyNode<T> next();
    DoublyNode<T> prev();
    void setData(T data);
    void setNext(DoublyNode<T> other);
    void setPrev(DoublyNode<T> other);
}