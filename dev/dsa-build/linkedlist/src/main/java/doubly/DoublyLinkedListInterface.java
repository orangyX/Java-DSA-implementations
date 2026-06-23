package doubly;

public interface DoublyLinkedListInterface<T>{
    int size();
    DoublyNode<T> insertAtHead(T data);
    DoublyNode<T> insertAtTrail(T data);
    DoublyNode<T> get(T data);
    DoublyNode<T> getFirst();
    DoublyNode<T> getLast();
    boolean contains(T data);
    DoublyNode<T> remove(T data);
}