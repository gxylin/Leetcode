Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

 Method 1: Forward Travel
 Time complexity: O(N^2 * 2^N)
class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0){
            return result;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (bfs(matrix, i, j)){
                    result.add(new int[]{i, j});
                }
            }
        }
        return result;
    }
    private boolean bfs(int[][] matrix, int i, int j){
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy= new LinkedList<>();
        qx.offer(i);
        qy.offer(j);
        boolean pacific = false;
        boolean atlantic = false;
        while (!qx.isEmpty()){
            int cx = qx.poll();
            int cy = qy.poll();
            for (int k = 0; k < dx.length; k++){
                int x = cx + dx[k];
                int y = cy + dy[k];
                if (x == m || y == n){
                    atlantic = true;
                }else if (x < 0 || y < 0){
                    pacific = true;
                }else if (!visited[x][y] && matrix[cx][cy] >= matrix[x][y]){
                    visited[x][y] = true;
                    qx.offer(x);
                    qy.offer(y);
                }
            }
        }
        if (pacific && atlantic){
            return true;
        }
        return false;
    }
}


class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0){
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (bfs(matrix, i, j)){
                    res.add(new int[]{i, j});
                }
            }
        }
        return res;
    }
    private boolean bfs(int[][] matrix, int x, int y){
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        boolean atlantic = false;
        boolean pacific = false;
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int[] dir : dirs){
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx < 0 || ny < 0){
                    pacific = true;
                }else if (nx == m || ny == n){
                    atlantic = true;
                }else if (!visited[nx][ny] && matrix[nx][ny] <= matrix[curr[0]][curr[1]]){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
                if (pacific && atlantic){
                    return true;
                }
            }
        }
        return false;
    }
}

Method 2: Best solution: Backward travel

Time complexity: O(N^2 + 2^N) 
class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0){
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        Queue<int[]> paQueue = new LinkedList<>();
        Queue<int[]> atQueue = new LinkedList<>();
        for (int i = 0; i < m; i++){
            pacific[i][0] = true;
            paQueue.offer(new int[]{i, 0});
            atlantic[i][n-1] = true;
            atQueue.offer(new int[]{i, n-1});
        }
        for (int j = 0; j < n; j++){
            pacific[0][j] = true;
            paQueue.offer(new int[]{0, j});
            atlantic[m-1][j] = true;
            atQueue.offer(new int[]{m-1, j});
        }
        bfs(matrix, paQueue, pacific);
        bfs(matrix, atQueue, atlantic);
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (pacific[i][j] && atlantic[i][j]){
                    res.add(new int[]{i, j});
                }
            }
        }
        return res;
    }
    private void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited){
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int[] dir : dirs){
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx >= 0 && nx < m && ny >=0 && ny < n && !visited[nx][ny] && matrix[nx][ny] >= matrix[curr[0]][curr[1]]){ 
                  //note that here is >= , not < because we are doing backward travel not like method 1 which is forward travel
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
  
