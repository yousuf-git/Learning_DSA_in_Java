/* -------Practice Questions for stack-------- */
import java.util.Scanner;
import java.util.Stack;

public class StackQs {

  public static void pushBottom(Stack<String> stack, String item) {
    if (stack.empty()) {
      stack.push(item); // item to be added at bottom
      return;
    } else {
      String currItem = stack.pop();
      pushBottom(stack, item);
      stack.push(currItem);
    }
  }
  public static void reverseStk(Stack<String> stack){
    if (stack.empty()) {
      return;
    } else{
      String item = stack.pop();
      reverseStk(stack);
      pushBottom(stack, item); // push top element at bottom
    }
  }

  public static void main(String[] args) {
    // Q1: Push at the bottom of stack
    // Initializing stack with 3 values
    Stack<String> stack = new Stack<String>();
    stack.push("7");
    stack.push("8");
    stack.push("9");

    System.out.print("\nEnter Item to put on bottom: ");
    Scanner input = new Scanner(System.in);
    String item = input.nextLine();
    pushBottom(stack, item);
    System.out.println("\nItems in stack: ");
    while (!stack.empty()) {
      System.out.println(stack.pop());
    }
    stack.push("3");
    stack.push("2");
    stack.push("1"); // top

    /* Q2: Reverse the stack */
    reverseStk(stack);

    System.out.println("\nReversed stack: ");
    while (!stack.empty()) {
      System.out.println(stack.pop());
    }

    



    input.close();
  }
}
