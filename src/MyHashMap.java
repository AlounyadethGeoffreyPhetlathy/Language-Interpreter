import java.util.ArrayList;
public class MyHashMap {
	// Fields
	ArrayList<Entry>[] buckets;
	private int value;
	Alphabet al;
	
	// Constructor
	public MyHashMap() {
		buckets = new ArrayList[10];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayList<Entry>();
		}
	}
	
	// Methods
	public int hash(String variableName) {
//		int total = 0;
//		for (int i = 0; i < variableName.length(); i++) {
//			total += value;
//		}
		return 0;
	}
}