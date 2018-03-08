Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Have you met this question in a real interview? 
Example
Given [1,2,3,4], k = 2, target = 5.

There are 2 solutions: [1,4] and [2,3].

Return 2.


n个数,取k个数,组成和为target 
• State: f[i][j][t]前i个数取j个数出来能否和为t 
• Function: f[i][j][t] = f[i - 1][j - 1][t - a[i-1]] + f[i - 1][j][t]
• Intialization: f[i][0][0] = 1
• Answer: f[n][k][target]

Method 1: 
Space complexity: O(A.length*k*target)
public class Solution {
    /**
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        int n = A.length;
        int[][][] dp = new int[n+1][k+1][target+1];
        for (int i = 0; i <= n; i++){
            dp[i][0][0] = 1;
        }
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= k; j++){
                for (int t = 1; t <= target; t++){
                    if (t >= A[i-1]){
                        dp[i][j][t] = dp[i-1][j-1][t-A[i-1]] + dp[i-1][j][t];
                    }else{
                        dp[i][j][t] = dp[i-1][j][t];
                    }
                }
            }
        }
        return dp[n][k][target];
    }
}

Method 2: 
Space complexity: O(k*target)
public class Solution {
    /**
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        int n = A.length;
        int[][][] dp = new int[2][k+1][target+1];
        for (int i = 0; i <= 1; i++){
            dp[i][0][0] = 1;
        }
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= k; j++){
                for (int t = 1; t <= target; t++){
                    if (t >= A[i-1]){
                        dp[i%2][j][t] = dp[(i-1)%2][j-1][t-A[i-1]] + dp[(i-1)%2][j][t];
                    }else{
                        dp[i%2][j][t] = dp[(i-1)%2][j][t];
                    }
                }
            }
        }
        return dp[n%2][k][target];
    }
}
