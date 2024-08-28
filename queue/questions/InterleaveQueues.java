/*

Given: A queue of even size. Interleave first half with second half

Example:

Input = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

First Half = [1, 2, 3, 4, 5]
Second Half = [6, 7, 8, 9, 10]

Output: [1, 6, 2, 7, 3, 8, 4, 9, 5, 10]

Approach:

- Create a new array and add size/2 elements in it
- Now original array has second half and new array has first half
- Choose element one by by and start adding in original array

*/

package queue.questions;

import java.util.ArrayDeque;
import java.util.Queue;

public class InterleaveQueues {

    public static void main(String[] args) {
        
        // Prepare Input
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= 10; i++) {
            queue.add(i);
        }

        // Step 1: Prepare first half 
        Queue<Integer> firstHalf = new ArrayDeque<>();

        int limit = queue.size()/2;
        for (int i = 1; i <= limit; i++) {
            firstHalf.add(queue.remove());
        }

        // System.out.println(queue);
        // System.out.println(firstHalf);

        while (!firstHalf.isEmpty()) {
            queue.add(firstHalf.remove()); // one element from first half
            queue.add(queue.remove()); // one element from original 
        }

        System.out.println(queue);
    }

}
