Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous 
subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.

Method 1: Similar to Leetcode 560 Subarray Sum Equals K
Note that (a+b) % c = a % c + b % c,
so to find multiple of k, just find presum % k are equal
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int preSum = 0;
        for (int i = 0; i < nums.length; i++){
            preSum += nums[i];
            if (k != 0){
               preSum = preSum % k; 
            }     
            if (map.containsKey(preSum)){
                if (i - map.get(preSum) > 1){
                    return true;
                }
            }else{
                map.put(preSum, i);
            }
        }
        return false;
    }
}
