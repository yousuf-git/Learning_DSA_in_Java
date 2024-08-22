// Implementing stack using 2 queues

package queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class TwoQueueStack<T> {

    Queue<T> queue1;
    Queue<T> queue2;

    TwoQueueStack() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }

    // Adds the element on top of stack
    public void push(T item) {
        if (queue1.isEmpty()) {
            queue2.add(item);
        } else {
            queue1.add(item);
        }

    }

    public boolean isEmpty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    // Removes the element on top of stack
    public T pop() {
        if (this.isEmpty()) {
            return null;
        }

        T item = null;
        if (queue1.isEmpty()) {
            // Shift all elements from queue2 to queue1
            while (queue2.size() != 1) {
                queue1.add(queue2.remove());
            }
            item = queue2.remove();
        } else {
            // Shift all elements from queue1 to queue2
            while (queue1.size() != 1) {
                queue2.add(queue1.remove());
            }
            item = queue1.remove();
        }
        return item;
    }

    // Returns the element on top of stack
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }

        T item = null;
        if (queue1.isEmpty()) {
            // Shift all elements from queue2 to queue1
            while (queue2.size() != 1) {
                queue1.add(queue2.remove());
            }
            item = queue2.peek(); // the last element
        } else {
            // Shift all elements from queue1 to queue2
            while (queue1.size() != 1) {
                queue2.add(queue1.remove());
            }
            item = queue1.peek(); // the last element
        }
        return item; // return the last element
    }

    public static void main(String[] args) {
        TwoQueueStack<Integer> stack = new TwoQueueStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Peek: " + stack.peek());

        while (!stack.isEmpty()) {
            System.out.println("Element: " + stack.pop());
        }
    }
}
