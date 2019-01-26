Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. 
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

To get max length of increasing sequences:

Do DFS from every cell
Compare every 4 direction and skip cells that are out of boundary or smaller
Get matrix max from every cell’s max
Use matrix[x][y] <= matrix[i][j] so we don’t need a visited[m][n] array
The key is to cache the distance because it’s highly possible to revisit a cell

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 1;
        int[][] cache = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                int countMax = dfsMaxLen(matrix, i, j, cache);
                max = Math.max(max, countMax);
            }
        }
        return max;
    }

    private int dfsMaxLen(int[][] matrix, int i, int j, int[][] cache){
       if (cache[i][j] != 0){
            return cache[i][j];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int max = 1;
        for (int k = 0; k < dx.length; k++){
            int cx = i + dx[k];
            int cy = j + dy[k];
            if (cx >= 0 && cx < m && cy >= 0 && cy < n && matrix[cx][cy] > matrix[i][j]){
                int len = dfsMaxLen(matrix, cx, cy, cache);
                max = Math.max(max, len + 1);
            }
        }
        cache[i][j] = max;
        return max;
    }
    
}

Better version:
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 1;
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                int candi = dfs(matrix, i, j, dirs, map);
                max = Math.max(max, candi);
            }
        }
        return max;
    }
    private int dfs(int[][] matrix, int x, int y, int[][] dirs, Map<Integer, Integer> map){
        int m = matrix.length;
        int n = matrix[0].length;
        int key = x * n + y;
        if (map.containsKey(key)){
            return map.get(key);
        }
        int res = 1;
        for (int[] dir : dirs){
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && matrix[nx][ny] > matrix[x][y]){
                res = Math.max(res, dfs(matrix, nx, ny, dirs, map) + 1);
            }
        }
        map.put(key, res);
        return res;
    }
}
