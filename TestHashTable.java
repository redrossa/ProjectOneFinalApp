// --== CS400 File Header Information ==--
// Name: Mayank Dornala
// Email: dornala@wisc.edu
// Team: Blue
// Group: JF
// TA: Xinyi
// Lecturer: Dahl
// Notes to Grader:
import java.util.NoSuchElementException;


/**
 * Tests five distinct methods of HashTableMap.
 */
public class TestHashTable {
    /**
     * Tests HashTableMap.put().
     * @return false if test failed, true otherwise.
     */
    public static boolean test1() {
        try {
            HashTableMap<String, String> map = new HashTableMap<>();

            // put() into an empty map
            if (!map.put("Hi", "World")
                    && map.size() != 1
                    && !map.get("Hi").equals("World"))
                return false;

            // put() more
            if (!map.put("His", "Worlds")
                    && map.size() != 2
                    && !map.get("His").equals("Worlds"))
                return false;

            // put() with existing key
            if (map.put("Hi", "wow"))
                return false;

            // put() with different keys, same hashcode
            if (!map.put("FB", "hey")
                    && !map.put("Ea", "lol")
                    && map.size() != 4
                    && !map.get("FB").equals("hey")
                    && !map.get("Ea").equals("lol"))
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests HashTableMap.get().
     * @return false if test failed, true otherwise.
     */
    public static boolean test2() {
        try {
            HashTableMap<String, String> map = new HashTableMap<>();

            // get() non existent key
            try {
                map.get("fake");
                return false;
            } catch (NoSuchElementException ignored) {
                // expected
            }

            // get() existent key
            map.put("wow", "ok");
            if (!map.get("wow").equals("ok"))
                return false;

            // get() value with pairs that are chained
            map.put("FB", "hey");
            map.put("Ea", "hay");
            if (!map.get("FB").equals("hey") && !map.get("Ea").equals("hay"))
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests HashTableMap.containsKey().
     * @return false if test failed, true otherwise.
     */
    public static boolean test3() {
        try {
            HashTableMap<String, String> map = new HashTableMap<>();

            // containsKey() of non existent key
            if (map.containsKey("fake"))
                return false;

            // containsKey() of existent key
            map.put("wow", "ok");
            if (!map.containsKey("wow"))
                return false;

            // containsKey() of keys that are chained
            map.put("FB", "hey");
            map.put("Ea", "hay");
            if (!map.containsKey("FB") && !map.containsKey("Ea"))
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests HashTableMap.remove().
     * @return false if test failed, true otherwise.
     */
    public static boolean test4() {
        try {

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests HashTableMap.clear().
     * @return false if test failed, true otherwise.
     */
    public static boolean test5() {
        try {
            HashTableMap<String, String> map = new HashTableMap<>();

            // remove() on an empty map
            if (map.remove("Hi") != null)
                return false;

            // remove() an item in map
            map.put("His", "Worlds");
            if (!map.remove("His").equals("Worlds")
                    && map.size() != 0)
                return false;

            // remove() with different keys, same hashcode
            map.put("FB", "hey");
            map.put("Ea", "lol");
            if (!map.remove("FB").equals("hey")
                    && !map.remove("Ea").equals("lol")
                    && map.size() != 0)
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Runs all the tests in this class.
     * @param args if any.
     */
    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
        System.out.println(test5());
    }
}
