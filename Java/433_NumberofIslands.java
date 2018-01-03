Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, 
we consider them in the same island. We only consider up/down/left/right adjacent.

Find the number of islands.

Have you met this question in a real interview? Yes
Example
Given graph:

[
  [1, 1, 0, 0, 0],
  [0, 1, 0, 0, 1],
  [0, 0, 0, 1, 1],
  [0, 0, 0, 0, 0],
  [0, 0, 0, 0, 1]
]

Method 1:
public class Solution {
    /*
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j]){
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    private void bfs(boolean[][] grid, int i, int j){
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        qx.offer(i);
        qy.offer(j);
        while (!qx.isEmpty()){
            int cx = qx.poll();
            int cy = qy.poll();
            for (int k = 0; k < 4; k++){
                int nx = cx + dx[k];
                int ny = cy + dy[k];
                if (isBound(grid, nx, ny) && grid[nx][ny]){
                    qx.offer(nx);
                    qy.offer(ny);
                    grid[nx][ny] = false;
                }
            }
        }
    }
    private boolean isBound(boolean[][] grid, int x, int y){
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}

Method 2: create a new class
public class Solution {
    /*
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    private class Coordinate{
        int x;
        int y;
        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int isIsland = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j]){
                    markByBFS(grid, i, j);
                    isIsland++;
                }
            }
        }
        return isIsland;
    }
    
    private void markByBFS(boolean[][] grid, int x, int y){
        int[] directionX = {0, 1, -1, 0};
        int[] directionY = {-1, 0, 0, 1};
        Queue<Coordinate> queue = new LinkedList<>();
        
        queue.offer(new Coordinate(x,y));
        grid[x][y] = false;
        
        while (!queue.isEmpty()){
            Coordinate coor = queue.poll();
            for (int i = 0; i < 4; i++){
                Coordinate adj = new Coordinate(coor.x + directionX[i], coor.y + directionY[i]);
                if (!inBound(adj, grid)){
                    continue;
                }
                if (grid[adj.x][adj.y]){
                    queue.offer(adj);
                    grid[adj.x][adj.y] = false;
                }
            }
        }
    }
    private boolean inBound(Coordinate adj, boolean[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        
        return adj.x >= 0 && adj.y >= 0 && adj.x < m && adj.y < n;
    }
}
