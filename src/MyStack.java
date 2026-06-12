import java.util.ArrayList;
public class MyStack {
	private ArrayList<IParser> stack = new ArrayList<IParser>();
	
	public void Push(IParser object) {
		stack.add(object);
	}
	
	public void Pop() {
		stack.removeLast();
	}
	
	// Will get replaced by Peek, probably, but this is for better debugging
	public void ReadStack() {
		System.out.println(stack.size());
		for (IParser item: stack) {
			System.out.println(item);
		}
	}
	
	public int StackSize() {
		return stack.size();
	}
}