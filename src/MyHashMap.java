import java.util.ArrayList;
public class MyHashMap {
	ArrayList<Entry>[] buckets;
	
	// Constructor
	public MyHashMap() {
		buckets = new ArrayList[10];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayList<Entry>();
		}
	}
	
	// Methods
	public int hash(String variableName) {
		return 0;
	}
}