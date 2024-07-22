/*
Q: Given an arraylist of integers that show height of lines on x-axis. Return the maxmimum number of cells that can be used as a container (by joining any 2 lines) to store water in them

See: MaxWaterContainer.png to get clear about problem

Reference: https://leetcode.com/problems/container-with-most-water/

*/
import java.util.ArrayList;

public class MaxWaterContainer {
    public static int getMostWater(ArrayList<Integer> heights) {
        int max = Integer.MIN_VALUE;

        // Approach 1- Brute Force
        // TIme Complexity: O(n^2)
        // for (int i = 0; i < heights.size(); i++) {
        //     for (int j = i+1; j < heights.size(); j++) {
        //         // width = diff of indices
        //         int width = j-i;
        //         // height = min of both heights
        //         int height = Math.min(heights.get(i), heights.get(j));
        //         // Update Max
        //         max = Math.max(max, width*height);
        //     }
        // }

        // Approach 2 - Optimized by 2 pointers approach
        // TIme Complexity: O(n)
        int leftPtr = 0;
        int rightPtr = heights.size()-1;
        while (leftPtr < rightPtr) {
            // Calculate Height and width
            int width = rightPtr - leftPtr;
            int height = Math.min(heights.get(leftPtr), heights.get(rightPtr));
            
            // Update Max
            max = Math.max(max, width*height);
            
            // Update pointers based on which one's height is smaller
            if (heights.get(leftPtr) < heights.get(rightPtr)) {
                leftPtr++;
            } else {
                rightPtr--;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        ArrayList<Integer> heights = new ArrayList<>();
        heights.add(1);
        heights.add(8);
        heights.add(6);
        heights.add(2);
        heights.add(5);
        heights.add(4);
        heights.add(8);
        heights.add(3);
        heights.add(7);
        int max = getMostWater(heights);
        System.out.println(max);
    }
}
