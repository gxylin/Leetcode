Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.

class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int minSum = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            maxSum = Math.max(sum - minSum, maxSum);
            minSum = Math.min(sum, minSum);
        }
        return maxSum;
    }
}
