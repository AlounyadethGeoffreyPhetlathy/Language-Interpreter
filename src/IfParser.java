import java.util.ArrayList;
public class IfParser {
	// Fields
	private final String trueKeyword = "ຈິງ";
	private final String falseKeyword = "ຜິດ";
	
	// Reconstruct the condition and evaluate it
	public String EvaluateIf(ArrayList<String> inputCondition) {
		for (int i = 0; i < inputCondition.size(); i++) {
			if (inputCondition.get(i).equals(trueKeyword)) inputCondition.set(i, "true");
			if (inputCondition.get(i).equals(falseKeyword)) inputCondition.set(i, "false");
		}
		
//		for (String s: inputCondition) System.out.println(s);
		
		return inputCondition.get(1);
	}
}