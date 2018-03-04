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
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] f = new int[m][n];
        int maxSide = 0;
        for (int i = 0; i < m; i++){
            f[i][0] = matrix[i][0] - '0';
            maxSide = Math.max(maxSide, f[i][0]);
        }
        for (int j = 0; j < n; j++){
            f[0][j] = matrix[0][j] - '0';
            maxSide = Math.max(maxSide, f[0][j]);
        }
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (matrix[i][j] == '1'){
                    f[i][j] = Math.min(f[i-1][j-1], Math.min(f[i-1][j], f[i][j-1])) + 1;
                }else{
                    f[i][j] = 0;
                }
                maxSide = Math.max(maxSide, f[i][j]);
            }
        }
        return maxSide * maxSide;
    }
}
