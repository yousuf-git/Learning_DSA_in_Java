/*

Q: given an array of size n, Return an array nextGreater[] such that:

nextGreater[i] indicates instantly next greater element of array[i] in right side

If there is no element greater than array[i] on right side, nextGreater[i] = -1

Example: array = { 6, 8, 0, 1, 3, 5 }

output = {8, -1, 1, 3, 5, -1}

Explanation: 
-> array[0] = 6 and instantly next greater than 6 is 8 so output[0] = 8
-> array[1] = 8 and there is no element greater than 8 on right side so so output[1] = -1

*/

package Questions;

import java.util.Stack;

public class NextGreater {
    public static void main(String[] args) {
        int[] array = { 6, 8, 0, 1, 3, 5 };
        int[] nextGreater = new int[array.length];
        /**
         * Approach:
         * - Iterate in reverse order in array so that:
         *   we have track of elements i+1 to n-1 when we are at index i
         * - Maintain a stack which will have the last greater element than current element
         * 
         */

        Stack<Integer> stk = new Stack<>();
        for (int i = array.length - 1; i >= 0; i--) {
            // Remove from stack untill stack goes empty or we found a greater element
            while (!stk.empty() && stk.peek() < array[i]) {
                stk.pop();
            }
            // If satck went empty it means no element is greater on right side
            if (stk.empty()) {
                nextGreater[i] = -1;
            } else {
                nextGreater[i] = stk.peek(); // we removed all smaller elements stk.peek() is greater
            }
            stk.push(array[i]); // push current element in stack
        }

        // printing output
        for (int e : nextGreater) {
            System.out.print(e + " ");
        }
    }
}
