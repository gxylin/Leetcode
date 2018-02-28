Given n x m non-negative integers representing an elevation map 2d where the area of each cell is 1 x 1, 
compute how much water it is able to trap after raining.

Given 5*4 matrix

[12,13,0,12]
[13,4,13,12]
[13,8,10,12]
[12,13,12,12]
[13,13,13,13]
return 14.

Method: PriorityQueue + BFS
Advanced Algorithm jiuzhang

public class Solution {
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    class Pair{
        int x;
        int y;
        int h;
        public Pair(int x, int y, int h){
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
    public int trapRainWater(int[][] heights) {
        Queue<Pair> q = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return a.h - b.h;
            }
        });
        int ans = 0;
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++){
            q.offer(new Pair(i, 0, heights[i][0]));
            q.offer(new Pair(i, n-1, heights[i][n-1]));
            visited[i][0] = true;
            visited[i][n-1] = true;
        }
        for (int j = 0; j < n; j++){
            q.offer(new Pair(0, j, heights[0][j]));
            q.offer(new Pair(m-1, j, heights[m-1][j]));
            visited[0][j] = true;
            visited[m-1][j] = true;
        }
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        while (!q.isEmpty()){
            Pair curr = q.poll();
            for (int i = 0; i < dx.length; i++){
                int x = curr.x + dx[i];
                int y = curr.y + dy[i];
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
                    visited[x][y] = true;
                    ans += Math.max(0, curr.h - heights[x][y]);
                    q.offer(new Pair(x, y, Math.max(curr.h, heights[x][y])));
                }
            }
        }
        return ans;
    }
}
