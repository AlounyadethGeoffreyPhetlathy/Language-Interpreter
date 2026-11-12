import java.util.ArrayList;

public class IfParser implements IParser {
	// Fields
	private final int start;
	private final int end;
	private ArrayList<Token> ifCondition;
	private boolean result;
	
	public IfParser(int start, int end, ArrayList<Token> inputCondition) {
		this.start = start;
		this.end = end;
		ifCondition = MyExpressionParser.ParseCondition(inputCondition);
	}
	
	@Override
	public boolean EvaluateCondition(MyHashMap mhmInstance, ArrayList<Token> condition) {
		int bracketCount = 0;
		int startPar = -1;
		int endPar =  -1;
		String value = "";
		int tokenIndex = 0;
		
		// Evaluate commands first
		while (tokenIndex < condition.size()) {
			switch (condition.get(tokenIndex).GetType()) {
			case "READ_VARIABLE":
				value = mhmInstance.GetEntry(mhmInstance.Hash(condition.get(tokenIndex).GetValue()), condition.get(tokenIndex).GetValue()).value;
				condition.set(tokenIndex, new Token(value, "VALUE"));
				break;
			}
			tokenIndex++;
		}
		
		// Evaluate parentheses second
		tokenIndex = 0;
		while (tokenIndex < condition.size()) {
			switch (condition.get(tokenIndex).GetType()) {
			case "OPEN_PARENTHESE":
				bracketCount++;
				if (startPar == -1) {
					startPar = tokenIndex;
				}
				break;
			case "CLOSE_PARENTHESE":
				bracketCount--;
				if (endPar == -1 && startPar != -1 && bracketCount == 0) {
					endPar = tokenIndex;
				}
				break;
			}
			if (startPar != -1 && endPar != -1) {
				ArrayList<Token> subCondition = new ArrayList<Token>();
				for (int i = startPar+1; i < endPar; i++) {
					subCondition.add(condition.get(i));
				}
				Token subResult = EvaluateCondition(mhmInstance, subCondition) ? new Token("ຈິງ", "VALUE") : new Token("ຜິດ", "VALUE");
				condition.set(startPar, subResult);
				for (int i = startPar+1; i <= endPar; i++) {
					condition.remove(startPar+1);
				}
				tokenIndex = startPar;
				startPar = -1;
				endPar = -1;
			}
			tokenIndex++;
		}
		
		// Evaluate comparisons third
		tokenIndex = 0;
		String valueSTR1 = "";
		String valueSTR2 = "";
		while (tokenIndex < condition.size()) {
			switch (condition.get(tokenIndex).GetType()) {
			case "EQUALS_STRING":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (valueSTR1.equals(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "EQUALS_INTEGER":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Integer.parseInt(valueSTR1) == Integer.parseInt(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "EQUALS_FLOAT":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Float.parseFloat(valueSTR1) == Float.parseFloat(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "NOTEQUALS_STRING":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (!valueSTR1.equals(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "NOTEQUALS_INTEGER":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Integer.parseInt(valueSTR1) != Integer.parseInt(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "NOTEQUALS_FLOAT":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Float.parseFloat(valueSTR1) != Float.parseFloat(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "LESSTHAN_INTEGER":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Integer.parseInt(valueSTR1) < Integer.parseInt(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "LESSTHAN_FLOAT":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Float.parseFloat(valueSTR1) < Float.parseFloat(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "LESSOREQUAL_INTEGER":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Integer.parseInt(valueSTR1) <= Integer.parseInt(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "LESSOREQUAL_FLOAT":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Float.parseFloat(valueSTR1) <= Float.parseFloat(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "GREATERTHAN_INTEGER":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Integer.parseInt(valueSTR1) > Integer.parseInt(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "GREATERTHAN_FLOAT":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Float.parseFloat(valueSTR1) > Float.parseFloat(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "GREATEROREQUAL_INTEGER":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Integer.parseInt(valueSTR1) >= Integer.parseInt(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "GREATEROREQUAL_FLOAT":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Float.parseFloat(valueSTR1) >= Float.parseFloat(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			}
			tokenIndex++;
		}
		
		// Evaulate logical AND forth
		tokenIndex = 0;
		Token booltoken1;
		Token booltoken2;
		while (tokenIndex < condition.size()) {
			switch (condition.get(tokenIndex).GetType()) {
			case "AND":
				booltoken1 = condition.get(tokenIndex-1);
				booltoken2 = condition.get(tokenIndex+1);
				if (booltoken1.GetValue().equals("ຈິງ") && booltoken2.GetValue().equals("ຈິງ")) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			}
			tokenIndex++;
		}
		
		// Evaluate logical OR fifth
		while (tokenIndex < condition.size()) {
			switch (condition.get(tokenIndex).GetType()) {
			case "OR":
				booltoken1 = condition.get(tokenIndex-1);
				booltoken2 = condition.get(tokenIndex+1);
				if (booltoken1.GetValue().equals("ຈິງ") || booltoken2.GetValue().equals("ຈິງ")) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "VALUE"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			}
			tokenIndex++;
		}
		
		// Final value
		switch (condition.get(0).GetValue()) {
		case "ຈິງ":
			result = true;
			break;
		case "ຜິດ":
			result = false;
			break;
		}
		
		// Return the boolean value
		return result;
	}
	
	public ArrayList<Token> getIfCondition() {
		return ifCondition;
	}
	
	public int GetStartBlock() {
		return start;
	}
	
	public int GetEndBlock() {
		return end;
	}
}