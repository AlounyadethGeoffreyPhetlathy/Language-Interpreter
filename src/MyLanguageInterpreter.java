import java.util.ArrayList;
public class MyLanguageInterpreter {
	// Important classes
	MyFileHandler mfh;
	MyHashMap mhm;
	
	// Fields
	public ArrayList<ArrayList<String>> codeLines;
	private int line = 0;
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
		while (line < codeLines.size()) {
			SearchForCommand(codeLines.get(line));
			line++;
		}
	}
	
	// Command check
	private void SearchForCommand(ArrayList<String> line) {
		if (line.size() != 0) com = Commands.Execute(line.get(0));
		switch (com.GetCommand()) {
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
		case "ເກັບໄວ້ໃນ":
			if (line.size() > 3) throw new RuntimeException("Set variable command has too many arugments");
			SetVariableCommand(line.get(1), line.get(2));
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
}