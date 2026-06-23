package hashmap;

import java.util.Objects; // Resolve via separate chaining
import java.util.Random;
import singly.SinglyLinkedList;
import singly.Node;

public class Hashmap<K, V> implements HashmapInterface<K, V> {
    private final int INITIAL_CAPACITY = 10;
    private int capacity = INITIAL_CAPACITY;
    private int size = 0;
    private SinglyLinkedList<Entry>[] table;

    // MAD variables
    private final Random random = new Random();
    private final long p = 98317; // Sufficient for a table storing 100000 keys
    private final long a = random.nextLong(1, p - 1);
    private final long b = random.nextLong(0, p - 1);
    /**
     * Populate each bucket with a linked list
     */
    @SuppressWarnings("unchecked")
    public Hashmap() {
        table = (SinglyLinkedList<Entry>[]) new SinglyLinkedList[capacity];

        // Populate each bucked with a linked list

        for (int i = 0; i < capacity; i++) { 
            table[i] = new SinglyLinkedList<>();
        }
    }

    /**
     * Named, nested class
     * Stores key, value pairs, used in conjunction with node class
     * @param key - key value used to access bucket and node in the linked list
     * @param value - value associated with the entry
     */
    private class Entry {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) { return true; }

            if (other == null || getClass() != other.getClass()) { return false; }

            @SuppressWarnings("unchecked")
            Entry otherOne = (Entry) other;
            return Objects.equals(key, otherOne.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    /**
     * Returns the number of elements in the hashmap
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Inserts a key, value pair (as an entry) into the hashmap
     * Uses key, calculates the bucket index; inserts the entry as a node in the linked list
     * Insertion occurs at the tail of the linked list
     * @param key - key, corresponds to a bucket
     * @param value - data associated with the entry
     */
    @Override
    public void put(K key, V value) {
        SinglyLinkedList<Entry> chain = bucket(key);
        Node<Entry> existing = chain.get(new Entry(key, null));

        if (existing != null) {
            existing.setData(new Entry(key, value));
        }
        else {
            chain.insertAtTail(new Entry(key, value));
            size++;
        }

        if (size / (double) capacity >= 0.75) {
            resize();
        }
    }

    /**
     * Removes an item from the hashmap, by key value
     * @param key - key identifying the entry to remove
     * @return the value associated with the removed entry
     */
    @Override
    public V remove(K key) {
        SinglyLinkedList<Entry> chain = bucket(key);
        Entry removed = chain.remove(new Entry(key, null));

        if (removed == null) { return null; }
        size--;

        return removed.getValue();
    }

    /**
     * Fetches the value for the specified key in the map
     * @param key - associated with a bucket, and an entry in the linked list
     * @return returns value associated with the key
     */
    @Override
    public V get(K key) {
        SinglyLinkedList<Entry> chain = bucket(key);   
        Node<Entry> existing = chain.get(new Entry(key, null));

        if (existing == null) { return null; }

        return existing.getData().getValue();
    }

    /**
     * Determines presence of a key in the map
     * @param key - key identifying the bucket, and node in the linked list
     * @return true/false, true if the key exists, else false
     */
    @Override
    public boolean contains(K key) {
        return bucket(key).contains(new Entry(key, null));
    }

    /**
     * Standard hash function
     * Converts a key into some integer within the set of integers
     * @param key - argument passed; to be hashed
     * @return hashcode, some arbitrary integer
     */
    private long hashFunction(K key) {
        return (key == null) ? 0 : key.hashCode();
    }

    /**
     * Standard compression function - makes use of MAD, yielding greater universal distribution
     * Converts an integer (of some large arbitrary value) into a valid table index
     * Makes use of prime numbers to yield more random table indices
     * @param hashed - the integer given to the function
     * @return a valid index for the hash table
     */
    private int compressionFunction(long hashed) {
        long mad = ((long) a * hashed + b) % p;
        return Math.floorMod((int) mad, capacity);
    }

    /**
     * Index of the map, storing the linked list
     * @param key - key identifying the bucket, and node in the linked list
     * @return the bucket corresponding to the key
     */
    private SinglyLinkedList<Entry> bucket(K key) {
        return table[compressionFunction(hashFunction(key))];
    }

    /**
     * Resize operation, executed when the load factor (n/N >= 0.75) exceeds the capacity (buckets cardinality)
     * Rehashing is required, for all entries in each linked list
     */
    @SuppressWarnings("unchecked")
    private void resize() {

        int oldCapacity = capacity;
        capacity = capacity * 2;
        SinglyLinkedList<Entry>[] newMap = new SinglyLinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            newMap[i] = new SinglyLinkedList<>();
        }
        
        for (int j = 0; j < oldCapacity; j++) {
            SinglyLinkedList<Entry> linkedList = table[j];
            Node<Entry> head = linkedList.getHead();

            while (head != null) {
                int newHash = compressionFunction(hashFunction(head.getData().getKey()));

                newMap[newHash].insertAtTail(head.getData());

                head = head.getNext();
            }
        }

        table = newMap;
    }

    // Test methods
    /**
     * @return number of buckets in the map
     */
    public int getCapacity() {
        return capacity;
    }
}