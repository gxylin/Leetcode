Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.

Method 1:
Time complexity: O(nlogn)
class Solution {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++){
            if (nums[i-1] == nums[i]){
                return nums[i];
            }
        }
        return 0;
    }
}

Method 2:
Time complexity: O(nlogn)
Find the biggest number that check_smaller_number <= itself
http://www.lintcode.com/en/problem/find-the-duplicate-number/

class Solution {
    public int findDuplicate(int[] nums) {
        int start = 1;
        int end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (check_smaller_number(mid, nums) <= mid){
                start = mid;
            }else{
                end = mid;
            }
        }
        if (check_smaller_number(start, nums) <= start){
            return end;
        }
        return start;
    }
    private int check_smaller_number(int mid, int[] nums){
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] <= mid){
                count++;
            }
        }
        return count;
    }
}

Method 3:
https://leetcode.com/problems/find-the-duplicate-number/description/
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (fast != slow){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int entry = 0;//can't use nums[0]
        while (entry != slow){
            entry = nums[entry];
            slow = nums[slow];
        }
        return slow;
    }
}
