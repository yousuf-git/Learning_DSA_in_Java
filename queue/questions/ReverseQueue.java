/*

Input: Queue 
Output: Reversed Queue

Try yourself by using stack, if you can't in 10 min then see the solution

*/

package queue.questions;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class ReverseQueue {
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < 10; i++) {
            queue.add(new Random().nextInt(40));
        }
        System.out.println("Original Queue: " + queue);

        Stack<Integer> stack = new Stack<>();

        while (!queue.isEmpty()) {
            stack.push(queue.remove());
            // stack.add(queue.remove());
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
            // queue.add(stack.remove(stack.size()-1));
        }

        System.out.println("Reversed Queue: " + queue);

    }
}
