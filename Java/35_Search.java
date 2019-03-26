Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2
Example 2:

Input: [1,3,5,6], 2
Output: 1
Example 3:

Input: [1,3,5,6], 7
Output: 4
Example 1:

Input: [1,3,5,6], 0
Output: 0

    
Check leetcode: https://leetcode.com/problems/random-pick-with-weight/discuss/154044/Java-accumulated-freq-sum-and-binary-search

https://github.com/optimisea/Leetcode/blob/master/Java/binary.java

Note that for this method, in most cases target should be between low (inclusive) and high (inclusive) 
     But there are two corner cases
     case 1: target less than low, e.g. [1,2,3] target = 0 == > low will be 1, high will be 2
     case 2: target greater than high, e.g. [1,2,3] target = 4 == > low will be 2, high will be 3
         
class Solution {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] < target){
                start = mid;
            }else{
                end = mid;
            }
        }
        if (nums[start] >= target){//cover corner case 1 and normal case
            return start;
        }
        if (nums[end] >= target){//cover normal case
            return end;
        }
        return end+1; //cover corner case 2
        //return nums.length;
    }
}

Better version: no need to check corner cases since it already covered.
class Solution {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = start + (end - start) / 2;
            if (target == nums[mid]){
                return mid;
            }else if (nums[mid] < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return start;
    }
}
