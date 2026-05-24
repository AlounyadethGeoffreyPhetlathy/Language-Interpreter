public class Main {
	public static void main(String[] args) {
		// Create all necessary objects
		MyFileHandler mfh = new MyFileHandler();
		MyLanguageInterpreter mli = new MyLanguageInterpreter();
		
		// Setting up the text file, path is hardcoded for easier testing
		mfh.AssignFile("C:/JavaLessons/Language Interpreter/src/Test.txt");
		mfh.OpenFile(mfh.GetFile());
		
		// Testing
		while (mfh.CanReadNextLine()) {
			String line = mfh.CurrentLine();
			mli.CheckStatement(line);
		}
		
		// End Reading File/Interpreting File
		mfh.CloseFile();
	}
}