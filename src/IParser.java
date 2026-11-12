import java.util.ArrayList;
public interface IParser {
	// GOD HELP ME
	
	public boolean EvaluateCondition(MyHashMap mhmInstance, ArrayList<Token> condition);
	
	public int GetStartBlock();
	
	public int GetEndBlock();
	
}