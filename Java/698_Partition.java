Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.


Similar as matchstick to make squre, this is also a partition (backtracking) problem

not the same as Partition Equal Subset Sum which is DP problem

Method: backtracking, must add Arrays.sort(nums) otherwise it will be TLE
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if (sum % k != 0){
            return false;
        }
        int target = sum / k;
        Arrays.sort(nums);
        int[] sums = new int[nums.length];
        return canParition(nums, target, sums, k, nums.length - 1);
    }
    private boolean canParition(int[] nums, int target, int[] sums, int k, int pos){
        if (pos < 0){
            for (int i = 0; i < k; i++){
                if (sums[i] != target){
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < k; i++){
            if (sums[i] + nums[pos] > target){
                continue;
            }
            sums[i] += nums[pos];
            if (canParition(nums, target, sums, k, pos-1)){
                return true;
            }
            sums[i] -= nums[pos];
        }
        return false;
    }
}
