Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Write a function to determine if a given target is in the array.

The array may contain duplicates.

https://leetcode.com/problems/search-in-rotated-sorted-array-ii/discuss/28218/My-8ms-C++-solution-(o(logn)-on-average-o(n)-worst-case)


class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] == target){
                return true;
            }
            if (nums[mid] > nums[start]){
                if (nums[start] <= target && target < nums[mid]){
                    end = mid;
                }else{
                    start = mid;
                }
            }else if (nums[mid] < nums[end]){
                if (nums[mid] < target && target <= nums[end]){
                    start = mid;
                }else{
                    end = mid;
                }
            }else{//this case is the only difference due to the duplicates
                if (nums[start] == nums[mid]){
                    start++;
                }
                if (nums[end] == nums[mid]){
                    end--;
                }
            }
        }
        if (nums[start] == target || nums[end] == target){
            return true;
        }
        return false;
    }
}
