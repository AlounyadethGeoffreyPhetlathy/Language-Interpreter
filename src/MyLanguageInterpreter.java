import java.util.ArrayList;
public class MyLanguageInterpreter {
	// Important classes
	MyFileHandler mfh;
	
	// Fields
	public ArrayList<ArrayList<String>> codeLines;
	private int line = 0;
	private Commands com;
	
	// Constructor
	public MyLanguageInterpreter() {
		mfh = new MyFileHandler();
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
		case "ຂຽນ":
			if (line.size() > 2) throw new RuntimeException("print command has too many arugments");
			PrintCommand(line.get(1));
			break;
		}
	}
	
	// Commands
	private void PrintCommand(String msg) {
		System.out.println(msg);
	}
}