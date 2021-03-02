// --== CS400 File Header Information ==--
// Name: Mayank Dornala
// Email: dornala@wisc.edu
// Team: Blue
// Group: JF
// TA: Xinyi
// Lecturer: Dahl
// Notes to Grader:
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;


/**
 * A HashTablePair class is a helper class, whose instance
 * represents a key-value pair in a HashTableMap.
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
class HashTablePair<K, V> {
    /** The key in this pair. */
    K key;
    /** The value in this pair. */
    V value;

    /**
     * Creates a new HashTablePair.
     * @param key the key
     * @param value the value
     */
    public HashTablePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Two HashTablePair instances are equal if only they have equal keys.
     * @param o the other HashTablePair to compare
     * @return true if the other HashTablePair has the same key as this
     * HashTablePair, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTablePair<?, ?> that = (HashTablePair<?, ?>) o;
        return Objects.equals(key, that.key);
    }

    /**
     * The hashcode of a HashTablePair is the hashcode of its key.
     * @return the hashcode of this key.
     */
    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}

/**
 * A HashTableMap is a data structure of pairs of key-value maps.
 * @param <K> the type of the keys.
 * @param <V> the type of the values.
 */
public class HashTableMap<K, V> implements MapADT<K, V> {
    /** The load factor threshold when to grow this hash table. */
    private static final double LOAD_FACTOR_THRESHOLD = 0.85;

    /** An array of LinkedList of HashTablePair in order to handle hash collisions. */
    private LinkedList<HashTablePair<K, V>>[] records;

    /** The number of items in this HashTableMap. */
    private int size;

    /**
     * Creates a new HashTableMap with the specified initial capacity.
     * @param capacity the initial capacity
     */
    public HashTableMap(int capacity) {
        records = new LinkedList[capacity];
        size = 0;
    }

    /**
     * Creates a new HashTableMap with an initial capacity of 10.
     */
    public HashTableMap() {
        this(10);
    }

    /**
     * Returns the percentage the HashTableMap is filled.
     * @return the percentage the HashTableMap is filled.
     */
    private double loadFactor() {
        return (double) size / records.length;
    }

    /**
     * Doubles this HashTableMap and rehashes its contents.
     */
    private void resize() {
        // The new records with double the size
        LinkedList<HashTablePair<K, V>>[] tmp = new LinkedList[records.length * 2];

        // Iterate through the current records to find each pair and rehash them
        // into the new records.
        for (LinkedList<HashTablePair<K, V>> pairs : records) {
            if (pairs == null)
                continue;
            // Rehash each pair in the pair chain
            for (HashTablePair<K, V> pair : pairs) {
                int newIndex = Math.abs(pair.key.hashCode()) % tmp.length;
                if (tmp[newIndex] == null)
                    tmp[newIndex] = new LinkedList<>();
                tmp[newIndex].add(pair);
            }
        }
        records = tmp;
    }

    /**
     * Returns a hash index in this HashTableMap of a given key.
     * @param key the given key
     * @return a hash index in this HashTableMap of a given key.
     */
    private int hashIndex(K key) {
        return Math.abs(key.hashCode()) % records.length;
    }

    /**
     * Puts a key-value pair. If newly-put pair results in the current load
     * factor exceeding the threshold, resizes this HashTableMap. If the key
     * already exists or it's null, nothing is added.
     * @param key the key of the pair to be added
     * @param value the value of the pair to be added
     * @return true if successful, false if key is null or the key already exists.
     */
    @Override
    public boolean put(K key, V value) {
        if (key == null)
            return false;
        if (containsKey(key))
            return false;

        // initialize the chain if the chain at index is not yet initialized
        if (records[hashIndex(key)] == null)
            records[hashIndex(key)] = new LinkedList<>();

        // add the pair into the chain at index
        records[hashIndex(key)].add(new HashTablePair<>(key, value));
        size++;

        if (loadFactor() >= LOAD_FACTOR_THRESHOLD)
            resize();

        return true;
    }

    /**
     * Returns the value corresponding to the given key. If the key does not exist,
     * throws a NoSuchElementException.
     * @param key the key the value corresponds to
     * @return the value corresponding to the given key
     * @throws NoSuchElementException if the key does not exist
     */
    @Override
    public V get(K key) throws NoSuchElementException {
        if (!containsKey(key))
            throw new NoSuchElementException();

        LinkedList<HashTablePair<K, V>> chain = records[hashIndex(key)];

        // find the HashTablePair in the chain with the given key and return it
        int loc = chain.indexOf(new HashTablePair<K, V>(key, null));
        return chain.get(loc).value;
    }

    /**
     * Returns the current number of items.
     * @return the current number of items.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the given key is present.
     * @param key the key to check
     * @return true if the key is present, false otherwise.
     */
    @Override
    public boolean containsKey(K key) {
        LinkedList<HashTablePair<K, V>> chain = records[hashIndex(key)];

        // false if chain at index is not yet initialized, or the chain does not contain the key, true otherwise
        return chain != null && chain.contains(new HashTablePair<K, V>(key, null));
    }

    /**
     * Removes a key-value pair with the corresponding key, and returns its
     * value. If the key does not exist, returns null.
     * @param key the key of the key-value pair.
     * @return the value that the key corresponds to, null if the key does not exist.
     */
    @Override
    public V remove(K key) {
        if (!containsKey(key))
            return null;

        LinkedList<HashTablePair<K, V>> chain = records[hashIndex(key)];

        // find the HashTablePair in the chain with the given key and remove it
        int loc = chain.indexOf(new HashTablePair<K, V>(key, null));
        HashTablePair<K, V> pair = chain.remove(loc);
        size--;
        return pair.value;
    }

    /**
     * Clears all the key-value pairs.
     */
    @Override
    public void clear() {
        records = new LinkedList[records.length];
        size = 0;
    }
}
