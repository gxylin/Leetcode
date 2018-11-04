In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)

Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

 

Example 1:

Input: [[0,1],[1,0]]
Output: 1
Example 2:

Input: [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1
 

Note:

1 <= A.length = A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1

Method 1: BFS
Time complexity: O((mn)^2)
class Solution {
    public int shortestBridge(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        boolean found = false;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (A[i][j] == 1){
                    bfs(A, i, j);
                    found = true;
                    break;
                }
            }
            if (found){
                break;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (A[i][j] == 1){
                    boolean[][] visited = new boolean[m][n];
                    int flip = bfsConnect(A, i, j, visited);
                    res = Math.min(res, flip);
                }
            }
        }
        return res;
    }
    private void bfs(int[][] A, int x, int y){
        int m = A.length;
        int n = A[0].length;
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        A[x][y] = -1;
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int[] dir : dirs){
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && A[nx][ny] == 1){
                    A[nx][ny] = -1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
    private int bfsConnect(int[][] A, int x, int y, boolean[][] visited){
        int target = -A[x][y];
        int m = A.length;
        int n = A[0].length;
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        int res = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int[] curr = queue.poll();
                for (int[] dir : dirs){
                    int nx = curr[0] + dir[0];
                    int ny = curr[1] + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]){
                        if (A[nx][ny] == target){
                            return res;
                        }else if (A[nx][ny] == 0){
                            queue.offer(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        } 
                    }
                }
            }
            res++;
        }
        return Integer.MAX_VALUE;
    }
}


Method 2: better version
Time complexity: O(mn)
Store islandQueue and expand it using BFS

class Solution {
    public int shortestBridge(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        Queue<int[]> islandQueue = new LinkedList<>();
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][] visited = new boolean[m][n];
        boolean found = false;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (A[i][j] == 1){
                    bfs(A, i, j, islandQueue, visited);
                    found = true;
                    break;
                }
            }
            if (found){
                break;
            }
        }
        int res = 0;
        while (!islandQueue.isEmpty()){
            int size = islandQueue.size();
            for (int i = 0; i < size; i++){
                int[] curr = islandQueue.poll();
                for (int[] dir : dirs){
                    int nx = curr[0] + dir[0];
                    int ny = curr[1] + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]){
                        if (A[nx][ny] == 1){
                            return res;
                        }
                        islandQueue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            res++;
        }
        return -1;
    }
    private void bfs(int[][] A, int x, int y, Queue<int[]> islandQueue, boolean[][] visited){
        int m = A.length;
        int n = A[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        islandQueue.offer(new int[]{x, y});
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for(int[] dir : dirs){
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && A[nx][ny] == 1){
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    islandQueue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
