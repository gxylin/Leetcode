Given a non-empty array containing only positive integers, find if the array can be partitioned into 
two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.


Similar to backpack problem
• State:
• f[i][S] “前i”个物品,取出一些能否组成和为S 
• Function:
• a[i-1] 是第i个物品下标是i-1
• f[i][S] = f[i-1][S - a[i-1]] or f[i-1][S]
• Intialize:
• f[i][0] = true; f[0][1..target] = false
• Answer:
• f[n][target]
• O(n*S) , 滚动数组优化
Method 1: DP
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if (sum % 2 != 0){
            return false;
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][target+1];
        for (int i = 0; i <= nums.length; i++){
            dp[i][0] = true;
        }
        for (int i = 1; i <= nums.length; i++){
            for (int j = 1; j <= target; j++){
                if (j >= nums[i-1]){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[nums.length][target];
    }
}

Method 2: 滚动数组优化
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if (sum % 2 != 0){
            return false;
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[2][target+1];
        for (int i = 0; i <= nums.length; i++){
            dp[i%2][0] = true;
        }
        for (int i = 1; i <= nums.length; i++){
            for (int j = 1; j <= target; j++){
                if (j >= nums[i-1]){
                    dp[i%2][j] = dp[(i-1)%2][j] || dp[(i-1)%2][j-nums[i-1]];
                }else{
                    dp[i%2][j] = dp[(i-1)%2][j];
                }
            }
        }
        return dp[nums.length%2][target];
    }
}
