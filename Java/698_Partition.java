Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty 
subsets whose sums are all equal.

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
        return canParition(nums, target, sums, k, nums.length - 1); //pos must go from largest to smallest otherwise it will TLE
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

Intuition

As even when k = 2, the problem is a "Subset Sum" problem which is known to be NP-hard, (and because the given input limits are low,) 
    our solution will focus on exhaustive search.

A natural approach is to simulate the k groups (disjoint subsets of nums). For each number in nums, we'll check whether putting it 
    in the i-th group solves the problem. We can check those possibilities by recursively searching.

Algorithm

Firstly, we know that each of the k group-sums must be equal to target = sum(nums) / k. (If this quantity is not an integer, 
                                                                                         the task is impossible.)

For each number in nums, we could add it into one of k group-sums, as long as the group's sum would not exceed the target.
    For each of these choices, we recursively search with one less number to consider in nums. If we placed every 
    number successfully, then our search was successful.

One important speedup is that we can ensure all the 0 values of each group occur at the end of the array groups, 
by enforcing if (groups[i] == 0) break;. This greatly reduces repeated work - for example, in the first run of search,
we will make only 1 recursive call, instead of k. Actually, we could do better by skipping any repeated values of groups[i], 
but it isn't necessary.

Another speedup is we could sort the array nums, so that we try to place the largest elements first. When the answer is true and 
involves subsets with a low size, this method of placing elements will consider these lower size subsets sooner. We can also handle
elements nums[i] >= target appropriately. These tricks are not necessary to solve the problem, but they are presented in the solutions
below.
    
class Solution {
    public boolean search(int[] groups, int row, int[] nums, int target) {
        if (row < 0) return true;
        int v = nums[row--];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + v <= target) {
                groups[i] += v;
                if (search(groups, row, nums, target)) return true;
                groups[i] -= v;
            }
            if (groups[i] == 0) break;
        }
        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;
        int target = sum / k;

        Arrays.sort(nums);
        int row = nums.length - 1;
        if (nums[row] > target) return false;
        while (row >= 0 && nums[row] == target) {
            row--;
            k--;
        }
        return search(new int[k], row, nums, target);
    }
}
