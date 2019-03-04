A robot on an infinite grid starts at point (0, 0) and faces north.  The robot can receive one of three possible types of commands:

-2: turn left 90 degrees
-1: turn right 90 degrees
1 <= x <= 9: move forward x units
Some of the grid squares are obstacles. 

The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])

If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)

Return the square of the maximum Euclidean distance that the robot will be from the origin.

 

Example 1:

Input: commands = [4,-1,3], obstacles = []
Output: 25
Explanation: robot will go to (3, 4)
Example 2:

Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
Output: 65
Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)
 

Note:

0 <= commands.length <= 10000
0 <= obstacles.length <= 10000
-30000 <= obstacle[i][0] <= 30000
-30000 <= obstacle[i][1] <= 30000
The answer is guaranteed to be less than 2 ^ 31.

Method 1:
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int x = 0;
        int y = 0;
        Set<String> set = new HashSet<>();
        for (int[] obstacle : obstacles){
            set.add(obstacle[0] + ":" + obstacle[1]);
        }
        boolean yMove = true;
        boolean movePositive = true; //move towards to positive direction
        int res = 0;
        for (int command : commands){
            if (command < 0){
                if (yMove && movePositive){
                    if (command == -2){
                        movePositive = !movePositive;
                    }
                }else if (yMove && !movePositive){
                    if (command == -2){
                        movePositive = !movePositive;
                    }
                }else if (!yMove && movePositive){
                    if (command == -1){
                        movePositive = !movePositive;
                    }
                }else{
                    if (command == -1){
                        movePositive = !movePositive;
                    }
                }
                yMove = !yMove;
            }else{
                int step = 1;
                if (yMove && movePositive){
                    while (step <= command && !set.contains(x + ":" + (y+1))){
                        y++;
                        step++;
                    }
                }else if (yMove && !movePositive){
                    while (step <= command && !set.contains(x + ":" + (y-1))){
                        y--;
                        step++;
                    }
                }else if (!yMove && movePositive){
                    while (step <= command && !set.contains((x+1) + ":" + y)){
                        x++;
                        step++;
                    }
                }else{
                    while (step <= command && !set.contains((x-1) + ":" + y)){
                        x--;
                        step++;
                    }
                }
            }
            res = Math.max(res, x * x + y * y);
        }
        return res;
    }
}


Method 2: Better
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int res = 0;
        int x = 0;
        int y = 0;
        int[][] dirs = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};
        Set<String> set = new HashSet<>();
        for (int[] ob : obstacles){
            set.add(ob[0] + ":" + ob[1]);
        }
        int d = 0;
        int n = dirs.length;
        for (int c : commands){
            if (c == -1){
                d = (d + 1) % n;
            }else if (c == -2){
                d = (d + 3) % n;//note that in Java (-1) % 4 = -1
            }else{
                while (c > 0 && !set.contains(x + dirs[d][0] + ":" + (y + dirs[d][1]))){
                    x += dirs[d][0];
                    y += dirs[d][1];
                    c--;
                }
            }
            res = Math.max(res, x * x + y * y);
        }
        return res;
    }
}

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> set = new HashSet<>();
        for (int[] o : obstacles){
            set.add(o[0] + ":" + o[1]);
        }
        int x = 0;
        int y = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int max = 0;
        int d = 0;
        for (int c : commands){
            if (c == -1){
                d = (d + 1) % 4;
            }else if (c == -2){
                d = (d + 3) %4;
            }else{
                int[] dir = dirs[d];
                int nx = x + dir[0];
                int ny = y + dir[1];
                String key = nx + ":" + ny;
                while (c > 0 && !set.contains(key)){
                    x += dir[0];
                    y += dir[1];
                    c--;
                    nx = x + dir[0];
                    ny = y + dir[1];
                    key = nx + ":" + ny;
                }
            }
            max = Math.max(max, x * x + y * y);
        }
        return max;
    }
}
