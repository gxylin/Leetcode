Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another 
if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:

11000
11000
00011
00011

Given the above grid map, return 1.

Example 2:

11011
10000
00001
11011

Given the above grid map, return 3.

Notice that:

11
1

and

 1
11

are considered different island shapes, because we do not consider reflection / rotation.

Note: The length of each dimension in the given grid does not exceed 50. 


class Solution {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    String str = bfs(grid, i, j);
                    if (!set.contains(str)){
                        count++;
                        set.add(str);
                    }
                }
            }
        }
        return count;
    }
    private String bfs(int[][] grid, int i, int j){
        int m = grid.length;
        int n = grid[0].length;
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        qx.offer(i);
        qy.offer(j);
        grid[i][j] = 0;
        StringBuilder sb = new StringBuilder();
        while (!qx.isEmpty()){
            int cx = qx.poll();
            int cy = qy.poll();
            for (int k = 0; k < dx.length; k++){
                int nx = cx + dx[k];
                int ny = cy + dy[k];
                if (0 <= nx && nx < m && 0 <= ny && ny < n && grid[nx][ny] == 1){
                    qx.offer(nx);
                    qy.offer(ny);
                    grid[nx][ny] = 0;
                    sb.append(dx[k]);
                    sb.append(dy[k]);
                }
            }
           sb.append("_");
        }
        return sb.toString();
    }
}


Method 2: DFS
class Solution {
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, "0");
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
    private String dfs(int[][] grid, int x, int y, StringBuilder sb, String str){
        int m = grid.length;
        int n = grid[0].length;
        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
            grid[x][y] = 0;
            sb.append(str);
            dfs(grid, x+1, y, sb, "1");
            dfs(grid, x, y+1, sb, "2");
            dfs(grid, x-1, y, sb, "3");
            dfs(grid, x, y-1, sb, "4");
            sb.append("_");
        }
        return sb.toString();
    }
}
