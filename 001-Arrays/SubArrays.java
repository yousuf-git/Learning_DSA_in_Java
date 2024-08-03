/**
Print Sub-Arrays of given array: Sub-array is contiguous part of array

__________ Example __________

Array: [1,2,3,4]

Sub-arrays:

[1], [1,2], [1,2,3], [1,2,3,4]
[2], [2,3], [2,3,4]
[3], [3,4]
[4]

*/

public class SubArrays {
    // Print all sub-arrays of given array
    public static void printSubArrays(int[] array) {
        int total = 0; // total sub arrays
        for (int i = 0; i < array.length; i++) {
            int start = i;
            for (int j = i; j < array.length; j++) {
                total++;
                int end = j;
                System.out.print("[ ");
                for (int k = start; k <= end; k++) {
                    System.out.print(array[k] + " ");
                }
                System.out.print("]");
            }
            System.out.println();
        }
        System.out.println("Total sub arrays: " + total);
    }

    // Print All sub-array's sums and also find min and max among them
    // Time Complexity: O(n^3)      can be optimized by prefix sum approach
    public static void subArraysSum(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int start = i;
            for (int j = i; j < array.length; j++) {
                int end = j;
                int sum = 0;
                System.out.print("[ ");
                for (int k = start; k <= end; k++) {
                    System.out.print(array[k] + " ");
                    sum += array[k];
                }
                min = Math.min(min, sum);
                max = Math.max(max, sum);
                System.out.print("]");
                System.out.println("\tSum = " + sum);
            }
        }
        System.out.println();
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);

    }

    public static void main(String[] args) {
        System.out.println();
        int[] array = {1, 2, 3, 4, 5};
        // printSubArrays(array);
        subArraysSum(array);
        System.out.println();
    }
}

/*

Total Sub-Arrays = n(n+1) / 2

Example: if n = 5

    5(5+1) / 2
=   30/2
=   15

 */
