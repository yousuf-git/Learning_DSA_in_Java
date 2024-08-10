/*

Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]

Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

 */

public class RotateArray {
    public static void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }
    
    public static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
    
    public static void rotate(int[] nums, int k) {
        if (nums.length <= 1) {
            return;
        }
        // Normalize k to ensure it's within bounds
        k = k % nums.length;
        // reverse the whole array
        reverse(nums, 0, nums.length-1);
        // reverse first k-1 elemnts
        reverse(nums, 0, k-1);
        // reverse the remaining array
        reverse(nums, k, nums.length-1);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        rotate(nums, 3);

        for (int e : nums) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
}   

// https://leetcode.com/problems/rotate-array/description/