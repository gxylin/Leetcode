Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill 
using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong
to be destroyed.
Note: You can only put the bomb at an empty cell.

Example:

Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
Output: 3 
Explanation: For the given grid,

0 E 0 0 
E 0 W E 
0 E 0 0

Placing a bomb at (1,1) kills 3 enemies.

Method 1: Brute Force
Time compleixty: O(mn * (m+n))
class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                if (grid[i][j] == '0'){
                    int num = count(grid, i, j);
                    res = Math.max(res, num);
                }
            }
        }
        return res;
    }
    private int count(char[][] grid, int row, int col){
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        int i = row;
        while (i >= 0){
            if (grid[i][col] == 'E'){
                res++;
            }else if (grid[i][col] == 'W'){
                break;
            }
            i--;
        }
        i = row;
        while (i < m){
            if (grid[i][col] == 'E'){
                res++;
            }else if (grid[i][col] == 'W'){
                break;
            }
            i++;
        }
        int j = col;
        while (j >= 0){
            if (grid[row][j] == 'E'){
                res++;
            }else if (grid[row][j] == 'W'){
                break;
            }
            j--;
        }
        j = col;
        while (j < n){
            if (grid[row][j] == 'E'){
                res++;
            }else if (grid[row][j] == 'W'){
                break;
            }
            j++;
        }
        return res;
    }
}

Method 2: 
Time complexity: O(mn * factor)), worst case could be O(mn * (m or n))
Space complexity: O(n)
http://www.cnblogs.com/grandyang/p/5599289.html
public int maxKilledEnemies(char[][] grid){
     int res = 0;
     int m = grid.length;
     int n = grid[0].length;
     int rowKills = 0;
     int[] colKills = new int[n];
     for (int i = 0; i < m; i++){
         for (int j = 0; j < n; j++){
             if (j == 0 || grid[i][j-1] == 'W'){
                 rowKills = 0;
                 for (int k = j; j < n && grid[i][k] != 'W'; k++){
                     rowKills += grid[i][k] == 'E';
                 }
             }
             if (i == 0 || grid[i-1][j] == 'W'){
                 colKills[j] = 0;
                 for (int k = i; k < m && grid[k][j] != 'W'; k++){
                     colKills[j] += grid[k][j] == 'E';
                 }
             }
             if (grid[i][j] == '0'){
                 res = Math.max(res, rowKills + colKills[j]);
             }
         }
     }
     return res;
}

Method 3: 
Time complexty: O(mn)
Space complexity: O(mn)
http://www.cnblogs.com/grandyang/p/5599289.html

preSum to calculate left, right, up, down first

public int maxKilledEnemies(char[][] grid){
    int m = grid.length;
    int n = grid[0].length;
    int[][] left = new int[m][n];
    for (int i = 0; i < m; i++){
        for (int j = 0; j < n; j++){
            if (j == 0 || grid[i][j]=='W'){
                left[i][j] = 0;
            }else{
                left[i][j] = left[i][j-1] + (grid[i][j] == 'E');
            }
        }
    }
    int[][] right = new int[m][n];
    for (int i = 0; i < m; i++){
        for (int j = n-1; j >= 0; j--){
            if (j == n-1 || grid[i][j] == 'W'){
                right[i][j] = 0;
            }else{
                right[i][j] = right[i][j+1] + (grid[i][j] == 'E');
            }
        }
    }
    int[][] up = new int[m][n];
    for (int j = 0; j < n; j++){
        for (int i = 0; i < m; i++){
            if (i == 0 || grid[i][j] == 'W'){
                up[i][j] = 0;
            }else{
                up[i][j] = up[i-1][j] + (grid[i][j] == 'E');
            }
        }
    }
    int[][] down = new int[m][n];
    for (int j = 0; j < n; j++){
        for (int i = m-1; i>=0; i--){
            if (i == 0 || grid[i][j] == 'W'){
                down[i][j] = 0;
            }else{
                down[i][j] = down[i+1][j] + (grid[i][j] == 'E');
            }
        }
    }
    int res = 0;
    for (int i = 0; i < m; i++){
        for (int j = 0;j < n; j++){
            if (grid[i][j] == '0'){
                res = Math.max(res, left[i][j] + right[i][j] + up[i][j] + down[i][j]);
            }
        }
    }
    return res;
}
