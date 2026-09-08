import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class MyFileHandler {
	// Fields
	private Scanner scanobj;
	private MyFormatter mf;
	private ArrayList<String> FormattedInstructions;
	
	// Constructor
	public MyFileHandler() {
		mf = new MyFormatter();
		FormattedInstructions = new ArrayList<String>();
	}
	
	// Read all lines into "memory"
	public void ProcessFile(String fileName) {
		try {
			scanobj = new Scanner(new File(fileName));
			while (scanobj.hasNextLine()) {
				FormattedInstructions.add(mf.FormatLine(scanobj.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}