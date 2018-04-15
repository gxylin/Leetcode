/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 The cost of painting each house with a certain color is different. You have to paint all the houses such that no 
 two adjacent houses have the same color.
 The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, 
 costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color 
 green, and so on... Find the minimum cost to paint all houses.
 Note:
 All costs are positive integers.
 */
 
 Method: top-down dp
 public int minCost(int[][] costs) {
        int n = cost.length;
        int r = 0;
        int g = 0;
        int b = 0;
        int preR = 0;
        int preG = 0;
        int preB = 0;
        for (int i = 0; i < n; i++) {
            r = costs[i][0] + Math.min(preG, preB);
            g = costs[i][1] + Math.min(preR, preB);
            b = costs[i][2] + Math.min(preR, preG);
            preR = r;
            preG = g;
            preB = b;
        }
        return Math.min(r, Math.min(g,b));
    }
  
  Method 2: generalization
  public int minCost(int[][] costs) {
        int n = cost.length;
        int m = cost[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++){
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < m; k++){
                    if (j != k){
                       min = Math.min(min, dp[i-1][k];
                   }
                }
                dp[i][j] = cost[i][j] + min;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++){
           ans = Math.min(ans, dp[n-1][i]);
        }
        return ans;
    }
    
  Method 2: generalization + rolling array
  public int minCost(int[][] costs) {
        int n = cost.length;
        int m = cost[0].length;
        int[][] dp = new int[2][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++){
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < m; k++){
                    if (j != k){
                       min = Math.min(min, dp[(i-1)%2][k];
                   }
                }
                dp[i%2][j] = cost[i%2][j] + min;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++){
           ans = Math.min(ans, dp[(n-1)%2][i]);
        }
        return ans;
    }
