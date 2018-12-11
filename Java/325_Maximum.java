Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:

Input: nums = [1, -1, 5, -2, 3], k = 3
Output: 4 
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.

Example 2:

Input: nums = [-2, -1, 2, 1], k = 1
Output: 2 
Explanation: The subarray [-1, 2] sums to 1 and is the longest.

Method: preSum
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int sum = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++){
            sum = sum + nums[i]; 
            if (map.containsKey(sum - k)){
                max = Math.max(max, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)){//check to ensure not overwrite old values
                map.put(sum, i);
            }
        }
        return max;
    }
}
