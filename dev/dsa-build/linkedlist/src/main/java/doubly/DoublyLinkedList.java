package doubly;

import exceptions.NodeAbsenceException;

public class DoublyLinkedList<T> implements DoublyLinkedListInterface<T> {
    private int size = 0;
    private final DoublyNode<T> header;
    private final DoublyNode<T> trailer;

    public DoublyLinkedList(){
        header = new DoublyNode<>(null);
        trailer = new DoublyNode<>(null);
        header.setNext(trailer);
        trailer.setPrev(header);
    }

    /**
     * Returns size; no. nodes in the linked list
     * @return size of the linked list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Inserts a node containing the data at the head of the list
     * @param data - the data to be stored within the node
     * @return the inserted node
     */
    @Override
    public DoublyNode<T> insertAtHead(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        DoublyNode<T> nextNode = header.next();

        newNode.setPrev(header);
        newNode.setNext(nextNode);
        header.setNext(newNode);
        nextNode.setPrev(newNode);
        size++;

        return newNode;
    }

    /**
     * Insets a node between the trailer's previous node, and node's next()
     * @param data - the data to be stored within the node
     */
    @Override
    public DoublyNode<T> insertAtTrail(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        DoublyNode<T> prevNode = trailer.prev();

        newNode.setNext(trailer);
        newNode.setPrev(prevNode);
        prevNode.setNext(newNode);
        trailer.setPrev(newNode);
        size++;

        return newNode;
    }

    /**
     * Returns the node at the head of the linked list
     * @return header.next(): next() field of the header sentinel
     */
    @Override
    public DoublyNode<T> getFirst() {
        return header.next();
    }

    /**
     * Returns the node at the trail of the linked list
     * @return trailer.prev(): prev() field of the trailer sentinel
     */
    @Override 
    public DoublyNode<T> getLast() {
        return trailer.prev();
    }

    /**
     * Denotes presence/absence of a node associated with the data
     * @param data - the data to be searched for
     * @return boolean - true if a node with the data exists, false otherwise
     */
    @Override
    public boolean contains(T data) {
        DoublyNode<T> curr = header.next();

        while (curr != trailer) {
            if (curr.data().equals(data)) { return true; }
            curr = curr.next();
        }

        return false;
    }

    /**
     * Returns node associated with data
     * @param data - the data to be searched for
     * @return node associated with the data
     * @throws NodeAbsenceException if there was no node found with the matching data
     */
    @Override
    public DoublyNode<T> get(T data) {
        DoublyNode<T> curr = header.next();

        while (curr != trailer) {
            if (curr.data().equals(data)) { return curr; }
            curr = curr.next();
        }

        throw new NodeAbsenceException("Cannot fetch a node that does not exist");
    }

    /**
     * Removes the first occurrence of a node associated with the data
     * @param data - the data identifying the removable node
     * @return the node containing the data that was removed
     * @throws NodeAbsenceException if there was no node found with the matching data
     */
    @Override
    public DoublyNode<T> remove(T data) {
        DoublyNode<T> node = get(data);
        DoublyNode<T> returnNode = new DoublyNode<>(node.data());

        node.prev().setNext(node.next());
        node.next().setPrev(node.prev());
        node = null;

        size--;

        return returnNode;
    }

    /**
     * Removes a node from the list by altering its next() and prev() fields
     * @param node - the node to remove (via prev() and next())
     */
    public void removeNode(DoublyNode<T> node) {
        node.next().setPrev(node.prev());
        node.prev().setNext(node.next());
        size--;
    }

    public void moveToFront(DoublyNode<T> node) {
        // Detatchment
        node.next().setPrev(node.prev());
        node.prev().setNext(node.next());

        // Insertion at head
        DoublyNode<T> oldFirst = header.next();
        node.setPrev(header);
        node.setNext(header.next());
        header.setNext(node);
        oldFirst.setPrev(node);
    }
}