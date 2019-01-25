The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms
laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon 
to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, 
he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms 
are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path 
RIGHT-> RIGHT -> DOWN -> DOWN.

Method 1: f[i][j] denotes the minimal hp needed to exit from dungeon[i][j]
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] f = new int[m][n];
        f[m-1][n-1] = 1;
        for (int i = m-2; i>=0; i--){
            f[i][n-1] = f[i+1][n-1] - dungeon[i+1][n-1];
            if (f[i][n-1] <= 0){
                f[i][n-1] = 1;
            }
        }
        for (int i = n-2; i>=0; i--){
            f[m-1][i] = f[m-1][i+1] - dungeon[m-1][i+1];
            if (f[m-1][i] <= 0){
                f[m-1][i] = 1;
            }
        }
        for (int i = m-2; i>=0; i--){
            for (int j = n-2; j>=0; j--){
                f[i][j] = Math.min(f[i+1][j] - dungeon[i+1][j], f[i][j+1] - dungeon[i][j+1]); 
                if (f[i][j] <= 0){
                    f[i][j] = 1;
                }
            }
        }
        return Math.max(f[0][0] - dungeon[0][0], 1);
    }
}

Method 2: Better solution: health[i][j] denotes the minimal hp needed to enter into dungeon[i][j]
public int calculateMinimumHP(int[][] dungeon) {
    if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
    
    int m = dungeon.length;
    int n = dungeon[0].length;
    
    int[][] health = new int[m][n];

    health[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);

    for (int i = m - 2; i >= 0; i--) {            
        health[i][n - 1] = Math.max(health[i + 1][n - 1] - dungeon[i][n - 1], 1);
    }

    for (int j = n - 2; j >= 0; j--) {
        health[m - 1][j] = Math.max(health[m - 1][j + 1] - dungeon[m - 1][j], 1);
    }

    for (int i = m - 2; i >= 0; i--) {
        for (int j = n - 2; j >= 0; j--) {
            int down = Math.max(health[i + 1][j] - dungeon[i][j], 1);
            int right = Math.max(health[i][j + 1] - dungeon[i][j], 1);
            health[i][j] = Math.min(right, down);
        }
    }

    return health[0][0];
}


class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0){
            return 0;
        }
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = Math.max(1 - dungeon[m-1][n-1], 1);
        for (int i = m - 2; i >= 0; i--){
            dp[i][n-1] = Math.max(dp[i+1][n-1] - dungeon[i][n-1], 1);
        }
        for (int j = n - 2; j >= 0; j--){
            dp[m-1][j] = Math.max(dp[m-1][j+1] - dungeon[m-1][j], 1);
        }
        for (int i = m - 2; i >= 0; i--){
            for (int j = n- 2; j >= 0; j--){
                int down = Math.max(dp[i+1][j] - dungeon[i][j], 1);
                int right = Math.max(dp[i][j+1] - dungeon[i][j], 1);
                dp[i][j] = Math.min(down, right);
            }
        }
        return dp[0][0];
    }
}
