iven a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.

Method 1: 2D dynamic programming
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];//denote the max square side ending at i, j
        int max = 0;
        for (int i = 0; i < m; i++){
            dp[i][0] = (int)(matrix[i][0] - '0');
            max = Math.max(max, dp[i][0]);
        }
        for (int j = 0; j < n; j++){
            dp[0][j] = (int)(matrix[0][j] - '0');
            max = Math.max(max, dp[0][j]);
        }
       
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (matrix[i][j] == '1'){
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }else{
                    dp[i][j] = 0;
                }
                
            }
        }
        return max * max;
    }
}

Why pick Math.min
https://www.youtube.com/watch?v=aYnEO53H4lw&t=247s

