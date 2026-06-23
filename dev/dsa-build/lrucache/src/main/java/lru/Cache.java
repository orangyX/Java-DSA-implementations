package lru;

import doubly.DoublyLinkedList;
import doubly.DoublyNode;
import hashmap.Hashmap;

public class Cache<K, V> implements CacheInterface<K, V> {
    Hashmap<K, DoublyNode<Entry<K, V>>> hashmap;
    DoublyLinkedList<Entry<K, V>> linkedList;
    private int capacity;
    private int size;

    public Cache(int capacity) {
        this.capacity = capacity;
        this.hashmap = new Hashmap<>();
        this.linkedList = new DoublyLinkedList<>();
    }

    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        DoublyNode<Entry<K, V>> node = hashmap.get(key);

        if (node == null) { return null; }

        linkedList.moveToFront(node);

        return node.data().value();
    }

    @Override
    public void put(K key, V value) {
        DoublyNode<Entry<K, V>> node = hashmap.get(key);

        if (node != null) {
            node.data().setValue(value);
            linkedList.moveToFront(node);
        }
        else {
            DoublyNode<Entry<K, V>> newNode = linkedList.insertAtHead(new Entry<>(key, value));
            hashmap.put(key, newNode);
            size++;

            if (size > capacity) { evict(); }
        }
    }

    private void evict() {
        DoublyNode<Entry<K, V>> lastNode = linkedList.getLast();
        hashmap.remove(lastNode.data().key());
        linkedList.removeNode(lastNode);
        size--;
    }
}