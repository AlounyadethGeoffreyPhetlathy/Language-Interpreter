import java.util.ArrayList;
public class MyLanguageInterpreter {
	// Important classes
	MyFileHandler mfh;
	
	// Fields
	public ArrayList<String> codeLines;
	private int line;
	
	// Constructor
	public MyLanguageInterpreter() {
		mfh = new MyFileHandler();
	}
	
	// Setting up the text file, path is hardcoded for easier testing
	public void SetUp() {
//		mfh.AssignFile("C:/JavaLessons/Language Interpreter/src/Test.txt");
//		mfh.OpenFile(mfh.GetFile());
	}
}