/* Implemented Stack using ArrayList */

import java.util.ArrayList;

public class ALStack {

  private int top;
  private ArrayList<String> stack;

  ALStack() {
    stack = new ArrayList<String>();
    top = -1;
  }

  public boolean isEmpty() {
    if (top == -1) return true; else return false;
  }

  public void push(String item) {
    top += 1;
    stack.add(top, item);
  }

  public String pop() {
    if (isEmpty()) {
      return null;
    } else {
      String item = stack.get(top);
      top -= 1;
      return item;
    }
  }

  public String peek() {
    if (isEmpty()) {
      return null;
    } else {
      return stack.get(top);
    }
  }

  public static void main(String[] args) {
    ALStack stack = new ALStack();
    stack.push("I");
    stack.push("am");
    stack.push("harry");
    System.out.println("\nValue on top: " + stack.peek());
    while (!stack.isEmpty()) {
      System.out.println("\nDeleted Item: " + stack.pop());
    }
  }
}
