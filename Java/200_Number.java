Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by 
connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3

Method 1: BFS
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
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int[] dir : dirs){
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx < m && nx >= 0 && ny < n && ny >= 0 && grid[nx][ny] == '1'){
                    queue.offer(new int[]{nx, ny});
                    grid[nx][ny] = '0';
                }
            }
        }
    }
}


Better BFS version:
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (!visited[i][j] && grid[i][j] == '1'){
                    bfs(grid, visited, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    private void bfs(char[][] grid, boolean[][] visited, int x, int y){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int[] dir : dirs){
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && grid[nx][ny] == '1'){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}

Method 2: Union Find  faster than BFS
Time complexity: O(m*n) 
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
            return parent[x] = find(parent[x]);
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
