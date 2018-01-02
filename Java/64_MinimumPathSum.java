Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right 
which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
[[1,3,1],
 [1,5,1],
 [4,2,1]]
Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.

class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] f = new int[n][m];
        f[0][0] = grid[0][0];
        for (int i = 1; i < n; i++){
            f[i][0] = f[i-1][0] + grid[i][0];
        }
        for (int j = 1; j < m; j++){
            f[0][j] = f[0][j-1] + grid[0][j];
        }
        for (int i = 1; i < n; i++){
            for (int j = 1; j < m; j++){
                f[i][j] = grid[i][j] + Math.min(f[i][j-1], f[i-1][j]);
            }
        }
        return f[n-1][m-1];
    }
}
