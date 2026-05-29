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
		switch (com.GetCommand()) {
		case "ຄວາມຄິດເຫັນ": // Comment, do nothing
			break;
		case "ຂຽນ": // Write to console
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
			multi.add(0, splitLine[1]);
			CombineCommand(multi);
			break;
		case "ຕົວເລກ": // Operations with whole numbers
			NumberCommand(splitLine[1], splitLine[2], splitLine[4], splitLine[3]);
			break;
		case "ຕົວເລກສິບ":
			DecimalCommand(splitLine[1], splitLine[2], splitLine[4], splitLine[3]);
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
		Entry e = mhm.GetEntry(bucketIndex, variableName);
		String finalString = "";
		for (String s: strings) {
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
		case "ເພີ່ມເຕີມ":
			n = Integer.parseInt(mhm.GetEntry(bucketIndexes.get(1), variable1).value) + Integer.parseInt(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ຖອດ":
			n = Integer.parseInt(mhm.GetEntry(bucketIndexes.get(1), variable1).value) - Integer.parseInt(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ເພີ່ມທະວີຂຶ້ນ":
			n = Integer.parseInt(mhm.GetEntry(bucketIndexes.get(1), variable1).value) * Integer.parseInt(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ແບ່ງແຍກ":
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
		case "ເພີ່ມເຕີມ":
			n = Float.parseFloat(mhm.GetEntry(bucketIndexes.get(1), variable1).value) + Float.parseFloat(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ຖອດ":
			n = Float.parseFloat(mhm.GetEntry(bucketIndexes.get(1), variable1).value) - Float.parseFloat(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ເພີ່ມທະວີຂຶ້ນ":
			n = Float.parseFloat(mhm.GetEntry(bucketIndexes.get(1), variable1).value) * Float.parseFloat(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ແບ່ງແຍກ":
			n = Float.parseFloat(mhm.GetEntry(bucketIndexes.get(1), variable1).value) / Float.parseFloat(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		case "ສ່ວນທີ່ເຫຼືອ":
			n = Float.parseFloat(mhm.GetEntry(bucketIndexes.get(1), variable1).value) % Float.parseFloat(mhm.GetEntry(bucketIndexes.get(2), variable2).value);
			mhm.SetEntry(bucketIndexes.get(0), variableResult, String.valueOf(n));
			break;
		}
	}
}