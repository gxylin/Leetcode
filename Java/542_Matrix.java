Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1: 
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
Example 2: 
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.

Method 1: BFS TLE
Time complexity: O(m^2*n^2)
Space compleixity: O(m*n)
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == 0){
                    res[i][j] = 0;
                }else{
                    res[i][j] = bfs(matrix, i, j);
                }
            }
        }
        return res;
    }
    private int bfs(int[][] matrix, int i, int j){
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        qx.offer(i);
        qy.offer(j);
        visited[i][j] = true;
        int level = 0;
        while (!qx.isEmpty()){
            int size = qx.size();
            level++;
            for (int l = 0; l < size; l++){
                int cx = qx.poll();
                int cy = qy.poll();
                for (int k = 0; k < dx.length; k++){
                    int x = cx + dx[k];
                    int y = cy + dy[k];
                    if (x >= 0 && x < m && y >= 0 && y < n){
                        if (matrix[x][y] == 0){
                            return level;
                        }
                        if (!visited[x][y]){
                            qx.offer(x);
                            qy.offer(y);
                            visited[x][y] = true;
                        }
                    } 
                }
            }
        }
        return level;
    }
}

Method 2: BFS
Time complexity: O(m*n)
Space complexity: O(m*n)
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                res[i][j] = matrix[i][j];
                if (matrix[i][j] == 0){
                    qx.offer(i);
                    qy.offer(j);
                    visited[i][j] = true;
                }
            }
        }
        while (!qx.isEmpty()){
            int cx = qx.poll();
            int cy = qy.poll();
            for (int k = 0; k < dx.length; k++){
                int x = cx + dx[k];
                int y = cy + dy[k];
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
                    res[x][y] = res[cx][cy] + 1;
                    qx.offer(x);
                    qy.offer(y);
                    visited[x][y] = true;
                }
            }
        }
        return res;
    }
}

Method 3: BFS
Time complexity: O(mn)
Space complexity: O(1)
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[m][n];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == 0){
                    q.offer(new int[]{i, j});
                    res[i][j] = 0;
                }else{
                    res[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        while (!q.isEmpty()){
            int[] cell = q.poll();
            for (int k = 0; k < dx.length; k++){
                int x = cell[0] + dx[k];
                int y = cell[1] + dy[k];
                if (x >= 0 && x < m && y >= 0 && y < n && res[x][y] > res[cell[0]][cell[1]]){
                    res[x][y] = res[cell[0]][cell[1]] + 1;
                    q.offer(new int[]{x, y});
                }
            }
        }
        return res;
    }
}
