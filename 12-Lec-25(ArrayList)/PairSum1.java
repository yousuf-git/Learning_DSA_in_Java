// Q: Given a sorted array and a target, return pair[] from array whoose sum = target

public class PairSum1 {
    /* ---------- Approach 1 --------------- */
    // // Brute Force - O(n^2)
    // public static int[] pairSum(int[] array, int target) {
    //     int[] pair = new int[2];
    //     pair[0] = Integer.MIN_VALUE;
    //     // Itearte by pair loop
    //     for (int i = 0; i < array.length; i++) {
    //         for (int j = i + 1; j < array.length; j++) {
    //             if (array[i] + array[j] == target) {
    //                 pair[0] = array[i];
    //                 pair[1] = array[j];
    //                 return pair;
    //             }
    //         }
    //     }
    //     return pair;
    // }
    /* ---------- Approach 2 --------------- */
    // 2 Pointer - O(n)
    public static int[] pairSum(int[] array, int target) {
        int[] pair = new int[2];
        pair[0] = Integer.MIN_VALUE;
        int leftPtr = 0;
        int rightPtr = array.length-1;
        while (leftPtr < rightPtr) {
            if (array[leftPtr] + array[rightPtr] == target) {
                pair[0] = array[leftPtr];
                pair[1] = array[rightPtr];
                return pair;
            } else if (array[leftPtr] + array[rightPtr] < target) {
                leftPtr++;
            } else {
                rightPtr++;
            }
        }
        return pair;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 15;
        int[] pair = pairSum(array, target);
        if (pair[0] != Integer.MIN_VALUE) {
            System.out.println("Sum of " + pair[0] + " and " + pair[1] + " = " + target);
        } else {
            System.out.println("No Valid Pair Found !");
        }
    }
}
