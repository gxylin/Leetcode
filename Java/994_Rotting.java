In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean hasFresh = false;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 2){
                    queue.offer(new int[]{i, j});
                }
                if (grid[i][j] == 1){
                    hasFresh = true;
                }
            }
        }
        if (queue.isEmpty()){
            if (hasFresh){
                return -1;
            }
            return 0;
        }
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int count = -1;
        while (!queue.isEmpty()){
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++){
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];
                for (int[] dir : dirs){
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1){
                        queue.offer(new int[]{nx, ny});
                        grid[nx][ny] = 2;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    return -1;
                }
            }
        }
        return count;
    }
}
