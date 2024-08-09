// Maximum number of 1s in a given array of 0s and 1s, with having k flips

class MaxNumberOf1s {
    public static void main(String[] args) {
        // Test array 1
        // int[] array = { 0, 0, 1, 0, 1, 1, 0, 0, 1, 1 }; // k = 2
        // ans = 6
        
        // Test array 2
        int[] array = { 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1 }; // k = 2
        // ans = 6

        // Test array 3
        // int[] array = { 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1 }; // k = 2
        // ans = 8
        
        System.out.println(maxNumberOf1s(array));
    }

    // Time Complexity: O(n)
    public static int maxNumberOf1s(int[] array) {
        // I'll be using sliding window technique here
        int left = 0, right = 0, max = 0, k = 2, zeroCount = 0;

        // expand window untill right bound is less than lenght of array
        while (right < array.length) {
            if (array[right] == 0) {
                zeroCount++;
            }

            while (zeroCount > k) {
                if (array[left] == 0) {
                    zeroCount--;
                }
                left++; // shrink the window
            }

            max = Math.max(max, (right - left) + 1); // (right - left) + 1 is current window size
            right++; // expand the window
        }

        return max;
    }
}
