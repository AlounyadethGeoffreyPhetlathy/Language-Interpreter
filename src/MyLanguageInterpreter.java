import java.util.ArrayList;
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
		String input = "";
		ArrayList<String> multi = new ArrayList<String>();
		com = Commands.Execute(splitLine[0]);
		switch (splitLine[0]) {
		case "ຂຽນ": // Write To Console
			for (int i = 1; i < splitLine.length; i++) {
				input += splitLine[i] + " ";
			}
			WriteCommand(input);
			break;
		case "ສ້າງ": // Create Variable
			CreateVariableCommand(splitLine[1]);
			break;
		case "ອ່ານ": // Read variable
			ReadVariableCommand(splitLine[1]);
			break;
		case "ຊຸດ": // Set variable
			for (int i = 2; i < splitLine.length; i++) {
				input += splitLine[i] + " ";
			}
			SetVariableCommand(splitLine[1], input.substring(0, input.length()-1));
			break;
		case "ເຊື່ອມຕໍ່": // Concatenate
			for (int i = 2; i < splitLine.length; i++) {
				if (splitLine[i].equals("ຕໍ່ໄປ")) {
					multi.add(input.substring(0, input.length()-1));
					input = "";
				}
				else {
					input += splitLine[i];
					input += " ";
				}
			}
			multi.add(input.substring(0, input.length()-1));
			ConcatenateCommand(splitLine[1], multi);
			break;
		case "ລວມກັນ": // Combine
			for (int i = 2; i < splitLine.length; i++) {
				if (splitLine[i].equals("ຕໍ່ໄປ")) {
					multi.add(input.substring(0, input.length()-1));
					input = "";
				}
				else {
					input += splitLine[i];
					input += " ";
				}
			}
			multi.add(input.substring(0, input.length()-1));
			CombineCommand(splitLine[1], multi);
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
		mhm.ReadEntry(bucketIndex, variableName);
	}
	
	private void SetVariableCommand(String variableName, String value) {
		int bucketIndex = mhm.Hash(variableName);
		mhm.SetEntry(bucketIndex, variableName, value);
	}
	
	private void ConcatenateCommand(String variableName, ArrayList<String> strings) {
		int bucketIndex = mhm.Hash(variableName);
		String finalString = "";
		for (String s: strings) {
			finalString += s;
		}
		mhm.ConcatenateEntry(bucketIndex, variableName, finalString);
	}
	
	private void CombineCommand(String variableResult, ArrayList<String> variables) {
		ArrayList<Integer> bucketIndexes = new ArrayList<Integer>();
		int bucketIndex = mhm.Hash(variableResult);
		bucketIndexes.add(bucketIndex);
		for (String s: variables) {
			bucketIndex = mhm.Hash(s);
			bucketIndexes.add(bucketIndex);
		}
		mhm.CombineEntry(bucketIndexes, variableResult, variables);
	}
}