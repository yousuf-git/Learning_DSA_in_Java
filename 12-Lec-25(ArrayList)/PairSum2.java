/**
Q: Given a rotated Sorted Array and target value, find pair[] from array whoose sum = target

Example of rotated array: 
{10,11,7,8,9}       This array is rotated around 9 from actually {7,8,9,10,11}

Approach: 

Step 1: Find Max and Minimum element by finding the breakpoint in the array
Step 2: The minimum element index will be the left pointer, and max element index will be right ptr
Step 3: The movement of pointers will be by:        (to move in rotated fashion along the array)
        leftPtr = (leftPtr + 1) % array.length
        rightPtr = (array.length + rightPtr - 1) % array.length
Rest of the steps will be same as in 2 pointer approach
*/
public class PairSum2 {
    public static int[] pairSum(int[] array, int target) {
        int[] pair = new int[2];
        pair[0] = Integer.MIN_VALUE;
        // Default location of pointers
        int leftPtr = 0;
        int rightPtr = array.length;
        // Find break point and update the pointers
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[i + 1]) {
                rightPtr = i; // i  is break-point and hence array[i] is maximum value
                leftPtr = i + 1;
                break;
            }
        }
        while (leftPtr != rightPtr) {
            if (array[leftPtr] + array[rightPtr] < target) {
                // Move left pointer
                leftPtr = (leftPtr + 1) % array.length;
            } else if (array[leftPtr] + array[rightPtr] > target) {
                rightPtr = ((rightPtr - 1) + array.length) % array.length;
            } else {
                // System.out.println("Left Pointer: " + leftPtr);
                // System.out.println("Right Pointer: " + rightPtr);
                pair[0] = array[leftPtr];
                pair[1] = array[rightPtr];
                return pair;
            }
        }
        return pair;
    }

    public static void main(String[] args) {
        int[] array = {10, 11, 12, 5, 6, 7, 8, 9}; // rotated along 9
        int target = 12;
        int[] pair = pairSum(array, target);
        if (pair[0] != Integer.MIN_VALUE) {
            System.out.println("Sum of " + pair[0] + " and " + pair[1] + " = " + target);
        } else {
            System.out.println("No Valid Pair Found !");
        }
    }
}
