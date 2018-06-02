There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.

Note:

    There is only one ball and one destination in the maze.
    Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
    The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
    The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.




    Time complexity : O(mn)O(mn)O(mn). Complete traversal of maze will be done in the worst case. Here, mmm and nnn refers to the number of rows and coloumns of the maze.

    Space complexity : O(mn)O(mn)O(mn). visitedvisitedvisited array of size m∗nm*nm∗n is used and queuequeuequeue size can grow upto m∗nm*nm∗n in worst case.


Method 1: BFS
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        qx.offer(start[0]);
        qy.offer(start[1]);
        while (!qx.isEmpty()){
            int cx = qx.poll();
            int cy = qy.poll();
            if (cx == destination[0] && cy == destination[1]){
                return true;
            }
            for (int i = 0; i < dx.length; i++){
                int x = cx + dx[i];
                int y = cy + dy[i];
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0){
                    x += dx[i];
                    y += dy[i];
                }
                if (!visited[x-dx[i]][y-dy[i]]){
                    qx.offer(x-dx[i]);
                    qy.offer(y-dy[i]);
                    visited[x-dx[i]][y-dy[i]] = true;         
                }
            }
        }
        return false;
    }
}

One queue:
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        queue.offer(start);
        while (!queue.isEmpty()){
            int[] p = queue.poll();
            if (p[0] == destination[0] && p[1] == destination[1]){
                return true;
            }
            for (int i = 0; i < dir.length; i++){
                int x = p[0] + dir[i][0];
                int y = p[1] + dir[i][1];
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0){
                    x += dir[i][0];
                    y += dir[i][1];
                }
                if (!visited[x-dir[i][0]][y-dir[i][1]]){
                    queue.offer(new int[]{x-dir[i][0], y-dir[i][1]});
                    visited[x-dir[i][0]][y-dir[i][1]] = true;         
                }
            }
        }
        return false;
    }
}

Method 2: DFS
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }
    private boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited){
        if (start[0] == destination[0] && start[1] == destination[1]){
            return true;
        }
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int m = maze.length;
        int n = maze[0].length;
        for (int i = 0; i < dx.length; i++){
            int x = start[0] + dx[i];
            int y = start[1] + dy[i];
            while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0){
                x += dx[i];
                y += dy[i];
            }
            if (!visited[x-dx[i]][y-dy[i]]){
                visited[x-dx[i]][y-dy[i]] = true;
                if (dfs(maze, new int[]{x-dx[i], y-dy[i]}, destination, visited)){
                    return true;
                }
            }
        }
        return false;
    }
}

public class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }
    public boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        if (visited[start[0]][start[1]])
            return false;
        if (start[0] == destination[0] && start[1] == destination[1])
            return true;
        visited[start[0]][start[1]] = true;
        int r = start[1] + 1, l = start[1] - 1, u = start[0] - 1, d = start[0] + 1;
        while (r < maze[0].length && maze[start[0]][r] == 0) // right
            r++;
        if (dfs(maze, new int[] {start[0], r - 1}, destination, visited))
            return true;
        while (l >= 0 && maze[start[0]][l] == 0) //left
            l--;
        if (dfs(maze, new int[] {start[0], l + 1}, destination, visited))
            return true;
        while (u >= 0 && maze[u][start[1]] == 0) //up
            u--;
        if (dfs(maze, new int[] {u + 1, start[1]}, destination, visited))
            return true;
        while (d < maze.length && maze[d][start[1]] == 0) //down
            d++;
        if (dfs(maze, new int[] {d - 1, start[1]}, destination, visited))
            return true;
        return false;
    }
}

DFS:
In order to do this traversal, one of the simplest schemes is to undergo depth first search. In this case, we choose one path at a time and try to go as deep as possible into the levels of the tree before going for the next path. In order to implement this, we make use of a recursive function dfs(maze, start, desination, visited). This function takes the given mazemazemaze array, the startstartstart position and the destinationdestinationdestination position as its arguments along with a visitedvisitedvisited array. visitedvisitedvisited array is a 2-D boolean array of the same size as that of mazemazemaze. A True value at visited[i][j]visited[i][j]visited[i][j] represents that the current position has already been reached earlier during the path traversal. We make use of this array so as to keep track of the same paths being repeated over and over. We mark a True at the current position in the visitedvisitedvisited array once we reach that particular positon in the mazemazemaze.

From every startstartstart position, we can move continuously in either left, right, upward or downward direction till we reach the boundary or a wall. Thus, from the startstartstart position, we determine all the end points which can be reached by choosing the four directions. For each of the cases, the new endpoint will now act as the new start point for the traversals. The destination, obviously remains unchanged. Thus, now we call the same function four times for the four directions, each time with a new start point obtained previously.

If any of the function call returns a True value, it means we can reach the desination. 

