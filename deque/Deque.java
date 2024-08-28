// Dequeue: Remove element from the queue
// Deque: Interface like queue but in this, element can be added and removed from both sides
// Using built in Deque Interface here

package deque;

import java.util.ArrayDeque;

public class Deque {
    public static void main(String[] args) {
        java.util.Deque<Integer> deque = new ArrayDeque<>();
        
        // Methods

        // 1: Add
        
        deque.addLast(1);           // 1          
        deque.addFirst(2);          // 2 1
        deque.add(3);               // 2 1 3            Appends at the end
        System.out.println(deque);    // [2 1 3]
        // Add() Throws: IllegalStateException - if the element cannot be added at this time due to capacity restrictions

        // 2: Remove                                Current Deque: [2 1 3]
        
        System.out.println(deque.remove());         // 2            [1 3]
        System.out.println(deque.removeLast());     // 3            [1]
        System.out.println(deque.removeFirst());     // 1            []
        // Throws: NoSuchElementException - if this deque is empty

        // 3: Offer     Same as add() but returns true/false instead of throwing exception
        
        deque.offerLast(1);           // 1          
        deque.offerFirst(2);          // 2 1
        deque.offer(3);               // 2 1 3            Appends at the end
        System.out.println(deque);      // [2 1 3]

        // System.out.println(deque);

        // 4: Get
        deque.getFirst();
        deque.getLast();
        // Throws: NoSuchElementException - if this deque is empty
    }
}
