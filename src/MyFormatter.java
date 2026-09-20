import java.util.ArrayList;
public class MyFormatter {
	// Fields
	ArrayList<String> instructionsData = new ArrayList<String>();
	
	public ArrayList<String> FormatLine(String s) {
		ArrayList<String> finalArray = new ArrayList<String>();
		boolean valueDelimiter = false;
		boolean spaceDelimiter = false;
		String st = "";
		int count = 0;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '\"') { // start of value
				valueDelimiter = !valueDelimiter;
				if (!st.isEmpty()) { // end of value
					instructionsData.add(st);
					st = "";
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
				instructionsData.add(st);
				spaceDelimiter = true;
				st = "";
			}
		}
		if (!st.isEmpty()) instructionsData.add(st);
		if (valueDelimiter) throw new RuntimeException("Missing closing quotes");
		if (instructionsData.size() == 0) instructionsData.add("");
//		for (int i = 0; i < instructionsData.size(); i++) {
//			System.out.println(i + ": " + instructionsData.get(i));
//		}
		while (!instructionsData.isEmpty()) {
			finalArray.add(instructionsData.get(0));
			instructionsData.remove(0);
			count++;
		}
		return finalArray;
	}
}