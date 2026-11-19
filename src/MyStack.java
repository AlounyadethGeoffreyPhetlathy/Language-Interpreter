import java.util.ArrayList;
public class MyStack<T> {
	// Did a lesson on this in C#, what could be different in Java?
	private ArrayList<T> stack = new ArrayList<T>();
	
	// Add item of type T
	public void PushItem(T item) {
		stack.add(stack.size(), item);
	}
	
	// Remove last item of type T
	public T PopItem() {
		return stack.remove(stack.size()-1);
	}
	
	// See last item of type T
	public T PeekItem() {
		return stack.get(stack.size()-1);
	}
	
	// Checks if the stack is empty
	public boolean IsStackEmpty() {
		return stack.size() == 0 ? true : false;
	}
	
	// Checks for a reference to an existing object on the stack
	public boolean ContainsItem(T item) {
		boolean exists = false;
		for (T stackItem : stack) {
			if (stackItem == item) exists = true;
		}
		return exists;
	}
	
	// Gets stack size
	public int Size() {
		return stack.size();
	}
}
