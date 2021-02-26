//--== CS400 File Header Information ==--
//Name: Mayank Dornala
//Email: dornala@wisc.edu
//Team: Blue
//Group: JF
//TA: Xinyi
//Lecturer: Dahl
//Notes to Grader: I made a method in the HashTableMap class which allows me to access the private 
//                 hash array without making any changes to it. This is to check that the right 
//                 keys were placed in the right buckets. 
import java.util.NoSuchElementException;

public class TestHashTable {

	/** 
	 * test simple insertion of nodes and check they're in the right bucket
	 * 
	 * No Collision Tests (see test 2)
	 * 
	 * @return true if functionality verified, false otherwise
	 */
	public static boolean test1() {

		// default constructor of HashMapTable ; capacity = 10
		HashTableMap<String, String> table1 = new HashTableMap<String, String>();

		// Test Insertion without collision first: Bucket #
		table1.put("a", "Val1"); // 7
		table1.put("b", "Val2"); // 8
		table1.put("c", "Val3"); // 9
		table1.put("d", "Val4"); // 0
		table1.put("e", "Val5"); // 1

		// test if size if correct:
		if (table1.size() != 5) {
			return false;
		}

		// test if nodes are in the correct buckets
		int[] bucketIndices = { 7, 8, 9, 0, 1 };
		String[] keys = { "a", "b", "c", "d", "e" };
		for (int i = 0; i < bucketIndices.length; i++) {
			if (table1.getTable()[bucketIndices[i]].getKey() != keys[i]) {
				return false;
			}
		}

		return true; // by default, false if error encountered
	}

	/**
	 * test rehash() function
	 * 
	 * Forces a collision and checks if rehash function was triggered by checking 
	 * array properties before and after a rehash should happen
	 * 
	 * Put in 8 nodes, check properties, put in 9th node (triggers rehash) and check properties
	 * @return true if functionality verified, false otherwise
	 */
	public static boolean test2() {
		
		// default cap : 10
		HashTableMap<String, String> table = new HashTableMap<String, String>();

		// insert 8 Nodes, insertion of 9th node should trigger resize and rehash
		
		                                 // Bucket #    // New Bucket # (after rehash)
		table.put("a", "Val1"); //            7                       17

		table.put("b", "Val2"); //            8                       18

		table.put("c", "Val3"); //            9                       19

		table.put("d", "Val4"); //            0                        0

		table.put("e", "Val5"); //            1                        1

		table.put("f", "Val1"); //            2                        2

		table.put("g", "Val2"); //            3                        3

		table.put("h", "Val3"); //            4                        4

		// Check : size = 8, capacity = 10
		if (table.size() != 8 || table.getTable().length != 10) {
			return false;
		}
		
		// Addition of a ninth key,value pair (node) exceeds load factor (0.9 > 0.85), 
		// triggering rehash():

		table.put("i", "Val4"); //            5                        5
		
		// Check: size now 9, and capacity 2x to 20
		if (table.size() != 9 || table.getTable().length != 20) {
			return false;
		}
		
		// Check that K,V pairs in right buckets:
		int[] bucketIndices = { 17, 18, 19, 0, 1, 2, 3, 4, 5};
		String[] keys = { "a", "b", "c", "d", "e","f","g","h","i"};
		for (int i = 0; i < bucketIndices.length; i++) {
			if (table.getTable()[bucketIndices[i]].getKey() != keys[i]) {
				return false;
			}
		}

		return true; 
	}

	/**
	 * test remove(): 2 Cases
	 * 
	 * 1st Case: Removal of a Node, not in array and further downfield in linked node list
	 * 
	 * 2nd Case: Removal of a node that is the only node in the bucket 
	 * 
	 * @return true if functionality verified, false otherwise
	 */
	public static boolean test3() { 
		
		// default constructor of HashMapTable ; capacity = 10
		HashTableMap<String, String> table = new HashTableMap<String, String>();

		// Test Insertion without collision first: Bucket #
		table.put("a", "Val1"); // 7
		table.put("b", "Val2"); // 8
		table.put("c", "Val3"); // 9
		table.put("d", "Val4"); // 0
		table.put("e", "Val5"); // 1
		
		table.put("k", "val69"); // 7 (COLLISION!!!)
		
		// Check that collision occurred: a should be the next node of k in bucket 7
		if(table.getTable()[7].getKey() != "k" && table.getTable()[7].getNext().getKey() != "a") {
			return false; 
		}
		
		// Remove key = a : checks removal works on a bucket with multiple nodes
		// key "a" is deeper in the linkedList than k, even though they're in the same bucket
		// testing removal of a deeper node:
	    String valueReturned = table.remove("a");
	    if(!valueReturned.equals("Val1")) {
	    	return false; 
	    }
	    
	    // .get method should return Exception when trying to return a: try-catch 
	    try {
	    	table.get("a");
	    	return false;
	    }catch(NoSuchElementException e) {
	    	
	    }
	    
	   // Check Simple Removal: Remove K,V pair from a bucket with one node 
	   valueReturned = table.remove("b");
	   
	   if(!valueReturned.equals("Val2")) {
	    	return false; 
	    }
	    
	    // .get method should return Exception when trying to return b: try-catch 
	    try {
	    	table.get("a");
	    	return false;
	    }catch(NoSuchElementException e) {
	    	
	    }
	    
	
		return true; 
	}
	
	/**
	 * test clear():
	 * Adds Nodes, then clears,
	 * 
	 * Checks functionality by attempting to get() a certain key. Since table is not empty, 
	 * .get() will throw a NoSuchElementException if clear() worked correctly
	 * 
	 * @return true if functionality verified, false otherwise
	 */
	public static boolean test4() { 
		
		HashTableMap<String, String> table = new HashTableMap<String, String>();
		
		// fill table; clear(); check get() throws NoSuchElementException
		table.put("a", "Val1"); // 7
		table.put("b", "Val2"); // 8
		table.put("c", "Val3"); // 9
		table.put("d", "Val4"); // 0
		table.put("e", "Val5"); // 1
		
		table.clear(); 
		
		// if empty, table.get() for any key should throw a NoSuchElement
		try {
	    	table.get("a");
	    	return false;
	    }catch(NoSuchElementException e) {
	    	
	    }
		
		try {
	    	table.get("b");
	    	return false;
	    }catch(NoSuchElementException e) {
	    	
	    }
		
		// table size should be 0 after .clear() is called
		if(table.size() != 0) {
			return false; 
		}
		
		return true;
	}
	
	/**
	 * test containsKey: checks if containsKey returns true when argument is a key in the table 
	 *                   and false if key is not in table
	 * @return
	 */
	public static boolean test5() { 
		
		HashTableMap<String, String> table = new HashTableMap<String, String>();
		
		// fill table; clear(); check get() throws NoSuchElementException
		table.put("a", "Val1"); // 7
		table.put("b", "Val2"); // 8
		
		// key "c" is not in the table, .containKey('c') should return false 
		if(table.containsKey("c") == true) {
			return false; 
		}
		
		return (table.containsKey("a")); 
	}

	public static void main(String[] args) {

		System.out.println(test1());
		System.out.println(test2());
		System.out.println(test3());
		System.out.println(test4());
		System.out.println(test5());
		
	
	}

}
