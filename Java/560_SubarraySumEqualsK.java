Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

This is the same as Question 930
    https://github.com/optimisea/Leetcode/blob/master/Java/974_Subarray.java
Method 1: Best solution
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++){
            preSum += nums[i];
            if (map.containsKey(preSum-k)){
                count += map.get(preSum-k);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}

Method 2:
Time complexity: O(n)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= nums.length; i++){
            prefixSum[i] = prefixSum[i-1] + nums[i-1];
        }

        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i <= nums.length; i++){
            if (map.containsKey(prefixSum[i] - k)){
                count += map.get(prefixSum[i] - k);
            }
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);
        }
        return count;
    }
}
