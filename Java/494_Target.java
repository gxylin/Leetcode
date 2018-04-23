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
