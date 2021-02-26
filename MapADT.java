// --== CS400 File Header Information ==--
// Name: Mayank Dornala
// Email: dornala@wisc.edu
// Team: Blue
// Group: JF
// TA: Xinyi
// Lecturer: Dahl
// Notes to Grader: Provided MaptADT Interface in Canvas
import java.util.NoSuchElementException;

public interface MapADT<KeyType, ValueType> {

	public boolean put(KeyType key, ValueType value);
	public ValueType get(KeyType key) throws NoSuchElementException;
	public int size();
	public boolean containsKey(KeyType key);
	public ValueType remove(KeyType key);
	public void clear();
	
}
