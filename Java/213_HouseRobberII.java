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

