package deque;
import java.util.ArrayDeque;
import java.util.Deque;
// import java.util.LinkedList;

public class QueueUsingDeque<T> {
    Deque<T> deque;

    QueueUsingDeque() {
        deque = new ArrayDeque<>();
        // deque = new LinkedList<>();     can be used
    }

    public boolean add(T element) {
        return deque.offerLast(element);
    }

    public T remove() {
        return deque.removeFirst();
    }

    public T peek() {
        return deque.getFirst();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public String toString() {
        return deque.toString();
    }

    public static void main(String[] args) {
        QueueUsingDeque<Integer> queue = new QueueUsingDeque<>();
        queue.add(1);
        queue.add(12);
        queue.add(31);

        System.out.println("First Element: " + queue.peek());
        System.out.println(queue);

        System.out.println("Removing Elements.......");
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }
}
