/* Implemented Stack using LinkedList */
import singlyLL.*;

public class LLStack<T> {

  //   private Node top;
  //   private int size;
  HSinglyLL<T> stack;

  public LLStack() {
    stack = new HSinglyLL<>();
    // this.top = null;
    // this.size = size;
  }

  public void push(T item) {
    stack.addFirst(item);
  }

  public T pop() {
    if (stack.isEmpty()) {
      return null;
    } else {
      T item = stack.getHead().data;
      stack.removeFirst();
      return item;
    }
  }

  public T peek() {
    if (stack.isEmpty()) {
      return null;
    } else {
      T item = stack.getHead().data;
      return item;
    }
  }

  public void display() {
    if (stack.isEmpty()) {
      System.out.println("Stack is Empty !");
    } else {
      stack.newDisplay();
    }
  }

  public static void main(String[] args) {
    LLStack<String> stack = new LLStack<>();
    // stack.push("I");
    // stack.display();
    // stack.push("am");
    // stack.display();
    // stack.push("Harry");
    // stack.display();
    // stack.push("!");
    // stack.display();
    // for (int i = 0; i < 3; i++) {
    //   System.out.println("Deleted Item: " + stack.pop());
    // }
    // stack.display();

    stack.push("I");
    stack.push("am");
    stack.push("Harry");
    stack.push("!");
    stack.display();
    while (!stack.stack.isEmpty()) {
      System.out.println("Deleted Item: " + stack.pop());
    }
  }
}
