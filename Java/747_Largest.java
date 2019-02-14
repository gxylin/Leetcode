In a given integer array nums, there is always exactly one largest element.

Find whether the largest element in the array is at least twice as much as every other number in the array.

If it is, return the index of the largest element, otherwise return -1.

Example 1:

Input: nums = [3, 6, 1, 0]
Output: 1
Explanation: 6 is the largest integer, and for every other number in the array x,
6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
 

Example 2:

Input: nums = [1, 2, 3, 4]
Output: -1
Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
 

Note:

nums will have a length in the range [1, 50].
Every nums[i] will be an integer in the range [0, 99].

 One pass
class Solution {
    public int dominantIndex(int[] nums) {
        int max = Integer.MIN_VALUE;
        int maxInd = -1;
        int max2nd = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++){
            if (max < nums[i]){
                max2nd = max;
                max = nums[i];
                maxInd = i;
            }else if (max2nd < nums[i]){
                max2nd = nums[i];
            }
        }
        if (max >= 2 * max2nd){
            return maxInd;
        }
        return - 1;
    }
}

Two passes:
class Solution {
    public int dominantIndex(int[] nums) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] > max){
                max = nums[i];
                index = i;
            }
        }
        for (int i : nums){
            if (i == max){
                continue;
            }
            if (max < 2 * i){
                return -1;
            }
        }
        return index;
    }
}
