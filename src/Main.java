public class Main {
	public static void main(String[] args) {
		// Create all necessary objects
		MyFileHandler mfh = new MyFileHandler();
		
		// Setting up the text file
		mfh.AssignFile("./Test.txt");
		System.out.println(mfh.laoFile.exists());
//		mfh.OpenFile(mfh.getFile());
		
		// Test writeline
		System.out.println(mfh.laoFile);
	}

}