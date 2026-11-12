import java.util.ArrayList;

public class MyExpressionParser {
	public static ArrayList<Token> ParseCondition(ArrayList<Token> unformattedCondition) {
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
					if (unformattedCondition.get(i+1).GetValue().equals("STRING")) formattedCondition.add(new Token("STRING", "EQUALS_STRING"));
					else if (unformattedCondition.get(i+1).GetValue().equals("INTEGER")) formattedCondition.add(new Token("INTEGER", "EQUALS_INTEGER"));
					else if (unformattedCondition.get(i+1).GetValue().equals("FLOAT")) formattedCondition.add(new Token("FLOAT", "EQUALS_FLOAT"));
					i++;
					break;
				case "ບໍ່ເທົ່າກັບ": // !=
					if (!(unformattedCondition.get(i+1).GetValue().equals("STRING") || unformattedCondition.get(i+1).GetValue().equals("INTEGER") || unformattedCondition.get(i+1).GetValue().equals("FLOAT"))) throw new RuntimeException("Invalid keyword argument for NOTEQUALS");
					if (unformattedCondition.get(i+1).GetValue().equals("STRING")) formattedCondition.add(new Token("STRING", "NOTEQUALS_STRING"));
					else if (unformattedCondition.get(i+1).GetValue().equals("INTEGER")) formattedCondition.add(new Token("INTEGER", "NOTEQUALS_INTEGER"));
					else if (unformattedCondition.get(i+1).GetValue().equals("FLOAT")) formattedCondition.add(new Token("FLOAT", "NOTEQUALS_FLOAT"));
					i++;
					break;
				case "ນ້ອຍກວ່າ": // <
					if (!(unformattedCondition.get(i+1).GetValue().equals("INTEGER") || unformattedCondition.get(i+1).GetValue().equals("FLOAT"))) throw new RuntimeException("Invalid keyword argument for LESSTHAN");
					if (unformattedCondition.get(i+1).GetValue().equals("INTEGER")) formattedCondition.add(new Token("INTEGER", "LESSTHAN_INTEGER"));
					else if (unformattedCondition.get(i+1).GetValue().equals("FLOAT")) formattedCondition.add(new Token("FLOAT", "LESSTHAN_FLOAT"));
					i++;
					break;
				case "ນ້ອຍກວ່າຫຼືເທົ່າກັນ": // <=
					if (!(unformattedCondition.get(i+1).GetValue().equals("INTEGER") || unformattedCondition.get(i+1).GetValue().equals("FLOAT"))) throw new RuntimeException("Invalid keyword argument for LESSOREQUAL");
					if (unformattedCondition.get(i+1).GetValue().equals("INTEGER")) formattedCondition.add(new Token("INTEGER", "LESSOREQUAL_INTEGER"));
					else if (unformattedCondition.get(i+1).GetValue().equals("FLOAT")) formattedCondition.add(new Token("FLOAT", "LESSOREQUAL_FLOAT"));
					i++;
					break;
				case "ຫຼາຍກວ່າ": // >
					if (!(unformattedCondition.get(i+1).GetValue().equals("INTEGER") || unformattedCondition.get(i+1).GetValue().equals("FLOAT"))) throw new RuntimeException("Invalid keyword argument for GREATERTHAN");
					if (unformattedCondition.get(i+1).GetValue().equals("INTEGER")) formattedCondition.add(new Token("INTEGER", "GREATERTHAN_INTEGER"));
					else if (unformattedCondition.get(i+1).GetValue().equals("FLOAT")) formattedCondition.add(new Token("FLOAT", "GREATERTHAN_FLOAT"));
					i++;
					break;
				case "ຫຼາຍກວ່າຫຼືເທົ່າກັບ": // >=
					if (!(unformattedCondition.get(i+1).GetValue().equals("INTEGER") || unformattedCondition.get(i+1).GetValue().equals("FLOAT"))) throw new RuntimeException("Invalid keyword argument for GREATEROREQUAL");
					if (unformattedCondition.get(i+1).GetValue().equals("INTEGER")) formattedCondition.add(new Token("INTEGER", "GREATEROREQUAL_INTEGER"));
					else if (unformattedCondition.get(i+1).GetValue().equals("FLOAT")) formattedCondition.add(new Token("FLOAT", "GREATEROREQUAL_FLOAT"));
					i++;
					break;
				case "ແລະ": // &&
					formattedCondition.add(new Token("&&", "AND"));
					break;
				case "ຫຼື": // ||
					formattedCondition.add(new Token("||", "OR"));
					break;
				case "(":
					formattedCondition.add(new Token("(", "OPEN_PARENTHESE"));
					break;
				case ")":
					formattedCondition.add(new Token(")", "CLOSE_PARENTHESE"));
					break;
					
				}
			}
			else {
				formattedCondition.add(unformattedCondition.get(i));
			}
		}
		return formattedCondition;
	}
}
