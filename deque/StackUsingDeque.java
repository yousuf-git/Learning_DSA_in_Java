package deque;
import java.util.ArrayDeque;
import java.util.Deque;
// import java.util.LinkedList;

public class StackUsingDeque<T> {
    Deque<T> deque;

    StackUsingDeque() {
        deque = new ArrayDeque<>();
        // deque = new LinkedList<>();     can be used
    }

    public boolean push(T element) {
        return deque.offerLast(element);
    }

    public T pop() {
        return deque.removeLast();
    }

    public T peek() {
        return deque.getLast();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public String toString() {
        return deque.toString();
    }

    public static void main(String[] args) {
        StackUsingDeque<Integer> stack = new StackUsingDeque<>();
        stack.push(1);
        stack.push(12);
        stack.push(31);

        System.out.println("First Element: " + stack.peek());
        System.out.println(stack);

        System.out.println("Removing Elements.......");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
