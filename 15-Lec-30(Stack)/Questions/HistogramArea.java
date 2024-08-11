/*

Q: Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

Input: heights = [2,4]
Output: 4

See: HistogramArea1.png and HistogramArea2.png for visual explanation

* Approach:
* - Get Next Greater / Smaller Element index of each element
* - Area = height * width
* height = array[i]
* width = (left smaller index ) - (right smaller index) + 1

*/

package Questions;

import java.util.Stack;

public class HistogramArea {

    public static int[] rightSmaller(int[] array) {
        Stack<Integer> stk = new Stack<>();
        int[] rightSmaller = new int[array.length];

        for (int i = array.length - 1; i >= 0; i--) {
            while (!stk.empty() && array[stk.peek()] >= array[i]) {
                stk.pop();
            }
            if (stk.empty()) {
                rightSmaller[i] = array.length;
            } else {
                rightSmaller[i] = stk.peek();
            }
            stk.push(i);
        }
        return rightSmaller;
    }

    public static int[] leftSmaller(int[] array) {
        Stack<Integer> stk = new Stack<>();
        int[] leftSmaller = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            // int count = 0
            while (!stk.empty() && array[stk.peek()] >= array[i]) {
                stk.pop();
                // count++;
            }
            if (stk.empty()) {
                leftSmaller[i] = -1;
            } else {
                leftSmaller[i] = stk.peek();
            }
            stk.push(i);
        }
        return leftSmaller;
    }

    public static int largestRectangleArea(int[] heights) {
        int[] left = leftSmaller(heights);
        int[] right = rightSmaller(heights);

        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            // area = width * height
            int width = right[i] - left[i] - 1;
            int height = heights[i];
            area = Math.max(area, width * height);
        }
        return area;
    }

    public static void main(String[] args) {
        int[] heights = { 2, 1, 5, 6, 2, 3 };
        System.out.println(largestRectangleArea(heights)); // 10
    }

}

// https://leetcode.com/problems/largest-rectangle-in-histogram/