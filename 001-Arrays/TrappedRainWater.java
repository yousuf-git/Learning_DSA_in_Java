// See rainWater.png for question explanation

public class TrappedRainWater {
    // Approach 1 - Using auxilary arrays
    public static int getTrappedWater(int[] heights) {
        int water = 0;
        // Step 1: Create 2 auxilary arrays to store left max and right max boundaries for each index
        // Time Complexity: O(n^2)      |       Space Complexity: O(n)
        int[] leftMax = new int[heights.length], rightMax = new int[heights.length];
        leftMax[0] = heights[0];
        rightMax[heights.length - 1] = heights[heights.length - 1];

        for (int i = 1, j = heights.length - 2; i < heights.length && j >= 0; i++, j--) {
            leftMax[i] = Integer.max(leftMax[i - 1], heights[i]);
            rightMax[j] = Integer.max(rightMax[j + 1], heights[j]);
        }
        // printArray(leftMax);
        // printArray(rightMax);

        // Step 2: For each index i -> trapped water = (waterLevel - currHeight) * width    <width is optional>
        // => waterLevel = min(leftMax, rightMax)

        int waterLevel = 0, trappedWater = 0;
        for (int i = 0; i < heights.length; i++) {
            waterLevel = Integer.min(leftMax[i], rightMax[i]);
            trappedWater = waterLevel - heights[i];
            water += trappedWater;
        }
        return water;
    }

    // Approach 2 - Using 2 pointers approach
    // Time Complexity: O(n)      |       Space Complexity: O(1)
    public static int getTrappedWater2(int[] heights) {
        int water = 0;
        int leftPtr = 0, rightPtr = heights.length - 1;
        int leftMax = 0, rightMax = 0;
        // Repeat untill pointers are in valid range
        while (leftPtr <= rightPtr) {
            // Check if we have to consider left side or right side
            // If height[leftPtr] < height[rightPtr] then check if current bar can store water or not
            if (heights[leftPtr] < heights[rightPtr]) {
                // If height of leftPtr is greater than leftMax it means it cannot trap water
                if (heights[leftPtr] >= leftMax) {
                    // Just Update the leftMax
                    leftMax = heights[leftPtr];
                }
                // else -> trapped water = water level - current height
                else {
                    water += leftMax - heights[leftPtr];
                }
                leftPtr++;
            }
            // else - check the same things for right side
            else {
                // If heights[rightPtr] >= rightMax it means it cannot store water
                if (heights[rightPtr] >= rightMax) {
                    rightMax = heights[rightPtr];
                } else {
                    water += rightMax - heights[rightPtr];
                }
                rightPtr--;
            }
        }
        return water;
    }

    // A helper method to just print out the array
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // Driver Method
    public static void main(String[] args) {
        int[] heights = { 4, 2, 0, 6, 3, 2, 5 };
        int[] heights2 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        int[] heights3 = { 4, 2, 0, 3, 2, 5 };
        int[] heights4 = { 3, 1, 2, 4, 0, 1, 3, 2 };

        System.out.println("Trapped Water = " + getTrappedWater2(heights));
        System.out.println("Trapped Water = " + getTrappedWater2(heights2));
        System.out.println("Trapped Water = " + getTrappedWater2(heights3));
        System.out.println("Trapped Water = " + getTrappedWater2(heights4));

    }
}
