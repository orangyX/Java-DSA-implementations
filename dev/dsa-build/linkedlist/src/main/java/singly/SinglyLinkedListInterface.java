package singly;
public interface SinglyLinkedListInterface<T> {
    void insertAtHead(T data);
    void insertAtTail(T data);
    Node<T> get(T data);
    Node<T> getHead();
    boolean contains(T data);
    T remove(T data);
}