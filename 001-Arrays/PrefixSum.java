/**

Calculate sub-arrays sum using prefix sum approach

_______________ Example _______________

array = {1,2,3,4,5}

prefix array = {1, 3, 6, 10, 15}        prefix[i] = sum of elements till i


Now Sub array sum = prefix[end] - prefix[start - 1]

Suppose we need sum of sub array {2, 3, 4}    => startIdx = 1, endIdx = 3

Use Formula:    prefix[3] - prefix[1 - 1] = 10 - 1 = 9

*/

public class PrefixSum {
    // Time Complexity: O(n^2)
    public static void subArraysSum(int[] array) {
        // Build prefix sum array
        int[] prefixSum = new int[array.length];
        prefixSum[0] = array[0];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + array[i]; // update according to prefix sum
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int start = i;
            for (int j = i; j < array.length; j++) {
                int end = j;
                // if start = 0 => prefix[start - 1] = prefix[-1]     in this case we use prefixSum[end] only
                int sum = start == 0 ? prefixSum[end] : prefixSum[end] - prefixSum[start - 1];
                System.out.println("Sum = " + sum);
                min = Math.min(min, sum);
                max = Math.max(max, sum);
            }
        }
        System.out.println("\nMin Sum = " + min);
        System.out.println("Max Sum = " + max);
    }

    /* Max Sub-Arrays sum using kadane's algorithm - O(n)
    
    Approach:
    - Iterate over array and calculate current sum
    - If current sum is going negative, make it 0
    - Will not work as it is if all values in array are negative
    
    */
    public static void kadanes(int[] array) {
        int currentSum = 0;
        int max = Integer.MIN_VALUE;
        int sum;
        for (int i = 0; i < array.length; i++) {
            sum = currentSum + array[i];
            currentSum = sum < 0 ? 0 : sum;
            max = Math.max(max, currentSum);
        }
        System.out.println("Max = " + max);
    }

    public static void main(String[] args) {
        System.out.println();
        int[] array = {1, -2, 6, -1, 3};
        // subArraysSum(array);
        kadanes(array);
        System.out.println();
    }
}
