import java.util.ArrayList;
public class MyStack {
	private ArrayList<Object> stack = new ArrayList<Object>();
	
	public void Push(Object item) {
		stack.addLast(item);
	}
	
	public void Pop() {
		stack.removeLast();
	}
	
	public void Peek() {
		System.out.println(stack.getLast());
	}
	
	public void Execute() {
		Object item = stack.getLast();
		if (item instanceof IfParser) {
//			((IfParser) item).Run();
		}
	}
}