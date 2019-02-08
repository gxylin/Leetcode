There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a 
certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] 
is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... 
Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5. 

Follow up:
Could you solve it in O(nk) runtime?

Method 1: Best solution  Very Good Analysis
Time complexity: O(nk)
Space complexity: O(1)
Explanation: dp[i][j] represents the min paint cost from house 0 to house i when house i use color j;
The formula will be dp[i][j] = Math.min(any k!= j| dp[i-1][k]) + costs[i][j].

Take a closer look at the formula, we don't need an array to represent dp[i][j], we only need to know the min cost to 
the previous house of any color and if the color j is used on previous house to get prev min cost, use the second min 
cost that are not using color j on the previous house. So I have three variable to record: prevMin, prevMinColor, prevSecondMin. 
and the above formula will be translated into: 

dp[currentHouse][currentColor] = (currentColor == prevMinColor? prevSecondMin: prevMin) + costs[currentHouse][currentColor].

class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0){
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;
        int prevMin = 0;
        int prevSecMin = 0;
        int prevMinInd = -1;
        for (int i = 0; i < n; i++){
            int currMin = Integer.MAX_VALUE;
            int currSecMin = Integer.MAX_VALUE;
            int currMinInd = -1;
            for (int j = 0; j < k; j++){
                int val = costs[i][j] + (j == prevMinInd ? prevSecMin : prevMin);
                if (val < currMin){
                    currSecMin = currMin;
                    currMin = val;
                    currMinInd = j;
                }else if (val < currSecMin){
                    currSecMin = val;
                }
            }
            prevMin = currMin;
            prevSecMin = currSecMin;
            prevMinInd = currMinInd;
        }
        return prevMin;
    }
}


Method 2: 
Time complexity: O(n*k*k)
Space complexity: O(n*k)
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
    
Method 3: rolling array
Time complexity: O(n*k*k)
Space complexity: O(k)
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
