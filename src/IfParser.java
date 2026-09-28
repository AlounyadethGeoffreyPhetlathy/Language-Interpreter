import java.util.ArrayList;

public class IfParser implements IParser {
	// Fields
	private final int start;
	private final int end;
	private final ArrayList<String> condition;
	private boolean result;
	
	public IfParser(int start, int end, ArrayList<String> condition) {
		this.start = start;
		this.end = end;
		this.condition = condition;
	}
	
	public boolean EvaluateCondition() {
		// Forewarn: most complicated expression evaluation
		
		// Count comparison tokens
//		for (String item: condition) {
//			if (item.equals("ເທົ່າກັບ")) System.out.println("I need a better system");
//			else System.out.println("So much suffering");
//		}
		
		// Split down expression
		switch (condition.get(0)) {
		case "ຈິງ":
			result = true;
			break;
		case "ຜິດ":
			result = false;
			break;
		default:
			System.out.println("Something went very wrong evaluating if condition");
			break;
		}
		return result;
	}
	
	public int GetStartBlock() {
		return start;
	}
	
	public int GetEndBlock() {
		return end;
	}
}