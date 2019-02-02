You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.


https://leetcode.com/problems/target-sum/solution/
Method 1: dfs
Time complexity : O(2^n) Size of recursion tree will be 2^n2
Space complexity: O(n) n refers to the size of numsnums array.

Space complexity : O(n)O(n). The depth of the recursion tree can go upto nn.
class Solution {
    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        List<String> result = new ArrayList<>();
        dfs(nums, 0, S, 0);
        return count;
    }
    private void dfs(int[] nums, int start, int target, int eval){
        if (start == nums.length){
            if (eval == target){
                count++;
            }
            return;
        }
    //    for (int i = start; i < nums.length; i++){
            dfs(nums, start + 1, target, eval + nums[start]);
            dfs(nums, start + 1, target, eval - nums[start]);
    //    }
    }
}

class Solution {
    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        List<String> res = new ArrayList<>();
        dfs(nums, S, 0);
        return count;
    }
    private void dfs(int[] nums, int target, int start){
        if (start == nums.length){
            if (target == 0){
                count++;
            } 
            return;
        }
        dfs(nums, target - nums[start], start+1);
        dfs(nums, target + nums[start], start+1);
    }
}

Similar as Leetcode 416 Partition Equal Subset Sum
https://leetcode.com/problems/target-sum/discuss/97334/Java-(15-ms)-C++-(3-ms)-O(ns)-iterative-DP-solution-using-subset-sum-with-explanation

Best solution: Reduce space complexity by scan from end to start
if scan from start to end, need additional dimension because end data relies on previous start data

Best solution:
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int i : nums){
            sum += i;
        }
        if (sum < S || (sum + S) %2 != 0){
            return 0;
        }
        //below is the problem of Partition Equal Subset Sum
        int target = (sum + S) / 2;
        int n = nums.length;
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 0; i < n; i++){
            for (int j = target; j >= nums[i]; j--){
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}

class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int i : nums){
            sum += i;
        }
        if (sum < S || (sum + S) %2 != 0){
            return 0;
        }
        //below is the problem of Partition Equal Subset Sum
        int target = (sum + S) / 2;
        int n = nums.length;
        int[][] dp = new int[n+1][target+1];
        for (int i = 0; i <= n; i++){
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++){
            for (int j = 0; j <= target; j++){ // here j must start from 0, not 1 for special case [0, 0, 0, 0, 1], S = 1
                dp[i][j] = dp[i-1][j];
                if (j >= nums[i-1]){
                    dp[i][j] += dp[i-1][j - nums[i-1]];
                }
            }
        }
        return dp[n][target];
    }
}


