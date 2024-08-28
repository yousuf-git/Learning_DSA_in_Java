// Implementing queue using 2 stacks

package queue.questions;

import java.util.Stack;

public class TwoStackQueue<T> {

    Stack<T> stack1; // The actual elements will be here
    Stack<T> stack2; // It will help as a helper

    TwoStackQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void add(T item) {
        // 1. Remove items from stack 1 and push them in stack 2
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }

        // 2. Push item in stack 1
        stack1.push(item);

        // 3. Push items from stack 2 to stack 1 back
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    // Returns null if queue is empty
    public T remove() {
        if (stack1.empty()) {
            return null;
        }
        return stack1.pop();
    }

    public boolean isEmpty() {
        return stack1.empty();
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return stack1.peek();
    }

    // We need two stacks for it
    public static void main(String[] args) {
        TwoStackQueue<Integer> queue = new TwoStackQueue<>();

        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println("Peek: " + queue.peek());

        while (!queue.isEmpty()) {
            System.out.println("Element: " + queue.remove());
        }
    }
}
