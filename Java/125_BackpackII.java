Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?

 Notice
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Have you met this question in a real interview? 
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.

Challenge 
O(n x m) memory is acceptable, can you do it in O(m) memory?

状态 State: f[i][j] 表示前i个物品当中选一些物品组成容量为j的最大价值
方程 Function: f[i][j] = max(f[i-1][j], f[i-1][j-A[i-1]] + V[i-1]);
初始化 Intialization • f[0][0]=0;
答案 Answer • f[n][s]
Time complexity: O(n*m)
Space complexity: O(m)

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        int n = A.length;
        int[][] dp = new int[2][m+1];
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++){
                if (j >= A[i-1]){
                    dp[i%2][j] = Math.max(dp[(i-1)%2][j], dp[(i-1)%2][j-A[i-1]] + V[i-1]);
                }else{
                    dp[i%2][j] = dp[(i-1)%2][j];
                }
            }
        }
        int max = 0;
        for (int i = m; i >= 0; i--){
            max = Math.max(max, dp[n%2][i]);
        }
        return max;
    }
}
