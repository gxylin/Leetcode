On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

 

Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:

Input: [[0,1],[2,0]]
Output: 0
Explanation: 
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.
 

Note:

1 <= grid.length * grid[0].length <= 20


class Solution {
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<List<Integer>> res = new ArrayList<>();
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][] visited = new boolean[m][n];
        int start = 0;
        int end = 0;
        int count = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    start = i * n + j;
                    visited[i][j] = true;
                }
                if (grid[i][j] == 2){
                    end = i * n + j;
                }
                if (grid[i][j] == 0){
                    count++;
                }
            }
        }
        dfs(grid, res, new ArrayList<Integer>(), dirs, start, end, visited, count);
        return res.size();
    }
    
    private void dfs (int[][] grid, List<List<Integer>> res, List<Integer> item, int[][] dirs, int start, int end, boolean[][] visited, int count){
        if (start == end){
            if (count == item.size() - 1){
                List<Integer> cand = new ArrayList<>(item);
                cand.remove(cand.size() - 1);// remove the last dest point
                res.add(cand);
            }
            return;
        }
        int m = grid.length;
        int n = grid[0].length;
        int x = start / n;
        int y = start % n;
        for (int[] dir : dirs){
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] != -1 && !visited[nx][ny]){
                visited[nx][ny] = true;
                item.add(nx * n + ny);
                dfs(grid, res, item, dirs, nx * n + ny, end, visited, count);
                visited[nx][ny] = false;
                item.remove(item.size() - 1);
            }
        }
    }
}
