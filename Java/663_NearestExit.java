You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Have you met this question in a real interview? Yes
Example
Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
return the result:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

思路:
￼• 多源点单终点 == 单源点多终点,最短路常用转化套路
• 多个门,多源点多终点怎么办?
– 多个源点同时注水,看谁流得快
• 多源点同时注水,其实相当于增加了一个超级源点(dummy),超级源点 连接每个普通源点一条边
• 增加了超级源后,其实相当于从超级源的单源最短路

多源多终点 ==> 单源多终点 (增加超级源,最短路常用转化套路)

 小技巧总结:
• 多源点单终点 单源点多终点,最短路常用转化套路
• 多源多终点单源多终点 (增加超级源,最短路常用转化套路) • BFS可以求边长=1的图的最短路(如此题的棋盘图)

public class Solution {
    /*
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    public void wallsAndGates(int[][] rooms) {
        int n = rooms.length;
        if (n == 0){
            return;
        }
        int m = rooms[0].length;
        
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (rooms[i][j] == 0){
                    qx.offer(i);
                    qy.offer(j);
                }
            }
        }
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        while (!qx.isEmpty()){
            int cx = qx.poll();
            int cy = qy.poll();
            
            for (int i = 0; i < 4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m
                        && rooms[nx][ny] == Integer.MAX_VALUE){
                    rooms[nx][ny] = rooms[cx][cy] + 1;
                    qx.offer(nx);
                    qy.offer(ny);
                }
            }
        }
    }
}
