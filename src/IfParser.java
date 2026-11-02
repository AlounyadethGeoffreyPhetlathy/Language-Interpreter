import java.util.ArrayList;

public class IfParser implements IParser {
	// Fields
	private final int start;
	private final int end;
	private ArrayList<Token> condition;
	private boolean result;
	
	public IfParser(int start, int end, ArrayList<Token> inputCondition) {
		this.start = start;
		this.end = end;
		condition = ParseCondition(inputCondition);
	}
	
	@Override
	public boolean EvaluateCondition(MyHashMap mhmInstance) {
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
		
		// Evaluate comparisons second
		tokenIndex = 0;
		String valueSTR1 = "";
		String valueSTR2 = "";
		while (tokenIndex < condition.size()) {
			switch (condition.get(tokenIndex).GetType()) {
			case "EQUALS_STRING":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (valueSTR1.equals(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "EQUALS_INTEGER":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Integer.parseInt(valueSTR1) == Integer.parseInt(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "EQUALS_FLOAT":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Float.parseFloat(valueSTR1) == Float.parseFloat(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "NOTEQUALS_STRING":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (!valueSTR1.equals(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "NOTEQUALS_INTEGER":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Integer.parseInt(valueSTR1) != Integer.parseInt(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "NOTEQUALS_FLOAT":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Float.parseFloat(valueSTR1) != Float.parseFloat(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "LESSTHAN_INTEGER":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Integer.parseInt(valueSTR1) < Integer.parseInt(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "LESSTHAN_FLOAT":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Float.parseFloat(valueSTR1) < Float.parseFloat(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "LESSOREQUAL_INTEGER":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Integer.parseInt(valueSTR1) <= Integer.parseInt(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "LESSOREQUAL_FLOAT":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Float.parseFloat(valueSTR1) <= Float.parseFloat(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "GREATERTHAN_INTEGER":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Integer.parseInt(valueSTR1) > Integer.parseInt(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "GREATERTHAN_FLOAT":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Float.parseFloat(valueSTR1) > Float.parseFloat(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "GREATEROREQUAL_INTEGER":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Integer.parseInt(valueSTR1) >= Integer.parseInt(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				break;
			case "GREATEROREQUAL_FLOAT":
				valueSTR1 = condition.get(tokenIndex-1).GetValue();
				valueSTR2 = condition.get(tokenIndex+1).GetValue();
				if (Float.parseFloat(valueSTR1) >= Float.parseFloat(valueSTR2)) {
					condition.set(tokenIndex-1, new Token("ຈິງ", "BOOLEAN"));
					condition.remove(tokenIndex);
					condition.remove(tokenIndex);
				}
				else {
					condition.set(tokenIndex-1, new Token("ຜິດ", "BOOLEAN"));
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
	
	private ArrayList<Token> ParseCondition(ArrayList<Token> unformattedCondition) {
		ArrayList<Token> formattedCondition = new ArrayList<Token>();
		for (int i = 0; i < unformattedCondition.size(); i++) {
			if (unformattedCondition.get(i).GetType().equals("COMMAND")) {
				switch(unformattedCondition.get(i).GetValue()) {
				case "ອ່ານ": // Read variable
					if (unformattedCondition.get(i+1).GetType() != "VARIABLE") throw new RuntimeException("Invalid command argument for READ_VARIABLE");
					formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "READ_VARIABLE"));
					i++;
					break;
				}
			}
			else if (unformattedCondition.get(i).GetType().equals("KEYWORD")) {
				switch(unformattedCondition.get(i).GetValue()) {
				case "ເທົ່າກັບ": // ==
					if (!(unformattedCondition.get(i+1).GetValue().equals("STRING") || unformattedCondition.get(i+1).GetValue().equals("INTEGER") || unformattedCondition.get(i+1).GetValue().equals("FLOAT"))) throw new RuntimeException("Invalid keyword argument for EQUALS");
					if (unformattedCondition.get(i+1).GetValue().equals("STRING")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "EQUALS_STRING"));
					else if (unformattedCondition.get(i+1).GetValue().equals("INTEGER")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "EQUALS_INTEGER"));
					else if (unformattedCondition.get(i+1).GetValue().equals("FLOAT")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "EQUALS_FLOAT"));
					i++;
					break;
				case "ບໍ່ເທົ່າກັບ": // !=
					if (!(unformattedCondition.get(i+1).GetValue().equals("STRING") || unformattedCondition.get(i+1).GetValue().equals("INTEGER") || unformattedCondition.get(i+1).GetValue().equals("FLOAT"))) throw new RuntimeException("Invalid keyword argument for NOTEQUALS");
					if (unformattedCondition.get(i+1).GetValue().equals("STRING")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "NOTEQUALS_STRING"));
					else if (unformattedCondition.get(i+1).GetValue().equals("INTEGER")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "NOTEQUALS_INTEGER"));
					else if (unformattedCondition.get(i+1).GetValue().equals("FLOAT")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "NOTEQUALS_FLOAT"));
					i++;
					break;
				case "ນ້ອຍກວ່າ": // <
					if (!(unformattedCondition.get(i+1).GetValue().equals("INTEGER") || unformattedCondition.get(i+1).GetValue().equals("FLOAT"))) throw new RuntimeException("Invalid keyword argument for LESSTHAN");
					if (unformattedCondition.get(i+1).GetValue().equals("INTEGER")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "LESSTHAN_INTEGER"));
					else if (unformattedCondition.get(i+1).GetValue().equals("FLOAT")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "LESSTHAN_FLOAT"));
					i++;
					break;
				case "ນ້ອຍກວ່າຫຼືເທົ່າກັນ": // <=
					if (!(unformattedCondition.get(i+1).GetValue().equals("INTEGER") || unformattedCondition.get(i+1).GetValue().equals("FLOAT"))) throw new RuntimeException("Invalid keyword argument for LESSOREQUAL");
					if (unformattedCondition.get(i+1).GetValue().equals("INTEGER")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "LESSOREQUAL_INTEGER"));
					else if (unformattedCondition.get(i+1).GetValue().equals("FLOAT")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "LESSOREQUAL_FLOAT"));
					i++;
					break;
				case "ຫຼາຍກວ່າ": // >
					if (!(unformattedCondition.get(i+1).GetValue().equals("INTEGER") || unformattedCondition.get(i+1).GetValue().equals("FLOAT"))) throw new RuntimeException("Invalid keyword argument for GREATERTHAN");
					if (unformattedCondition.get(i+1).GetValue().equals("INTEGER")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "GREATERTHAN_INTEGER"));
					else if (unformattedCondition.get(i+1).GetValue().equals("FLOAT")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "GREATERTHAN_FLOAT"));
					i++;
					break;
				case "ຫຼາຍກວ່າຫຼືເທົ່າກັບ": // >=
					if (!(unformattedCondition.get(i+1).GetValue().equals("INTEGER") || unformattedCondition.get(i+1).GetValue().equals("FLOAT"))) throw new RuntimeException("Invalid keyword argument for GREATEROREQUAL");
					if (unformattedCondition.get(i+1).GetValue().equals("INTEGER")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "GREATEROREQUAL_INTEGER"));
					else if (unformattedCondition.get(i+1).GetValue().equals("FLOAT")) formattedCondition.add(new Token(unformattedCondition.get(i+1).GetValue(), "GREATEROREQUAL_FLOAT"));
					i++;
					break;
				}
			}
			else {
				formattedCondition.add(unformattedCondition.get(i));
			}
		}
		return formattedCondition;
	}
	
	public int GetStartBlock() {
		return start;
	}
	
	public int GetEndBlock() {
		return end;
	}
}