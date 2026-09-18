import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class MyFileHandler {
	// Fields
	private Scanner scanobj;
	private MyFormatter mf;
	private ArrayList<ArrayList<String>> formattedInstructions;
	
	// Constructor
	public MyFileHandler() {
		mf = new MyFormatter();
		formattedInstructions = new ArrayList<ArrayList<String>>();
	}
	
	// Read and format all lines into a "memory"
	public ArrayList<ArrayList<String>> ProcessFile(String fileName) {
		try {
			scanobj = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanobj.hasNextLine()) {
//			formattedInstructions.get(0) = mf.FormatLine(scanobj.nextLine());
			formattedInstructions.add(mf.FormatLine(scanobj.nextLine()));
		}
		
		return formattedInstructions;
	}
}