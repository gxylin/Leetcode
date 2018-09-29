You are given a m x n 2D grid initialized with these three possible values.

    -1 - A wall or an obstacle.
    0 - A gate.
    INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.

Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example: 

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4


Method: BFS
Time complexity: O(mn)
class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0){
            return;
        }
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int m = rooms.length;
        int n = rooms[0].length;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (rooms[i][j] == 0){
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int level = 0;
        while (!queue.isEmpty()){
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int[] curr = queue.poll();
                for (int[] dir : dirs){
                    int nx = curr[0] + dir[0];
                    int ny = curr[1] + dir[1];
                    if (nx < m && nx >= 0 && ny < n && ny >= 0 && rooms[nx][ny] != -1 && rooms[nx][ny] != 0){
                        if (level <= rooms[nx][ny]){
                            rooms[nx][ny] = level;
                            queue.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
    }
}
