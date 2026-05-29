import java.util.ArrayList;
public class MyHashMap {
	// Fields
	ArrayList<Entry>[] buckets;
//	private int value;
	
	// Constructor
	public MyHashMap() {
		buckets = new ArrayList[10];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayList<Entry>();
		}
	}
	
	// Methods
	public int Hash(String variableName) {
		int total = 0;
		String temp = variableName.toLowerCase();
		for (int i = 0; i < temp.length(); i++) {
			total += CharToNumber(temp.charAt(i));
		}
		return total % 10;
	}
	
	public void AddEntry(int bucketIndex, String variableName) {
		Entry e = new Entry(variableName, "");
		buckets[bucketIndex].add(e);
	}
	
	public void ReadEntry(int bucketIndex, String variableName) {
		for (Entry e: buckets[bucketIndex]) {
			if (e.key.equals(variableName)) {
				System.out.println(e.value);
				return;
			}
		}
		throw new RuntimeException("Can not find variable " + variableName);
	}
	
	public void SetEntry(int bucketIndex, String variableName, String value) {
		for (Entry e: buckets[bucketIndex]) {
			if (e.key.equals(variableName)) {
				e.value = value;
				return;
			}
		}
		throw new RuntimeException("Can not find variable " + variableName);
	}
	
	public Entry GetEntry(int bucketIndex, String variableName) {
		for (Entry e: buckets[bucketIndex]) {
			if (e.key.equals(variableName)) {
				return e;
			}
		}
		return null;
	}
	
	private int CharToNumber(char c) {
		switch(c) {
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		case 'i':
			return 8;
		case 'j':
			return 9;
		case 'k':
			return 10;
		case 'l':
			return 11;
		case 'm':
			return 12;
		case 'n':
			return 13;
		case 'o':
			return 14;
		case 'p':
			return 15;
		case 'q':
			return 16;
		case 'r':
			return 17;
		case 's':
			return 18;
		case 't':
			return 19;
		case 'u':
			return 20;
		case 'v':
			return 21;
		case 'w':
			return 22;
		case 'x':
			return 23;
		case 'y':
			return 24;
		case 'z':
			return 25;
		default:
			throw new RuntimeException("Invalid character in name");
		}
	}
}