public class MyLanguageInterpreter {
	public void CheckStatement(String line) {
		String[] splitLine = line.split(" ");
		switch (splitLine[0]) {
		case "ຂຽນ": // Write
			String input = "";
			for (int i = 1; i < splitLine.length; i++) {
				input += splitLine[i] + " ";
			}
			WriteCommand(input);
		}
	}
	
	private void WriteCommand(String input) {
		System.out.println(input);
	}
}