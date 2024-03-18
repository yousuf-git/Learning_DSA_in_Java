/* Look at this at the end, first see the other implementations by ArrayList and LinkedList in order to understand properly  */

import java.util.Stack;

public class BuiltInStack {

  public static void main(String[] args) {
    Stack<String> stack = new Stack<>();
    stack.push("harry");
    stack.push("is");
    stack.push("here");

    System.out.println("\nItem on Peek / Top: " + stack.peek());
    while (!stack.empty()) {
      System.out.println("\nDeleted Item: " + stack.pop());
    }
  }
}
