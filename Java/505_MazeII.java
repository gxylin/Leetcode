
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right,
but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination.
The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination 
(included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of
the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1
Explanation: There is no way for the ball to stop at the destination.

Note:

    There is only one ball and one destination in the maze.
    Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
    The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
    The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.


Method 1: Better solution
BFS (accepted)
Time complexity : O(m∗n∗max(m,n)). Complete traversal of maze will be done in the worst case. 
Further, for every current node chosen, we can travel upto a maximum depth of max(m,n) in any direction.

Space complexity : O(mn) queue size can grow upto m∗n in the worst case

class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] distance = new int[m][n];
        for (int[] row : distance){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        qx.offer(start[0]);
        qy.offer(start[1]);
        while (!qx.isEmpty()){
            int cx = qx.poll();
            int cy = qy.poll();
            for (int i = 0; i < dx.length; i++){
                int x = cx + dx[i];
                int y = cy + dy[i];
                int dist = 0;
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0){
                    x += dx[i];
                    y += dy[i];
                    dist++;
                }
                if (distance[cx][cy] + dist < distance[x-dx[i]][y-dy[i]]){
                    distance[x-dx[i]][y-dy[i]] = distance[cx][cy] + dist;
                    qx.offer(x-dx[i]);
                    qy.offer(y-dy[i]);
                }
            }
        }
        return distance[destination[0]][destination[1]] != Integer.MAX_VALUE ? distance[destination[0]][destination[1]] : -1;
    }
}

public class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        int[][] dirs={{0, 1} ,{0, -1}, {-1, 0}, {1, 0}};
        Queue <int[]> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir: dirs) {
                int x = curr[0] + dir[0];
                int y = curr[1] + dir[1];
                int step = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    step++;
                }
                if (distance[curr[0]][curr[1]] + step < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[curr[0]][curr[1]] + count;
                    queue.add(new int[] {x - dir[0], y - dir[1]});
                }
            }
        }
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }
}



Method 2:
backtracking (TLE)
class Solution {
    int minDist = Integer.MAX_VALUE;
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[start[0]][start[1]] = true;
        dfs(maze, start, destination, visited, 0);
        return minDist != Integer.MAX_VALUE ? minDist : -1;
    }
    private void dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited, int prevDist){
        if (start[0] == destination[0] && start[1] == destination[1]){
            minDist = Math.min(minDist, prevDist);
            return;
        }
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int m = maze.length;
        int n = maze[0].length;
        for (int i = 0; i < dx.length; i++){
            int x = start[0] + dx[i];
            int y = start[1] + dy[i];
            int dist = prevDist;
            while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0){
                x += dx[i];
                y += dy[i];
                dist++;
            }
            if (!visited[x-dx[i]][y-dy[i]]){
                visited[x-dx[i]][y-dy[i]] = true;
                dfs(maze, new int[]{x-dx[i], y-dy[i]}, destination, visited, dist);
                visited[x-dx[i]][y-dy[i]] = false;
            }
        }
    }
}


DFS (accepted)
class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row : distance){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        dfs(maze, start, destination, distance);
        return distance[destination[0]][destination[1]] != Integer.MAX_VALUE ? distance[destination[0]][destination[1]] : -1;
    }
    private void dfs(int[][] maze, int[] start, int[] destination, int[][] distance){
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int m = maze.length;
        int n = maze[0].length;
        for (int i = 0; i < dx.length; i++){
            int x = start[0] + dx[i];
            int y = start[1] + dy[i];
            int dist = 0;
            while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0){
                x += dx[i];
                y += dy[i];
                dist++;
            }
            if (distance[start[0]][start[1]] + dist < distance[x-dx[i]][y-dy[i]]){
                distance[x-dx[i]][y-dy[i]] = distance[start[0]][start[1]] + dist;
                dfs(maze, new int[]{x-dx[i], y-dy[i]}, destination, distance);
            }
        }
    }
}


Time complexity : O(m∗n∗max(m,n)). Complete traversal of maze will be done in the worst case. 

 Space complexity : O(mn). 

Method 3: Dijkstra Algorithm

Time complexity : O((mn)^2). Complete traversal of maze will be done in the worst case and function minDistance takes O(mn) time.

Space complexity : O(mn). distancedistancedistance array of size m∗n is used.

public class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        dijkstra(maze, distance, visited);
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }
    public int[] minDistance(int[][] distance, boolean[][] visited) {
        int[] min={-1,-1};
        int min_val = Integer.MAX_VALUE;
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                if (!visited[i][j] && distance[i][j] < min_val) {
                    min = new int[] {i, j};
                    min_val = distance[i][j];
                }
            }
        }
        return min;
    }
    public void dijkstra(int[][] maze, int[][] distance, boolean[][] visited) {
        int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
        while (true) {
            int[] s = minDistance(distance, visited);
            if (s[0] < 0)
                break;
            visited[s[0]][s[1]] = true;
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                }
            }
        }
    }
}

Method 4: Dijkstra Algorithm and Priority Queue (best solution)


Time complexity : O(mn∗log(mn)). Complete traversal of maze will be done in the worst case giving a factor of mn. 
Further, poll method is a combination of heapifying(O(log(n)) and removing the top elementO(1) from the priority queue, 
and it takes O(n) time for nnn elements. In the current case, poll introduces a factor of log(mn)log(mn)log(mn).

Space complexity : O(mn). distancedistancedistance array of size m∗n is used and queuequeuequeue size can grow upto m∗nm*nm∗n in
worst case.
public class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        dijkstra(maze, start, distance);
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    public void dijkstra(int[][] maze, int[] start, int[][] distance) {
        int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
        PriorityQueue < int[] > queue = new PriorityQueue < > ((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{start[0],start[1],0});
        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            if(distance[s[0]][s[1]] < s[2])
                continue;
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                    queue.offer(new int[]{x - dir[0], y - dir[1], distance[x - dir[0]][y - dir[1]]});
                }
            }
        }
    }
}
