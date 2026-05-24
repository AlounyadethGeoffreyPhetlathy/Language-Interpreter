import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class MyFileHandler {
	// fields will be package-protected for now
	private int line;
	private File laoFile;
	private Scanner scanobj;
	
	// Getters
	public File GetFile() {
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
	
	public String CurrentLine() {
		return scanobj.nextLine();
	}
	
	public boolean CanReadNextLine() {
		return scanobj.hasNextLine();
	}
	
	public void CloseFile() {
		scanobj.close();
	}
}