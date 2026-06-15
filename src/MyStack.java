import java.util.ArrayList;
public class MyStack {
	private ArrayList<IParser> stack = new ArrayList<IParser>();
	
	// Add IParser object to stack
	public void Push(IParser object) {
		stack.add(object);
	}
	
	// Remove IParser object to stack
	public void Pop() {
		stack.removeLast();
	}
	
	// Will get replaced by Peek, probably, but this is for better debugging
	public void ReadStack() {
		for (IParser item: stack) {
			System.out.println(item);
		}
	}
	
	public IParser Peek() {
		return stack.getLast();
	}
	
	public int StackSize() {
		return stack.size();
	}
}