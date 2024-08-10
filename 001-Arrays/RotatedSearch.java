// https://leetcode.com/problems/search-in-rotated-sorted-array/
/*
Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4


*/

public class RotatedSearch {
    public static void main(String[] args) {
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 0;
        System.out.println(helper(nums, 0, nums.length-1, target));
    }

    public static int helper(int[] nums, int st, int end, int target) {
        if (st > end) {
            return -1;
        }
        int mid = st + (end - st) / 2;
        // Desired Location
        if (nums[mid] == target) {
            return mid;
        }
        // On Line 1
        if (nums[mid] >= nums[st]) {
            // left of L1
            if (target <= nums[mid] && target >= nums[st]) {
                return helper(nums, st, mid-1, target);
            } 
            // (right of L1) + L2
            else {
                return helper(nums, mid + 1, end, target);
            }
        }
        // on Line 2
        else if (nums[mid] <= nums[end]) {
            // right of L2
            if (target >= nums[mid] && target <= nums[end]) {
                return helper(nums, mid+1, end, target);
            } 
            // (left of L2) + L1
            else {
                return helper(nums, st, mid-1, target);
            }
        }
        return -1;
    }
}
