import java.util.ArrayList;
public class MyLanguageInterpreter {
	// Fields
	private MyFileHandler mfh;
	private MyHashMap mhm;
	private MyFormatter mf;
	private IfParser iparser;
	Commands com;
	ArrayList<String> instructions;
	private int scope = 0;
	
	// Constructor
	public MyLanguageInterpreter() {
		mfh = new MyFileHandler();
		mhm = new MyHashMap();
		mf = new MyFormatter();
		iparser = new IfParser();
	}
	
	public void SetUp() {
		// Setting up the text file, path is hardcoded for easier testing
		mfh.AssignFile("C:/JavaLessons/Language Interpreter/src/Test.txt");
		mfh.OpenFile(mfh.GetFile());
	}
	
	public void InterpreterLoop() {
		// Main Loop
		while (mfh.CanReadNextLine()) {
			String line = mfh.CurrentLine();
			
			if (scope != 0 && line.equals("}")) {
				scope--;
			}
			else {
				CheckStatement(line);
			}
		}
		
		// End Reading File/Interpreting File
		mfh.CloseFile();
	}
	
	// Methods
	public void CheckStatement(String line) {
		instructions = mf.FormatLine(line);
		ArrayList<String> multi = new ArrayList<String>();
		com = Commands.Execute(instructions.get(0));
		switch (com.GetCommand()) {
		case "ຄວາມຄິດເຫັນ": // For the following, do nothing
		case "{":
		case "}":
		case "":
			break;
		case "ຂຽນ": // Write to console
			if (instructions.size() > 2) throw new RuntimeException("Print command has two many arguments");
			WriteCommand(instructions.get(1));
			break;
		case "ສ້າງ": // Create Variable
			CreateVariableCommand(instructions.get(1));
			break;
		case "ອ່ານ": // Read variable
			ReadVariableCommand(instructions.get(1));
			break;
		case "ເກັບໄວ້ໃນ": // Set variable
			SetVariableCommand(instructions.get(1), instructions.get(2));
			break;
		case "ປະກອບ": // Concatenate
			for (int i = 2; i < instructions.size(); i++) {
				multi.add(instructions.get(i));
			};
			ConcatenateCommand(instructions.get(1), multi);
			break;
		case "ລວມກັນ": // Combine
			for (int i = 1; i < instructions.size(); i++) {
				multi.add(instructions.get(i));
			}
			CombineCommand(multi);
			break;
		case "ຕົວເລກ": // Operations with whole numbers
			NumberCommand(instructions.get(1), instructions.get(2), instructions.get(4), instructions.get(3));
			break;
		case "ຕົວເລກສິບ": // Operations with decimal numbers
			DecimalCommand(instructions.get(1), instructions.get(2), instructions.get(4), instructions.get(3));
			break;
		case "ຖ້າວ່າ": // If statement
			for (int i = 1; i < instructions.size(); i++) {
				multi.add(instructions.get(i));
			}
			IfCommand(multi);
			break;
		}
		mf.ClearData();
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
		mhm.ReadEntry(bucketIndex, variableName);
	}
	
	private void SetVariableCommand(String variableName, String value) {
		int bucketIndex = mhm.Hash(variableName);
		mhm.SetEntry(bucketIndex, variableName, value);
	}
	
	private void ConcatenateCommand(String variableName, ArrayList<String> strings) {
		int bucketIndex = mhm.Hash(variableName);
		Entry e = mhm.GetEntry(bucketIndex, variableName);
		String finalString = "";
		for (String s: strings) {
//			System.out.println(s);
			finalString += s;
		}
		mhm.SetEntry(bucketIndex, variableName, finalString);
	}
	
	private void CombineCommand(ArrayList<String> variables) {
		ArrayList<Integer> bucketIndexes = new ArrayList<Integer>();
		ArrayList<String> strings = new ArrayList<String>();
		String finalString = "";
		for (String s: variables) {
			bucketIndexes.add(mhm.Hash(s));
		}
		for (int i = 1; i < variables.size(); i++) {
			strings.add(mhm.GetEntry(bucketIndexes.get(i), variables.get(i)).value);
		}
		for (String s: strings) {
			finalString += s;
		}
		mhm.SetEntry(bucketIndexes.get(0), variables.get(0), finalString);
	}
	
	private void NumberCommand(String variableResult, String variable1, String variable2, String operation) {
		ArrayList<Integer> bucketIndexes = new ArrayList<Integer>();
		bucketIndexes.add(mhm.Hash(variableResult));
		bucketIndexes.add(mhm.Hash(variable1));
		bucketIndexes.add(mhm.Hash(variable2));
		int n;
		switch (operation) {
		case "ເລກບວກ":
			n = Integer.parseInt(mhm.GetEntry(bucketIndexes.get(1), variable1).value) + Integer.parseInt(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ເລກລົບ":
			n = Integer.parseInt(mhm.GetEntry(bucketIndexes.get(1), variable1).value) - Integer.parseInt(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ເລກຄູນ":
			n = Integer.parseInt(mhm.GetEntry(bucketIndexes.get(1), variable1).value) * Integer.parseInt(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ເລກຫານ":
			n = Integer.parseInt(mhm.GetEntry(bucketIndexes.get(1), variable1).value) / Integer.parseInt(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ສ່ວນທີ່ເຫຼືອ":
			n = Integer.parseInt(mhm.GetEntry(bucketIndexes.get(1), variable1).value) % Integer.parseInt(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		}
	}
	
	private void DecimalCommand(String variableResult, String variable1, String variable2, String operation) {
		ArrayList<Integer> bucketIndexes = new ArrayList<Integer>();
		bucketIndexes.add(mhm.Hash(variableResult));
		bucketIndexes.add(mhm.Hash(variable1));
		bucketIndexes.add(mhm.Hash(variable2));
		float n;
		switch (operation) {
		case "ເລກບວກ":
			n = Float.parseFloat(mhm.GetEntry(bucketIndexes.get(1), variable1).value) + Float.parseFloat(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ເລກລົບ":
			n = Float.parseFloat(mhm.GetEntry(bucketIndexes.get(1), variable1).value) - Float.parseFloat(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ເລກຄູນ":
			n = Float.parseFloat(mhm.GetEntry(bucketIndexes.get(1), variable1).value) * Float.parseFloat(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ເລກຫານ":
			n = Float.parseFloat(mhm.GetEntry(bucketIndexes.get(1), variable1).value) / Float.parseFloat(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ສ່ວນທີ່ເຫຼືອ":
			n = Float.parseFloat(mhm.GetEntry(bucketIndexes.get(1), variable1).value) % Float.parseFloat(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		}
	}
	
	private void IfCommand(ArrayList<String> boolString) {
//		iparser.EvaluateIf(boolString);
		
		switch(boolString.get(1)) {
		case "ຈິງ":
			
			break;
		case "ຜິດ":
			break;
		}
	}
}