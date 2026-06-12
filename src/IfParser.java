import java.util.ArrayList;
public class IfParser implements IParser{
	// Fields
	private final String trueKeyword = "ຈິງ";
	private final String falseKeyword = "ຜິດ";
//	private final ArrayList<String> condition;
	
	// Reconstruct the condition and evaluate it
	public String EvaluateIf(ArrayList<String> inputCondition) {
		for (int i = 0; i < inputCondition.size(); i++) {
			if (inputCondition.get(i).equals(trueKeyword)) inputCondition.set(i, "true");
			if (inputCondition.get(i).equals(falseKeyword)) inputCondition.set(i, "false");
		}
		
//		for (String s: inputCondition) System.out.println(s);
		
		return inputCondition.get(0);
	}
}