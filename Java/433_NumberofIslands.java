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
Time complexity; O(mn)
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
        grid[i][j] = false;
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


Change the value of grid:
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1'){
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    private void bfs(char[][] grid, int x, int y){
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{1,0}, {0, 1}, {-1, 0}, {0, -1}};
    //    boolean[][] visited = new boolean[m][n];
        queue.offer(new int[]{x, y});
        grid[x][y] = '0';
     //   visited[x][y] = true;
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int[] dir : dirs){
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == '1'){
                    queue.offer(new int[]{nx, ny});
                    grid[nx][ny] = '0';
                }
            }
        }
    }
}


Best solution without changing value of grid
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1' && !visited[i][j]){
                    bfs(grid, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }
    private void bfs(char[][] grid, int x, int y, boolean[][] visited){
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{1,0}, {0, 1}, {-1, 0}, {0, -1}};
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int[] dir : dirs){
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == '1' && !visited[nx][ny]){
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}


Method 3: UF
class Solution {
    class UF {
        int[] parent;
        public UF (int N){
            parent = new int[N];
            for (int i = 0; i < N; i++){
                parent[i] = i;
            }
        }
        public int find(int x){
            if (x == parent[x]){
                return x;
            }
            return parent[x] = find(parent[x]);//path compression
        }
        public void union (int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY){
                parent[rootX] = rootY;
            }
        }
    }
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        UF uf = new UF(m*n);
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int count = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1'){
                    count++;
                    int num1 = i * n + j;
                    for (int[] dir : dirs){
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1'){
                            int num2 = x * n + y; 
                            if (uf.find(num1) != uf.find(num2)){
                                uf.union(num1, num2);
                                count--;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}


class Solution {
    class UF {
        int[] parent;
        int[] size;
        int count;
        public UF (int N){
            parent = new int[N];
            size = new int[N];
            count = N;
            for (int i = 0; i < N; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int x){
            if (x == parent[x]){
                return x;
            }
            return parent[x] = find(parent[x]);
        }
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY){
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
                count--;
            }
        }
        public int getSize(){
            return count;
        }
    }
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        UF uf = new UF(m*n);
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int countZero = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1'){
                    for (int[] dir : dirs){
                        int nx = i + dir[0];
                        int ny = j + dir[1];
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == '1'){
                            int curr = converter(i, j, n);
                            int next = converter(nx, ny, n);
                            uf.union(curr, next);
                        }
                    }
                }else{
                    countZero++;
                }
            }
        }
        return uf.getSize() - countZero;
    }
    private int converter(int x, int y, int n){
        return x * n + y;
    }
}
