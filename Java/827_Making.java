In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).

Example 1:

Input: [[1, 0], [0, 1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: [[1, 1], [1, 0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 1.
Example 3:

Input: [[1, 1], [1, 1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 1.
 

Notes:

1 <= grid.length = grid[0].length <= 50.
0 <= grid[i][j] <= 1.

Method 1: DFS/BFS TLE
Time complexity: O(N^4)
Space complexity: O(N^2)
For each 0 in the grid, let's temporarily change it to a 1, then count the size of the group from that square.

class Solution {
    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};

    public int largestIsland(int[][] grid) {
        int N = grid.length;

        int ans = 0;
        boolean hasZero = false;
        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c)
                if (grid[r][c] == 0) {
                    hasZero = true;
                    grid[r][c] = 1;
                    ans = Math.max(ans, check(grid, r, c));
                    grid[r][c] = 0;
                }

        return hasZero ? ans : N*N;
    }

    public int check(int[][] grid, int r0, int c0) {
        int N = grid.length;
        Queue<Integer> queue = new LinkedList();
        Set<Integer> seen = new HashSet();
        queue.offer(r0 * N + c0);
        seen.add(r0 * N + c0);

        while (!queue.isEmpty()) {
            int code = queue.poll();
            int r = code / N, c = code % N;
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k], nc = c + dc[k];
                if (!seen.contains(nr * N + nc) && 0 <= nr && nr < N &&
                        0 <= nc && nc < N && grid[nr][nc] == 1) {
                    queue.offer(nr * N + nc);
                    seen.add(nr * N + nc);
                }
            }
        }
        return seen.size();
    }
}

Method 2:
Two Steps:
1. Explore every island using DFS/BFS, count its area, give it an island index and save the result to a {index: area} map.
2. Loop every cell == 0, check its connected islands and calculate total islands area.

Time complexity: O(N^2)
Space complexity: O(N^2)
class Solution {
    public int largestIsland(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        Map<Integer, Integer> map = new HashMap<>();
        int index = 2;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    int area = bfs(grid, i, j, index);
                    map.put(index, area);
                    index++;
                    ans = Math.max(ans, area);
                }
            }
        }
        //traverse every 0 cell and count biggest island it can conntect
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 0){
                    int count = 1;
                    Set<Integer> seen = new HashSet<>();
                    for (int k = 0; k < dx.length; k++){
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (0 <= x && x < m && 0 <= y && y < n){
                            int key = grid[x][y];
                            if (map.containsKey(key) && !seen.contains(key)){
                                seen.add(key);
                                count += map.get(key);
                            }
                        }   
                    }
                    ans = Math.max(ans, count);
                }
            }
        }
        return ans;
    }
    private int bfs(int[][] grid, int x, int y, int index){
        grid[x][y] = index;
        int m = grid.length;
        int n = grid[0].length;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        qx.offer(x);
        qy.offer(y);
        int count = 1;
        while (!qx.isEmpty()){
            int cx = qx.poll();
            int cy = qy.poll();
            for (int i = 0 ; i < dx.length; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (0 <= nx && nx < m && 0 <= ny && ny < n && grid[nx][ny] == 1){
                    grid[nx][ny] = index;
                    qx.offer(nx);
                    qy.offer(ny);
                    count++;
                }
            }
        }
        return count;
    }
}

Method 3: Union Find
O(M*N)
class Solution {
    class UF {
        int[] parent;
        int[] size;
        int count;
        public UF (int N){
            parent = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++){
                parent[i] = i;
                size[i] = 1;
            }
            count = N;
        }
        public int find(int x){
            if (parent[x] == x){
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
    }
    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UF uf = new UF(m*n);
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int i = 0; i< m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    int curr = i * n + j;
                    for (int[] dir : dirs){
                        int nx = i + dir[0];
                        int ny = j + dir[1];
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1){
                            int nei = nx * n + ny;
                            uf.union(curr, nei);
                        }
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 0){
                    int count = 1;
                    int curr = i * n + j;
                    Set<Integer> seen = new HashSet<>();
                    for (int[] dir : dirs){
                        int nx = i + dir[0];
                        int ny = j + dir[1];
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1){
                            int nei = nx * n + ny;
                            int root = uf.find(nei);
                            if (!seen.contains(root)){
                                count += uf.size[root];
                                seen.add(root);
                            }
                        }
                    }
                    max = Math.max(max, count);
                }
            }
        }
        return max == 0 ? m * n : max;
    }
}
