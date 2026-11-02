import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class MyFileHandler {
	// Fields
	private Scanner scanobj;
	private MyFormatter mf;
	private ArrayList<ArrayList<Token>> formattedInstructions;
	
	// Constructor
	public MyFileHandler() {
		mf = new MyFormatter();
		formattedInstructions = new ArrayList<ArrayList<Token>>();
	}
	
	// Read and format all lines into a "memory"
	public ArrayList<ArrayList<Token>> ProcessFile(String fileName) {
		try {
			scanobj = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanobj.hasNextLine()) {
			formattedInstructions.add(mf.FormatLine(scanobj.nextLine()));
		}
		scanobj.close();
		
		return formattedInstructions;
	}
}