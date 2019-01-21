Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

class Solution {
    public int maxProduct(int[] nums) {
        int maxPre = 1;
        int minPre = 1;
        int maxCur = nums[0];
        int minCur = nums[0];
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++){
            maxCur = Math.max(maxPre * nums[i], minPre * nums[i]);
            minCur = Math.min(maxPre * nums[i], minPre * nums[i]);
            maxCur = Math.max(maxCur, nums[i]);
            minCur = Math.min(minCur, nums[i]);
            result = Math.max(result, maxCur);
            maxPre = maxCur;
            minPre = minCur;
        }
        return result;
    }
}

Similar to maximum subarray :https://github.com/optimisea/Leetcode/blob/master/Java/53_MaximumSubarray.java
DP
class Solution {
    public int maxProduct(int[] nums) {
        int global = Integer.MIN_VALUE;
        int maxCurr = 0;
        int minCurr = 0;
        int maxPrev = 1;
        int minPrev = 1;
        for (int num : nums){
            maxCurr = Math.max(num, Math.max(maxPrev * num, minPrev * num));
            minCurr = Math.min(num, Math.min(maxPrev * num, minPrev * num));
            global = Math.max(global, maxCurr);
            maxPrev = maxCurr;
            minPrev = minCurr;
        }
        return global;
    }
}
