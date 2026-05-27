public class MyLanguageInterpreter {
	// Fields
	private MyHashMap mhm;
	Commands com;
	
	// Constructor
	public MyLanguageInterpreter() {
		mhm = new MyHashMap();
	}
	
	// Methods
	public void CheckStatement(String line) {
		String[] splitLine = line.split(" ");
		com = Commands.Execute(splitLine[0]);
		switch (splitLine[0]) {
		case "ຂຽນ": // Write To Console
			String input = "";
			for (int i = 1; i < splitLine.length; i++) {
				input += splitLine[i] + " ";
			}
			WriteCommand(input);
			break;
		case "ສ້າງ": // Create Variable
			CreateVariableCommand(splitLine[1]);
			break;
		case "ອ່ານ":
			ReadVariableCommand(splitLine[1]);
			break;
		}
	}
	
	private void WriteCommand(String input) {
		System.out.println(input);
	}
	
	private void CreateVariableCommand(String variableName) {
		int bucketIndex = mhm.Hash(variableName);
		mhm.AddEntry(bucketIndex, variableName);
	}
	
	private void ReadVariableCommand(String variableName) {
		int bucketIndex = mhm.Hash(variableName);
	}
}