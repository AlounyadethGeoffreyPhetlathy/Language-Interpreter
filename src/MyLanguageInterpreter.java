import java.util.ArrayList;
public class MyLanguageInterpreter {
	// Important classes
	MyFileHandler mfh;
	MyHashMap mhm;
	MyMemory mm;
	
	// Fields
	public ArrayList<ArrayList<Token>> codeLines;
	private int globalLine = 0;
	private Commands com;
	
	// Constructor
	public MyLanguageInterpreter() {
		mfh = new MyFileHandler();
		mhm = new MyHashMap();
		mm = new MyMemory();
	}
	
	// Setting up the text file, path is hardcoded for easier testing
	public void SetUp() {
		codeLines = mfh.ProcessFile("C:/JavaLessons/Language Interpreter/src/Test.txt");
	}
	
	// Create IF, WHILE, and FOR objects
	public void ControlStructSetup() {
		while (globalLine < codeLines.size()) {
			SearchForControlStruct(codeLines.get(globalLine));
			globalLine++;
		}
		globalLine = 0;
	}
	
	// Main loop
	public void InterpretLoop() {
		while (globalLine < codeLines.size()) {
			SearchForCommand(codeLines.get(globalLine));
			globalLine++;
		}
	}
	
	// Control structures check
	private void SearchForControlStruct(ArrayList<Token> line) {
		int start = 0;
		int end = 0;
		int bracketCount = 1;
		int localLine = globalLine;
		ArrayList<Token> multi = new ArrayList<Token>();
		if (line.size() != 0) com = Commands.Execute(line.get(0).GetValue());
		switch (com.GetCommand()) {
		case "ຖ້າວ່າ":
			localLine+=1;
			if (!codeLines.get(localLine).get(0).GetValue().equals("{")) throw new RuntimeException("Invalid IF declaration");
			start = localLine;
			localLine+=1;
			while (localLine < codeLines.size() && bracketCount != 0) {
				if (codeLines.get(localLine).get(0).GetValue().equals("{")) bracketCount++;
				else if (codeLines.get(localLine).get(0).GetValue().equals("}")) bracketCount--;
				localLine++;
			}
			if (bracketCount != 0) throw new RuntimeException("Curly bracket missing");
			else end = localLine-1;
			for (int i = 1; i < line.size(); i++) {
				multi.add(line.get(i));
			}
			mm.CreateIf(start, end, multi);
			break;
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	}
	
	// Command check
	private void SearchForCommand(ArrayList<Token> line) {
		if (line.size() != 0) com = Commands.Execute(line.get(0).GetValue());
		ArrayList<String> multi = new ArrayList<String>();
		switch (com.GetCommand()) {
		case "ຄວາມຄິດເຫັນ": // Comment
			break;
		case "": // Empty line
			break;
		case "{": // Left Bracket
			break;
		case "}": // Right Bracket
			break;
		case "ຂຽນ": // Print to console
			if (line.size() > 2) throw new RuntimeException("Print command has too many arugments");
			PrintCommand(line.get(1).GetValue());
			break;
		case "ສ້າງ": // Create variable
			if (line.size() > 2) throw new RuntimeException("Create variable command has too many arugments");
			CreateVariableCommand(line.get(1).GetValue());
			break;
		case "ອ່ານ": // Read variable
			if (line.size() > 2) throw new RuntimeException("Read variable command has too many arugments");
			ReadVariableCommand(line.get(1).GetValue());
			break;
		case "ເກັບໄວ້ໃນ": // Set variable
			if (line.size() > 3) throw new RuntimeException("Set variable command has too many arugments");
			SetVariableCommand(line.get(1).GetValue(), line.get(2).GetValue());
			break;
		case "ປະກອບ": // Concatenate
			for (int i = 2; i < line.size(); i++) multi.add(line.get(i).GetValue());
			ConcatenateCommand(line.get(1).GetValue(), multi);
			break;
		case "ລວມກັນ": // Combine
			for (int i = 2; i < line.size(); i++) multi.add(line.get(i).GetValue());
			CombineCommand(line.get(1).GetValue(), multi);
			break;
		case "ຖ້າວ່າ":
			IfCommand();
			break;
		default:
			throw new RuntimeException("Invalid command on line: " + globalLine);
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
	
	private void IfCommand() {
		IfParser ifObj = mm.SearchIf(globalLine+1);
		boolean result = ifObj.EvaluateCondition(mhm);
		if (result == true) globalLine = ifObj.GetStartBlock();
		else globalLine = ifObj.GetEndBlock();
	}
}