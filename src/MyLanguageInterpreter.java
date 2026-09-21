import java.util.ArrayList;
public class MyLanguageInterpreter {
	// Important classes
	MyFileHandler mfh;
	MyHashMap mhm;
	
	// Fields
	public ArrayList<ArrayList<String>> codeLines;
	private int numLine = 0;
	private Commands com;
	
	// Constructor
	public MyLanguageInterpreter() {
		mfh = new MyFileHandler();
		mhm = new MyHashMap();
	}
	
	// Setting up the text file, path is hardcoded for easier testing
	public void SetUp() {
		codeLines = mfh.ProcessFile("C:/JavaLessons/Language Interpreter/src/Test.txt");
	}
	
	// Main loop
	public void InterpretLoop() {
		while (numLine < codeLines.size()) {
			SearchForCommand(codeLines.get(numLine));
			numLine++;
		}
	}
	
	// Command check
	private void SearchForCommand(ArrayList<String> line) {
		if (line.size() != 0) com = Commands.Execute(line.get(0));
		ArrayList<String> multi = new ArrayList<String>();
		switch (com.GetCommand()) {
		case "ຄວາມຄິດເຫັນ": // Comment
			break;
		case "": // Empty line
			break;
		case "ຂຽນ": // Print to console
			if (line.size() > 2) throw new RuntimeException("Print command has too many arugments");
			PrintCommand(line.get(1));
			break;
		case "ສ້າງ": // Create variable
			if (line.size() > 2) throw new RuntimeException("Create variable command has too many arugments");
			CreateVariableCommand(line.get(1));
			break;
		case "ອ່ານ": // Read variable
			if (line.size() > 2) throw new RuntimeException("Read variable command has too many arugments");
			ReadVariableCommand(line.get(1));
			break;
		case "ເກັບໄວ້ໃນ": // Set variable
			if (line.size() > 3) throw new RuntimeException("Set variable command has too many arugments");
			SetVariableCommand(line.get(1), line.get(2));
			break;
		case "ປະກອບ": // Concatenate
			for (int i = 2; i < line.size(); i++) multi.add(line.get(i));
			ConcatenateCommand(line.get(1), multi);
			break;
		case "ລວມກັນ": // Combine
			for (int i = 2; i < line.size(); i++) multi.add(line.get(i));
			CombineCommand(line.get(1), multi);
			break;
		}
	}
	
	// Commands
	private void PrintCommand(String msg) {
		System.out.println(msg);
	}
	
	private void CreateVariableCommand(String varName) {
		mhm.AddEntry(mhm.Hash(varName), varName);
	}
	
	private void ReadVariableCommand(String variableName) {
		mhm.ReadEntry(mhm.Hash(variableName), variableName);
	}
	
	private void SetVariableCommand(String variableName, String value) {
		mhm.SetEntry(mhm.Hash(variableName), variableName, value);
	}
	
	private void ConcatenateCommand(String variableName, ArrayList<String> strings) {
		String finalValue = "";
		for (String s: strings) {
			finalValue += s;
		}
		mhm.SetEntry(mhm.Hash(variableName), variableName, finalValue);
	}
	
	private void CombineCommand(String variableName, ArrayList<String> variables) {
		ArrayList<Integer> bucketIndexes = new ArrayList<Integer>();
		ArrayList<String> variableValues = new ArrayList<String>();
		String finalValue = "";
		for (String s: variables) {
			bucketIndexes.add(mhm.Hash(s));
		}
		for (int i = 0; i < variables.size(); i++) {
			variableValues.add(mhm.GetEntry(bucketIndexes.get(i), variables.get(i)).value);
		}
		for (String value: variableValues) {
			finalValue += value;
		}
		mhm.SetEntry(mhm.Hash(variableName), variableName, finalValue);
	}
}