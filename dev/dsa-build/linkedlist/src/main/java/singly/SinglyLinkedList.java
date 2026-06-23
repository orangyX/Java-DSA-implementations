package singly;
public class SinglyLinkedList<T> implements SinglyLinkedListInterface<T>{
    private int size = 0;
    private Node<T> head = null;

    public SinglyLinkedList() {}

    @Override
    public void insertAtHead(T data) {
        Node<T> node = new Node<>(data);

        if (size == 0) {
            head = node;
        }
        else {
            node.setNext(head);
            head = node;
        }

        size++;
    }

    @Override
    public void insertAtTail(T data) {
        Node<T> node = new Node<>(data);

        if (size == 0) {
            head = node;
        }
        else {
            Node<T> current = head;

            while (current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(node);
        }

        size++;
    }

    @Override
    @SuppressWarnings("unused")
    public Node<T> get(T data) {
        if (head == null) { return null; }

        if (head.getData().equals(data)) {
            return head;
        }
        Node<T> prev = head;
        Node<T> curr = head.getNext();

        while (curr != null) {
            if (curr.getData().equals(data)) {
                return curr;
            }

            prev = curr;
            curr = curr.getNext();
        }

        return null;
    }

    @Override
    public Node<T> getHead() {
        return head;
    }

    @Override
    public boolean contains(T data) {
        return get(data) != null;
    }

    @Override
    public T remove(T data) {
        if (head == null) { return null; }

        if (head.getData().equals(data)) {
            T headData = head.getData();
            head = head.getNext();
            size--;

            return headData;
        }

        Node<T> prev = head;
        Node<T> curr = head.getNext();

        while (curr != null) {
            if (curr.getData().equals(data)) {
                prev.setNext(curr.getNext());
                size--;
                return curr.getData();
            }

            prev = curr;
            curr = curr.getNext();  
        }

        return null;
    }
}