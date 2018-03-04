Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. 
This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the 
last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum 
amount of money you can rob tonight without alerting the police.





Since every house is either robbed or not robbed and at least half of the houses are not robbed, 
the solution is simply the larger of two cases with consecutive houses, i.e. house i not robbed,
break the circle, solve it, or house i + 1 not robbed. Hence, the following solution. 
I chose i = n and i + 1 = 0 for simpler coding. But, you can choose whichever two consecutive ones.

class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }
    private int rob(int[] nums, int start, int end){
        int odd = 0;
        int even = 0;
        for (int i = start; i <= end; i++){
            if (i%2 == 0){
                even = Math.max(even + nums[i], odd);
            }else{
                odd = Math.max(odd + nums[i], even);
            }
        }
        return Math.max(odd, even);
    }
}

Method 2: 循环数组处理方法一： 分裂
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        if (n == 1){
            return nums[0];
        }
        int[] A = new int[n - 1];
        for (int i = 0; i < n - 1; i++){
            A[i] = nums[i];
        }
        int[] B = new int[n - 1];
        for (int i = 1; i < n; i++){
            B[i-1] = nums[i];
        }
        return Math.max(robI(A), robI(B));
    }
    private int robI(int[] nums){
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

