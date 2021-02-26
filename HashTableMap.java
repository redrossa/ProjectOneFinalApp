//--== CS400 File Header Information ==--
//Name: Mayank Dornala
//Email: dornala@wisc.edu
//Team: Blue
//Group: JF
//TA: Xinyi
//Lecturer: Dahl
//Notes to Grader: HashTable and HashNode Classes
import java.util.NoSuchElementException;

/**
 * 
 * @author Mayank
 *
 * @param <K> key
 * @param <V> value
 */
class HashNode<K, V> {

	// node parameters:
	K key;
	V value;

	// pointers:
	HashNode<K, V> next;
	HashNode<K, V> prev;
 
	// Constructor
	public HashNode(K key, V value) {
		this.key = key;
		this.value = value;
		this.next = this.prev = null;
	}

	// Setters:
	
	// sets key of node:
	public void setK(K key) {
		this.key = key;
	}
	// sets value of node:
	public void setV(V value) {
		this.value = value;
	}
	// sets next pointer to a node next
	public void setNext(HashNode<K, V> next) {
		this.next = next;
	}
	// sets prev pointer to node prev
	public void setPrev(HashNode<K, V> prev) {
		this.prev = prev;
	}

	// Getters:

	// returns key
	public K getKey() {
		return this.key;
	}
	// returns value
	public V getValue() {
		return this.value;
	}
	// returns node of next pointer
	public HashNode<K, V> getNext() {
		return this.next;
	}
	// returns node of prev pointer
	public HashNode<K, V> getPrev() {
		return this.prev;
	}
}

/**
 * 
 * @author Mayank
 *
 * @param <K> Key
 * @param <V> Value
 */
public class HashTableMap<K, V> implements MapADT<K, V> {

	// instance fields:
	private int capacity; // current number buckets
	private int size; // current number nodes in HashTable
	private final double maxLoadFactor = 0.85;

	// Default Size: 10
	private final static int defaultCapacity = 10;

	// Private Array Instance Field: stores Key, Value Pairs
	private HashNode<K, V>[] table; // these will represent heads of each LinkedList

	// public accessor method for HashTable (For Tester Class: tests whether keys
	// inserted into correct buckets)
	public HashNode<K, V>[] getTable() {
		return this.table;
	}

	// Constructors:
	/**
	 * Default Constructor; capacity set to 10 (see instance field defaultCapacity)
	 */
	@SuppressWarnings("unchecked")
	public HashTableMap() {
		this.capacity = defaultCapacity;
		this.size = 0;
		// creates an array of HashNodes with generics type parameters:
		this.table = (HashNode<K, V>[]) new HashNode[capacity];
	}

	/**
	 * Constructs HashTableMap: 
	 * @param capacity: sets number buckets in array to this int value
	 */
	@SuppressWarnings("unchecked")
	public HashTableMap(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.table = (HashNode<K, V>[]) new HashNode[capacity];
	}

	/**
	 * HASH FUNCTION HELPER METHOD. Can be called from methods where specific spot
	 * is needed
	 * 
	 * @param key: key of Node
	 * @return
	 */
	private int hashFunction(K key) {
		int hashCode = key.hashCode();

		// if hashCode is neg, switch to pos
		if (hashCode < 0) {
			hashCode *= -1;
		}

		// Hash Function: hashCode abs value mod current capacity (from Canvas Specs)
		int tableIndex = hashCode % capacity;
		return tableIndex;
	}

	/** 
	 * ReHash and ReSize Helper Methods: See put()
	 * 
	 * 1. Doubles Capacity
	 * 2. Creates a local newTable variable which is used to store rehashed nodes
	 * 3. rehash nodes (recalculate hash code using new capacity and place into proper buckets)
	 * 4. store newTable as this.table instance field (replace old table with rehashed table)
	 * 
	 */
	private void rehash() {
		// double existing capacity:
		this.capacity *= 2;

		// ReHash Method:
		/*
		 * Create new table with size capacity Traverse through existing table and call
		 * put on each node in the table.
		 */
		@SuppressWarnings("unchecked")
		HashNode<K, V>[] newTable = (HashNode<K, V>[]) new HashNode[capacity]; // new empty table

		// traverse existing table:
		for (int i = 0; i < this.table.length; i++) {
			HashNode<K, V> head = (HashNode<K, V>) this.table[i];
			// if head is null, bucket is likely empty, move on to next bucket
			if (head == null) {
				continue;
			}
			
			// if head is the only node (K,V pair) in the bucket: 
			// rehash that node: 
			if(head.getNext() == null) { // condition for head node being the only node in current bucket
				HashNode<K, V> localCopy = new HashNode<K, V>(null, null);
				localCopy = head;
				// define new HashCode:
				int newIndex = hashFunction(localCopy.getKey());

				HashNode<K, V> existingNode = (HashNode<K, V>) newTable[newIndex]; // existing node in new table

				if (existingNode == null) {
					newTable[newIndex] = localCopy;
				} else {
					newTable[newIndex] = localCopy;
					localCopy.setNext(existingNode);
					existingNode.setPrev(localCopy);
				}
			}

			// ReHash Every Bucket with a head node
			while (head.getNext() != null) {
				HashNode<K, V> localCopy = new HashNode<K, V>(null, null);
				localCopy = head;
				// define new HashCode:
				int newIndex = hashFunction(localCopy.getKey());

				HashNode<K, V> existingNode = (HashNode<K, V>) newTable[newIndex]; // existing node in new table

				if (existingNode == null) {
					newTable[newIndex] = localCopy;
				} else {
					newTable[newIndex] = localCopy;
					localCopy.setNext(existingNode);
					existingNode.setPrev(localCopy);
				}

				// iterate to next HashNode in current bucket
				head = head.getNext();
			}
		}

		this.table = newTable; // set new table as instance table
	}

	/**
	 * Places node into correct bucket (calls hashFunction on key arg to calc hash code)
	 * 
	 * if bucket is empty, sets the array element to the arg node. 
	 * if bucket is not empty, pushes the existing element further into the list and replaces 
	 * with new node. 
	 * 
	 * @param key: key of node
	 * @param value: value of node 
	 */
	@Override
	public boolean put(K key, V value) {

		// if key is null, return false:
		if (key == null) {
			return false;
		}

		// if key already exists, return false:
		if (this.containsKey(key)) {
			return false;
		}

		// checks that load factor hasn't been reached
		if ((size + 1.0) / capacity >= maxLoadFactor) {
			rehash();
		}

		// create new Node:
		HashNode<K, V> node = new HashNode<K, V>(key, value); // node with adjacent nodes
		// determine index for hashtable using hashFunction helper method
		int tableIndex = hashFunction(node.getKey());

		HashNode<K, V> head = (HashNode<K, V>) this.table[tableIndex];

		if (head == null) {
			this.table[tableIndex] = node;
			this.size++;
		} else {
			this.table[tableIndex] = node;
			node.setNext(head);
			head.setPrev(node);
			this.size++;
		}

		return true;
	}

	/**
	 * Returns Value corresponding to argument key 
	 * 
	 * calculates correct bucket from arg key, 
	 * loops through that buckets list and returns node with arg key 
	 * if no node with key found, throw Exception
	 */
	@Override
	public V get(K key) throws NoSuchElementException {

		if (key.equals(null)) {
			throw new NoSuchElementException("Key is null");
		}

		int targetBucket = hashFunction(key);
		HashNode<K, V> head = (HashNode<K, V>) this.table[targetBucket];

		while (head != null) {

			if (head.getKey().equals(key)) { // if key is found
				return head.getValue();
			}

			head = head.getNext();

		}

		throw new NoSuchElementException("no such key in hash table.");
	}

	/**
	 * Returns size of list (stored as instance variable)
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}
	
	/**
	 * Calculates hash code for argument key. Iterates through the bucket for that hash code 
	 * and checks if a node with argument key exists.  
	 * 
	 * @returns true if table contains argument key, false if it does not
	 */
	@Override
	public boolean containsKey(K key) {
		// define index that this key would be at, then search that Bucket, return true
		// if found, false otherwise
		int bucketIndex = hashFunction(key);
		HashNode<K, V> head = (HashNode<K, V>) this.table[bucketIndex];

		while (head != null) {

			if (head.getKey().equals(key)) { // if key is found
				return true;
			}

			head = head.getNext();

		}

		return false;
	}

	/**
	 * Loops through bucket that key's hash code corresponds to. 
	 * 
	 * If the bucket is empty --> return null 
	 * 
	 * if the bucket contains one node: check if that node is the target. if true, return that node 
	 *                                  else targetNode = null and return null
	 *                                 
	 * if bucket contains multiple nodes and head node isn't targetNode: 
	 * Loop through bucket and check if any of the nodes are the target. If true, set targetNode to that 
	 * node. If false, targetNode = null and return null. 
	 */
	@Override
	public V remove(K key) {
		int targetBucket = hashFunction(key);
		HashNode<K, V> targetNode = null;
		HashNode<K, V> head = (HashNode<K, V>) this.table[targetBucket];

		// if target bucket is empty, return null
		if (head == null) {
			return null;
		}

		// if target is in array:
		if (head.getKey() == key) {
			targetNode = head;
			// reassign pointers:
			if (head.getNext() == null) {
				this.table[targetBucket] = null;
			} else {
				this.table[targetBucket] = head.getNext();
				this.table[targetBucket].setPrev(null);
				head.setNext(null); // prevents while loop execution
				// head = null;
			}
		}
		
		// if target is in linked list of nodes:
		while (head != null) {

			if (head.getKey() == key) { // if target is found:
				targetNode = head;   // target to head for accounting after while loop

				// if target node is at the end of the list:
				if (head.getNext() == null && head.getPrev() != null) {
					head.getPrev().setNext(null); 
					head.setPrev(null);; 
					
					// if target node is in the middle of the list:
				} else if (head.getNext()!=null && head.getPrev()!=(null)) {
					HashNode<K, V> prev = head.getPrev();
					HashNode<K, V> inFront = head.getNext();
 
					// cut out targetNode, splice together existing list:
					prev.setNext(inFront);
					inFront.setPrev(prev);
					
					head.setNext(null);
					head.setPrev(null);
				}

				break;
			}

			head = head.getNext();
		}
		

		if (targetNode == null) {
			// if key doesn't exist, return null
			return null;
		}
		this.size--; // decrement size upon removal confirmation (targetNode is not null)
		return targetNode.getValue(); // return value of target key
	}

	/**
	 * Loops through hash array and disconnects each head node from the list and sets the head node
	 * to null. Disconnect refers to changing pointer values so head node no longer points to adjacent 
	 * nodes. 
	 * 
	 * head node refers to the node actually stored in the hash array
	 */
	@Override
	public void clear() {
		// sets each element in the hash table to null so it no longer points to the
		// linked nodes
		for (int i = 0; i < this.table.length; i++) {
			if (this.table[i] == null ) {
				continue;
			}
			
			// if only one node in bucket: 
			if(this.table[i].getNext() == null) {
				this.table[i] = null;
				continue;
			}

			// if multiple nodes per bucket:
			
			// disconnect from next nodes: table will no longer be connect to linked nodes
			// further down field
			
			this.table[i].getNext().setPrev(null); // disconnect index 1 from index 0
			this.table[i].setNext(null);   // disconnect index 0 from index 1
			// delete node in hash table (head node) : clears table 
			this.table[i] = null;
		}
		
		this.size = 0; 
	}
}
