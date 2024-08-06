// A general Stack implementation in Java using an array:

@SuppressWarnings("unchecked")
public class HStack<T> {

	T[] stack;

	private int size;
	private int top;

	HStack() {
		top = -1;
		size = 10; // default size
		stack = (T[]) new Object[size];
	}

	HStack(int size) {
		this.size = size;
		stack = (T[]) new Object[size];
		top = -1;
		System.out.println("Stack of Size " + this.size + " initiated...\n");
	}

	/*------- To Remove element from the top ------------
	 * Time Complexity: O(1)
	 */
	public T pop() {
		if (top == -1) {
			// System.out.println("Stack underflow !");
			return null;
		} else {
			T item = stack[top]; // retrieve item from top
			top--; // update top
			return item; // return item
		}
	}

	/*------- To Add element at the top ------------
	 * Time Complexity: O(1)
	 */
	public boolean push(T item) {
		if (top == this.stack.length - 1) {
			return false;
		} else {
			top++;
			this.stack[top] = item;
			return true;
			// System.out.println("Push Successful");
		}
	}

	/*------ To get the last added element --------
	 * Time Complexity: O(1)
	 */
	public T peek() {
		if (isEmpty()) {
			// System.out.println("stack empty");
			return null;
		}
		return this.stack[top];
	}

	/* -------To Display the current elements in stack--------- */
	public void display() {
		if (top == -1) {
			System.out.println("No Elements in stack to display");
		} else {
			for (int i = top; i >= 0; i--) {
				if (i == top) {
					System.out.println("Index " + i + ": " + stack[i] + " (Top)");
				} else {
					System.out.println("Index " + i + ": " + stack[i]);
				}
			}
			// System.out.println("Display Successful");
		}
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top + 1 == size;
	}

	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			return top + 1;
		}
	}
}
