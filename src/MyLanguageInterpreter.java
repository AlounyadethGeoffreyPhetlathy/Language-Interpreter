import java.util.ArrayList;
import java.util.Scanner;
public class MyLanguageInterpreter {
	// Important classes
	MyFileHandler mfh;
	MyHashMap mhm;
	MyMemory mm;
	MyStack<IParser> ms;
	Scanner scn;
	
	// Fields
	public ArrayList<ArrayList<Token>> codeLines;
	private int globalLine = 0;
	private Commands com;
	
	// Constructor
	public MyLanguageInterpreter() {
		mfh = new MyFileHandler();
		mhm = new MyHashMap();
		mm = new MyMemory();
		ms = new MyStack<IParser>();
		scn = new Scanner(System.in);
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
		case "ໃນຂະນະທີ່":
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
			mm.CreateWhile(start, end, multi);
			break;
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	}
	
	// Command check
	private void SearchForCommand(ArrayList<Token> line) {
		if (line.size() != 0) com = Commands.Execute(line.get(0).GetValue());
//		ArrayList<String> multi = new ArrayList<String>();
		ArrayList<Token> tokenCollection = new ArrayList<Token>();
		switch (com.GetCommand()) {
		case "ຄວາມຄິດເຫັນ": // Comment
			break;
		case "": // Empty line
			break;
		case "{": // Left Bracket
			break;
		case "}": // Right Bracket
			if (ms.IsStackEmpty()) break; // Not an IF statement
			IParser ipar = ms.PeekItem();
			if (globalLine == ipar.GetEndBlock()) {
				globalLine = ipar.GetStartBlock()-2;
			}
			break;
		case "ຂຽນ": // Print to console
			if (line.size() > 2) throw new RuntimeException("Print command has too many arugments");
			PrintCommand(line.get(1).GetValue());
			break;
		case "ຂໍ້ມູນນໍາເຂົ້າ": // Input from console
			if (line.size() > 2) throw new RuntimeException("Input command has too many arguments");
			InputCommand(line.get(1).GetValue());
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
			for (int i = 2; i < line.size(); i++) tokenCollection.add(line.get(i));
			ConcatenateCommand(line.get(1).GetValue(), tokenCollection);
			break;
		case "ຕົວເລກ": // Math/Arithmetic
			for (int i = 2; i < line.size(); i++) tokenCollection.add(line.get(i));
			MathCommand(line.get(1).GetValue(), tokenCollection);
			break;
		// OLD CODE
//		case "ລວມກັນ": // Combine
//			for (int i = 2; i < line.size(); i++) multi.add(line.get(i).GetValue());
//			CombineCommand(line.get(1).GetValue(), multi);
//			break;
		case "ຖ້າວ່າ": // IF
			IfCommand();
			break;
		case "ໃນຂະນະທີ່": // WHILE
			WhileCommand();
			break;
		default:
			throw new RuntimeException("Invalid command on line: " + globalLine);
		}
	}
	
	// Commands
	private void PrintCommand(String msg) {
		System.out.println(msg);
	}
	
	private void InputCommand(String variableName) {
		String inValue = scn.nextLine();
		mhm.SetEntry(mhm.Hash(variableName), variableName, inValue);
	}
	
	private void CreateVariableCommand(String variableName) {
		mhm.AddEntry(mhm.Hash(variableName), variableName);
	}
	
	private void ReadVariableCommand(String variableName) {
		mhm.ReadEntry(mhm.Hash(variableName), variableName);
	}
	
	private void SetVariableCommand(String variableName, String value) {
		mhm.SetEntry(mhm.Hash(variableName), variableName, value);
	}
	
	private void ConcatenateCommand(String variableName, ArrayList<Token> tokenArgs) {
		String finalValue = "";
		for (Token t : tokenArgs) {
			if (t.GetType().equals("VARIABLE")) {
				finalValue += mhm.GetEntry(mhm.Hash(t.GetValue()), t.GetValue()).value;
			}
			else {
				finalValue += t.GetValue();
			}
		}
		mhm.SetEntry(mhm.Hash(variableName), variableName, finalValue);
	}
	
	private void MathCommand(String variableName, ArrayList<Token> tokenArgs) {
		boolean floatMode = false;
		int tokenIndex;
		
		for (int i = 0; i < tokenArgs.size(); i++) {
			if (tokenArgs.get(i).GetType().equals("VARIABLE")) {
				tokenArgs.set(i, new Token (mhm.GetEntry(mhm.Hash(tokenArgs.get(i).GetValue()), tokenArgs.get(i).GetValue()).value, "VALUE"));
			}
			else tokenArgs.set(i, tokenArgs.get(i));
		}
		
		for (Token t : tokenArgs) {
			if (t.GetType().equals("VALUE")) {
				if ((int)Float.parseFloat(t.GetValue()) != Float.parseFloat(t.GetValue())) {
					floatMode = true;
				}
			}
		}
		
		if (floatMode) {
			tokenIndex = 0;
			while (tokenIndex < tokenArgs.size()) {
				if (!tokenArgs.get(tokenIndex).GetType().equals("KEYWORD")) {
					tokenIndex++;
					continue;
				}
				switch (tokenArgs.get(tokenIndex).GetValue()) {
				case "ເລກຄູນ": // Float multiply
					tokenArgs.set(tokenIndex-1, new Token(Float.toString(Float.parseFloat(tokenArgs.get(tokenIndex-1).GetValue()) * Float.parseFloat(tokenArgs.get(tokenIndex+1).GetValue())), "VALUE"));
					tokenArgs.remove(tokenIndex);
					tokenArgs.remove(tokenIndex);
					tokenIndex--;
					break;
				case "ເລກຫານ": // Float divide
					tokenArgs.set(tokenIndex-1, new Token(Float.toString(Float.parseFloat(tokenArgs.get(tokenIndex-1).GetValue()) / Float.parseFloat(tokenArgs.get(tokenIndex+1).GetValue())), "VALUE"));
					tokenArgs.remove(tokenIndex);
					tokenArgs.remove(tokenIndex);
					tokenIndex--;
					break;
				case "ສ່ວນທີ່ເຫຼືອ": // Float remainder/modulo
					tokenArgs.set(tokenIndex-1, new Token(Float.toString(Float.parseFloat(tokenArgs.get(tokenIndex-1).GetValue()) % Float.parseFloat(tokenArgs.get(tokenIndex+1).GetValue())), "VALUE"));
					tokenArgs.remove(tokenIndex);
					tokenArgs.remove(tokenIndex);
					tokenIndex--;
					break;
				}
				tokenIndex++;
			}
			
			tokenIndex = 0;
			while (tokenIndex < tokenArgs.size()) {
				if (!tokenArgs.get(tokenIndex).GetType().equals("KEYWORD")) {
					tokenIndex++;
					continue;
				}
				switch (tokenArgs.get(tokenIndex).GetValue()) {
				case "ເລກບວກ": // Float add
					tokenArgs.set(tokenIndex-1, new Token(Float.toString(Float.parseFloat(tokenArgs.get(tokenIndex-1).GetValue()) + Float.parseFloat(tokenArgs.get(tokenIndex+1).GetValue())), "VALUE"));
					tokenArgs.remove(tokenIndex);
					tokenArgs.remove(tokenIndex);
					tokenIndex--;
					break;
				case "ເລກລົບ": // Float subtract
					tokenArgs.set(tokenIndex-1, new Token(Float.toString(Float.parseFloat(tokenArgs.get(tokenIndex-1).GetValue()) - Float.parseFloat(tokenArgs.get(tokenIndex+1).GetValue())), "VALUE"));
					tokenArgs.remove(tokenIndex);
					tokenArgs.remove(tokenIndex);
					tokenIndex--;
					break;
				}
				tokenIndex++;
			}
		}
		else {
			tokenIndex = 0;
			while (tokenIndex < tokenArgs.size()) {
				if (!tokenArgs.get(tokenIndex).GetType().equals("KEYWORD")) {
					tokenIndex++;
					continue;
				}
				switch (tokenArgs.get(tokenIndex).GetValue()) {
				case "ເລກຄູນ": // Integer multiply
					tokenArgs.set(tokenIndex-1, new Token(Integer.toString(Integer.parseInt(tokenArgs.get(tokenIndex-1).GetValue()) * Integer.parseInt(tokenArgs.get(tokenIndex+1).GetValue())), "VALUE"));
					tokenArgs.remove(tokenIndex);
					tokenArgs.remove(tokenIndex);
					tokenIndex--;
					break;
				case "ເລກຫານ": // Integer divide
					tokenArgs.set(tokenIndex-1, new Token(Integer.toString(Integer.parseInt(tokenArgs.get(tokenIndex-1).GetValue()) / Integer.parseInt(tokenArgs.get(tokenIndex+1).GetValue())), "VALUE"));
					tokenArgs.remove(tokenIndex);
					tokenArgs.remove(tokenIndex);
					tokenIndex--;
					break;
				case "ສ່ວນທີ່ເຫຼືອ": // Integer remainder/modulo
					tokenArgs.set(tokenIndex-1, new Token(Float.toString(Integer.parseInt(tokenArgs.get(tokenIndex-1).GetValue()) % Integer.parseInt(tokenArgs.get(tokenIndex+1).GetValue())), "VALUE"));
					tokenArgs.remove(tokenIndex);
					tokenArgs.remove(tokenIndex);
					tokenIndex--;
					break;
				}
				tokenIndex++;
			}
			
			tokenIndex = 0;
			while (tokenIndex < tokenArgs.size()) {
				if (!tokenArgs.get(tokenIndex).GetType().equals("KEYWORD")) {
					tokenIndex++;
					continue;
				}
				switch (tokenArgs.get(tokenIndex).GetValue()) {
				case "ເລກບວກ": // Integer add
					tokenArgs.set(tokenIndex-1, new Token(Integer.toString(Integer.parseInt(tokenArgs.get(tokenIndex-1).GetValue()) + Integer.parseInt(tokenArgs.get(tokenIndex+1).GetValue())), "VALUE"));
					tokenArgs.remove(tokenIndex);
					tokenArgs.remove(tokenIndex);
					tokenIndex--;
					break;
				case "ເລກລົບ": // Integer subtract
					tokenArgs.set(tokenIndex-1, new Token(Integer.toString(Integer.parseInt(tokenArgs.get(tokenIndex-1).GetValue()) - Integer.parseInt(tokenArgs.get(tokenIndex+1).GetValue())), "VALUE"));
					tokenArgs.remove(tokenIndex);
					tokenArgs.remove(tokenIndex);
					tokenIndex--;
					break;
				}
				tokenIndex++;
			}
		}
		
		mhm.SetEntry(mhm.Hash(variableName), variableName, tokenArgs.get(0).GetValue().toString());
	}
	
	@Deprecated
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
		ArrayList<Token> conditionCopy = new ArrayList<Token>(ifObj.getIfCondition());
		boolean result = ifObj.EvaluateCondition(mhm, conditionCopy);
		if (result == true) globalLine = ifObj.GetStartBlock();
		else globalLine = ifObj.GetEndBlock();
	}
	
	private void WhileCommand() {
		WhileParser whileObj = mm.SearchWhile(globalLine+1);
		ArrayList<Token> conditionCopy = new ArrayList<Token>(whileObj.getWhileCondition());
		boolean result = whileObj.EvaluateCondition(mhm, conditionCopy);
		if (result == true) {
			globalLine = whileObj.GetStartBlock();
			if (!ms.ContainsItem(whileObj)) ms.PushItem(whileObj);
		}
		else {
			ms.PopItem();
			globalLine = whileObj.GetEndBlock();
		}
	}
}