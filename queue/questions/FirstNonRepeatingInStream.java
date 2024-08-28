/*
Q: Print First non repeating letter in stream of string (contains lowercase only)

Example:

Input: "xxbcbcy"

Stream      First Non-repeating Letter
x                   x
xx                 -1                  since x is now repeating
xxb                 b
xxbc                b                  c is second non repeating, we need first
xxbcb               c
xxbcbc              -1                 both b and c are now repeating
xxbcbcy             y

Output: x -1 b b c -1 y

Approach:

- Maintain a freq[26] / count[26] array to store frequecncy of each character
- Store the elements in a queue as they arrive
- Remove the elements from the queue if their frequency is > 1, untill queue is gone empty
- If queue is empty, there is no such non-repeating character -> print -1

 */

package queue.questions;

import java.util.ArrayDeque;
import java.util.Queue;

public class FirstNonRepeatingInStream {

    public static void main(String[] args) {
        System.out.println();
        String input = "xxbcbcy";

        // System.out.println((int)'a' - 97); // 'a' = 97 , to store it at first index
        // 'a' - 97
        int[] freq = new int[26];

        Queue<Character> queue = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {

            // System.out.println(queue);

            char ch = input.charAt(i);
            queue.add(ch);

            // System.out.println(ch);

            int index = ch - 97;
            freq[index]++; // update frequency of current char

            while (!queue.isEmpty() && freq[queue.peek() - 97] > 1) {
                queue.remove();
            }

            if (queue.isEmpty()) {
                System.out.print(-1 + " ");
            } else {
                System.out.print(queue.peek() + " ");
            }
        }

        System.out.println("\n");
    }

}


// Time Complexity: O(n)        --> Iterate for each character once