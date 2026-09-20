import java.util.ArrayList;
public class MyHashMap {
	// Fields
	ArrayList<Entry>[] buckets;
	
	// Constructor
	public MyHashMap() {
		buckets = new ArrayList[10];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayList<Entry>();
		}
	}
	
	// Returns an integer value of the given variable name
	public int Hash(String variableName) {
		int total = 0;
		String temp = variableName.toLowerCase();
		for (int i = 0; i < temp.length(); i++) {
			total += variableName.charAt(i);
		}
		return total % 10;
	}
	
	// Add a variable to the bucket
	public void AddEntry(int bucketIndex, String variableName) {
		Entry e = new Entry(variableName, "");
		buckets[bucketIndex].add(e);
	}
	
	// Read a variable from the bucket
	public void ReadEntry(int bucketIndex, String variableName) {
		for (Entry e: buckets[bucketIndex]) {
			if (e.key.equals(variableName)) {
				System.out.println(e.value);
				return;
			}
		}
		throw new RuntimeException("Can not find variable " + variableName);
	}
	
	// Set a value to a variable
	public void SetEntry(int bucketIndex, String variableName, String value) {
		for (Entry e: buckets[bucketIndex]) {
			if (e.key.equals(variableName)) {
				e.value = value;
				return;
			}
		}
		throw new RuntimeException("Can not find variable " + variableName);
	}
}
