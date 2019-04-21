There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.

 

Example 1:

Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation: 
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 

Note:

1 <= costs.length <= 100
It is guaranteed that costs.length is even.
1 <= costs[i][0], costs[i][1] <= 1000

Method 1: Greedy
https://leetcode.com/problems/two-city-scheduling/discuss/278716/C%2B%2B-O(n-log-n)-sort-by-cost-difference

Time complexity: O(NlogN)
Space complexity: O(1)
class Solution {
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>(){
           public int compare (int[] c1, int[] c2){
               return c1[0] - c1[1] - (c2[0] - c2[1]);
           } 
        });
        int res = 0;
        for (int i = 0; i < costs.length; i++){
            if (i < costs.length / 2){
                res += costs[i][0];
            }else{
                res += costs[i][1];
            }
        }
        return res;
    }
}



Method 2: DP
dp[i][j] represents the cost when considering first (i + j) people in which i people assigned to city A and j people assigned to city B.

Time complexity: O(N^2)
Space complexity: O(N^2)
class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int N = costs.length/2;
        int[][] dp = new int[N+1][N+1];//i people went to A, j people went to B
        for (int i = 1; i <= N; i++){
            dp[i][0] = dp[i-1][0] + costs[i-1][0];
        }
        for (int j = 1; j <= N; j++){
            dp[0][j] = dp[0][j-1] + costs[j-1][1];
        }
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= N; j++){
                dp[i][j] = Math.min(dp[i-1][j] + costs[i+j-1][0], dp[i][j-1] + costs[i+j-1][1]); 
            }
        }
        return dp[N][N];
    }
}
