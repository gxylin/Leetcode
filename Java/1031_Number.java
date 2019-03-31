Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)

A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.

Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.

 

Example 1:

Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: 
There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
Example 2:

Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: 
All 1s are either on the boundary or can reach the boundary.
 

Note:

1 <= A.length <= 500
1 <= A[i].length <= 500
0 <= A[i][j] <= 1
All rows have the same size.

class Solution {
    public int numEnclaves(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{1, 0}, { 0, 1}, {-1, 0}, {0, -1}};
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++){
            if (A[i][0] == 1 && !visited[i][0]){
                queue.offer(new int[]{i, 0});
                visited[i][0] = true;
            }
            if (A[i][n-1] == 1 && !visited[i][n-1]){
                queue.offer(new int[]{i, n-1});
                visited[i][n-1] = true;
            }
        }
        for (int j = 0; j < n; j++){
            if (A[0][j] == 1 && !visited[0][j]){
                queue.offer(new int[]{0, j});
                visited[0][j] = true;
            }
            if (A[m-1][j] == 1 && !visited[m-1][j]){
                queue.offer(new int[]{m-1, j});
                visited[m-1][j] = true;
            }
        }
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int[] dir : dirs){
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && A[nx][ny] == 1 && !visited[nx][ny]){
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        ////next bfs to count how many "1"
        int res = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (!visited[i][j] && A[i][j] == 1){
                    res += bfs(A, visited, i, j);
                }
            }
        }
        return res;
    }
    private int bfs(int[][] A, boolean[][] visited, int x, int y){
        int m = A.length;
        int n = A[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{1, 0}, { 0, 1}, {-1, 0}, {0, -1}};
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int count = 1;
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int[] dir : dirs){
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && A[nx][ny] == 1 && !visited[nx][ny]){
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
