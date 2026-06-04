public class Main {
	public static void main(String[] args) {
		// Create all necessary objects
		MyLanguageInterpreter mli = new MyLanguageInterpreter();
		
		// Start the loop
		mli.SetUp();
		mli.InterpreterLoop();
	}
}