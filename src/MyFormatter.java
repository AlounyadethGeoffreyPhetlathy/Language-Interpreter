import java.util.ArrayList;
public class MyFormatter {
	// Fields
	ArrayList<Token> instructionsData = new ArrayList<Token>();
	final String[] KEYWORDS = {"ເທົ່າກັບ", "ບໍ່ເທົ່າກັບ", "ນ້ອຍກວ່າ", "ນ້ອຍກວ່າຫຼືເທົ່າກັນ", "ຫຼາຍກວ່າ", "ຫຼາຍກວ່າຫຼືເທົ່າກັບ", "ແລະ", "ຫຼື", "ບໍ່ແມ່ນ", "(", ")", "ເລກບວກ", "ເລກລົບ", "ເລກຄູນ", "ເລກຫານ", "ສ່ວນທີ່ເຫຼືອ"};
	final String[] COMMANDS = {"ອ່ານ", "ໃນຂະນະທີ່", "{", "}"};
	
	public ArrayList<Token> FormatLine(String s) {
		ArrayList<Token> finalArray = new ArrayList<Token>();
		boolean valueDelimiter = false;
		boolean spaceDelimiter = false;
		boolean getCom = false;
		boolean emptyString = false;
		String st = "";
//		int count = 0;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '\"') { // start of value
				valueDelimiter = !valueDelimiter;
				if (i != 0) if (s.charAt(i-1) == '\"') emptyString = true;
				if (!st.isEmpty() || emptyString) { // end of value
					instructionsData.add(new Token(st, "VALUE"));
					st = "";
					emptyString = false;
				}
			}
			else if (valueDelimiter) { // add character in value
				st += s.charAt(i);
			}
			else if (s.charAt(i) != '\s' && s.charAt(i) != '\t') { // text character
				st += s.charAt(i);
				spaceDelimiter = false;
			}
			else if ((s.charAt(i) == '\s' || s.charAt(i) == '\t') && !spaceDelimiter && !st.isEmpty()) { // store characters
				if (!getCom)  {
					instructionsData.add(new Token(st, "COMMAND"));
					getCom = true;
				}
				else if (CheckForCommand(st)) instructionsData.add(new Token(st, "COMMAND"));
				else if (CheckForKeyword(st)) instructionsData.add(new Token(st, "KEYWORD"));
				else instructionsData.add(new Token(st, "VARIABLE"));
				spaceDelimiter = true;
				st = "";
			}
		}
		if (!st.isEmpty()) {
			if (CheckForCommand(st)) instructionsData.add(new Token(st, "COMMAND"));
			else if (CheckForKeyword(st)) instructionsData.add(new Token(st, "KEYWORD"));
			else instructionsData.add(new Token(st, "VARIABLE"));
		}
		if (valueDelimiter) throw new RuntimeException("Missing closing quotes");
		if (instructionsData.size() == 0) instructionsData.add(new Token(st, "COMMAND"));
//		for (int i = 0; i < instructionsData.size(); i++) {
//			System.out.println(i + ": " + instructionsData.get(i).GetType() + " " + instructionsData.get(i).GetValue());
//		}
		while (!instructionsData.isEmpty()) {
			finalArray.add(instructionsData.get(0));
			instructionsData.remove(0);
//			count++;
		}
		return finalArray;
	}
	
	private boolean CheckForKeyword(String word) {
		for (String keyword: KEYWORDS) {
			if (word.equals(keyword)) return true;
		}
		return false;
	}
	
	private boolean CheckForCommand(String word) {
		for (String command: COMMANDS) {
			if (word.equals(command)) return true;
		}
		return false;
	}
}