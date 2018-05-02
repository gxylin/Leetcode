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
