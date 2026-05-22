import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class MyFileHandler {
	// fields will be package-protected for now
	int line;
	File laoFile;
	Scanner scanobj;
	
	// Getters
	public File getFile() {
		return laoFile;
	}
	
	// Important methods
	public void AssignFile(String path) {
		laoFile = new File(path);
	}
	
	public void OpenFile(File laoFile) {
		try {
			scanobj = new Scanner(laoFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		}
	}
}