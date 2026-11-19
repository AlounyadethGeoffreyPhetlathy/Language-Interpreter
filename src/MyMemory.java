import java.util.ArrayList;
public class MyMemory {
	ArrayList<IfParser> IfObjects = new ArrayList<IfParser>();
	ArrayList<WhileParser> WhileObjects = new ArrayList<WhileParser>();
	
	public void CreateIf(int start, int end, ArrayList<Token> condition) {
		IfObjects.add(new IfParser(start, end, condition));
	}
	
	public IfParser SearchIf(int start) {
		for (int i = 0; i < IfObjects.size(); i++) {
			if (IfObjects.get(i).GetStartBlock() == start) return IfObjects.get(i);
		}
		return null;
	}
	
	public void CreateWhile(int start, int end, ArrayList<Token> condition) {
		WhileObjects.add(new WhileParser(start, end, condition));
	}
	
	public WhileParser SearchWhile(int start) {
		for (int i = 0; i < WhileObjects.size(); i++) {
			if (WhileObjects.get(i).GetStartBlock() == start) return WhileObjects.get(i);
		}
		return null;
	} 
}
