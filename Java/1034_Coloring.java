Given a 2-dimensional grid of integers, each value in the grid represents the color of the grid square at that location.

Two squares belong to the same connected component if and only if they have the same color and are next to each other in any of the 4 directions.

The border of a connected component is all the squares in the connected component that are either 4-directionally adjacent to a square not in the component, or on the boundary of the grid (the first or last row or column).

Given a square at location (r0, c0) in the grid and a color, color the border of the connected component of that square with the given color, and return the final grid.

 

Example 1:

Input: grid = [[1,1],[1,2]], r0 = 0, c0 = 0, color = 3
Output: [[3, 3], [3, 2]]
Example 2:

Input: grid = [[1,2,2],[2,3,2]], r0 = 0, c0 = 1, color = 3
Output: [[1, 3, 3], [2, 3, 3]]
Example 3:

Input: grid = [[1,1,1],[1,1,1],[1,1,1]], r0 = 1, c0 = 1, color = 2
Output: [[2, 2, 2], [2, 1, 2], [2, 2, 2]]
 

Note:

1 <= grid.length <= 50
1 <= grid[0].length <= 50
1 <= grid[i][j] <= 1000
0 <= r0 < grid.length
0 <= c0 < grid[0].length
1 <= color <= 1000

Method 1: BFS
Time complexity: O(mn)
Space complexity: O(mn)
class Solution {
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] res = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        queue.offer(new int[]{r0, c0});
        boolean[][] visited = new boolean[m][n];
        visited[r0][c0] = true;
        int orig = grid[r0][c0];
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int[] dir : dirs){
                int x = curr[0] + dir[0];
                int y = curr[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == orig){
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (!visited[i][j]){
                    res[i][j] = grid[i][j];
                }else{
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1){
                        res[i][j] = color;
                    }else{
                        boolean isBorder = false;
                        for (int[] dir : dirs){
                            int x = i + dir[0];
                            int y = j + dir[1];
                            if (grid[x][y] != orig){
                                res[i][j] = color;
                                isBorder = true;
                                break;
                            }
                        }
                        if (!isBorder){
                            res[i][j] = grid[i][j];
                        }
                    }
                }
            }
        }
        return res;
    }
}
