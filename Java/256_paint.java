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
  public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0){
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n+1][k];
        for (int i = 1; i <= n; i++){
            for (int j = 0; j < k; j++){
                int min = Integer.MAX_VALUE;
                for (int m = 0; m < k; m++){
                    if (j != m){
                        min = Math.min(min, dp[i-1][m]);
                    }
                }
                if (min != Integer.MAX_VALUE){
                    dp[i][j] = min + costs[i-1][j];
                }else{
                    dp[i][j] = costs[i-1][j];
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++){
            ans = Math.min(ans, dp[n][i]);
        }
        return ans;
    }
    
  Method 2: generalization + rolling array
  public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0){
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[2][k];
        for (int i = 1; i <= n; i++){
            for (int j = 0; j < k; j++){
                int min = Integer.MAX_VALUE;
                for (int m = 0; m < k; m++){
                    if (j != m){
                        min = Math.min(min, dp[(i-1)%2][m]);
                    }
                }
                if (min != Integer.MAX_VALUE){
                    dp[i%2][j] = min + costs[i-1][j];
                }else{
                    dp[i%2][j] = costs[i-1][j];
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++){
            ans = Math.min(ans, dp[n%2][i]);
        }
        return ans;
    }
}
