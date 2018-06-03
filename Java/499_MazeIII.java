There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"
Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".

Example 2

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (3, 0)
Output: "impossible"
Explanation: The ball cannot reach the hole.

Note:

    There is only one ball and one hole in the maze.
    Both the ball and hole exist on an empty space, and they will not be at the same position initially.
    The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
    The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.


class Solution {
    class Point{
        int x;
        int y;
        int dist;
        String s;
        public Point(int x, int y, int dist, String s){
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.s = s;
        }
    }
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length;
        int n = maze[0].length;
        Point[][] points = new Point[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (i == ball[0] && j == ball[1]){
                    points[i][j] = new Point(i, j, 0, "");
                }else{
                    points[i][j] = new Point(i, j, Integer.MAX_VALUE, "");
                } 
            }
        }
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        String[] str = {"d", "r", "u", "l"};
        Queue<Point> queue= new LinkedList<>();
        queue.offer(new Point(ball[0], ball[1], 0, ""));
        while (!queue.isEmpty()){
            Point p = queue.poll();
            for (int i = 0; i < dx.length; i++){
                int x = p.x;
                int y = p.y;
                int count = 0;
                String currStr = p.s + str[i];
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0 && (x != hole[0] || y != hole[1])){
                    x += dx[i];
                    y += dy[i];
                    count++;
                }
                if (x != hole[0] || y != hole[1]){
                    x -= dx[i];
                    y -= dy[i];
                    count--;
                }
                if (p.dist + count < points[x][y].dist || p.dist + count == points[x][y].dist && currStr.compareTo(points[x][y].s) < 0){
                    points[x][y].dist = p.dist + count;
                    points[x][y].s = currStr;
                    queue.offer(new Point(x, y, p.dist + count, currStr));
                }
            }
        }
        return points[hole[0]][hole[1]].dist != Integer.MAX_VALUE ? points[hole[0]][hole[1]].s : "impossible";
    }
}
