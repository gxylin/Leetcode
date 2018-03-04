You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you 
from robbing each of them is that adjacent houses have security system connected and it will automatically contact 
the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount 
of money you can rob tonight without alerting the police.

class Solution {
    public int rob(int[] nums) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < nums.length; i++){
            if (i%2 == 0){
                a = Math.max(nums[i] + a, b);
            }else{
                b = Math.max(a, nums[i] + b);
            }
        }
        return Math.max(a, b);
    }
}

Method 2: dynamic programming (序列型,接龙型）
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int[] f = new int[n+1];
        f[0] = 0;
        f[1] = nums[0];
        for (int i = 2; i <= n; i++){
            f[i] = Math.max(f[i-1], f[i-2] + nums[i-1]);
        }
        return f[n];
    }
}
