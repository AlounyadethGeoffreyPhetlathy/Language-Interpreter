import java.util.ArrayList;
public class MyMemory {
	ArrayList<IfParser> IfObjects = new ArrayList<IfParser>();
	
	public void CreateIf(int start, int end, ArrayList<Token> condition) {
		IfObjects.add(new IfParser(start, end, condition));
	}
	
	public IfParser SearchIf(int start) {
		for (int i = 0; i < IfObjects.size(); i++) {
			if (IfObjects.get(i).GetStartBlock() == start) return IfObjects.get(i);
		}
		return null;
	}
}
