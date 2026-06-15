import java.util.ArrayList;
public class IfParser implements IParser{
	// Fields
	private final ArrayList<String> condition;
	private boolean isExecuted;
	
	public IfParser(ArrayList<String> condition) {
		this.condition = condition;
	}
	
	// Reconstruct the condition and evaluate it
	public void EvaluateCondition() {
//		for (int i = 0; i < condition.size(); i++) {
//			if (condition.get(i).equals("ຈິງ")) condition.set(i, "true");
//			if (condition.get(i).equals("ຜິດ")) condition.set(i, "false");
//		}
		
		if (condition.get(0).equals("ຈິງ")) isExecuted = true;
		if (condition.get(0).equals("ຜິດ")) isExecuted = false;
	}
	
	public boolean GetExecuteStatus() {
		return isExecuted;
	}
}