Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.

Method 1: Greey
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

Method 2: DP
f[i] denotes the maxium value of the subarray that ends at the first i elements

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] f = new int[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; i++){
            f[i] = Math.max(nums[i-1], nums[i-1] + f[i-1]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++){
            max = Math.max(max, f[i]);
        }
        return max;
    }
}

Method 3: rolling array
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] f = new int[2];
        f[0] = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++){
            f[i%2] = Math.max(nums[i-1], nums[i-1] + f[(i-1)%2]);
            max = Math.max(max, f[i%2]);
        }
        return max;
    }
}
