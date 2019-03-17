In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

Train tickets are sold in 3 different ways:

a 1-day pass is sold for costs[0] dollars;
a 7-day pass is sold for costs[1] dollars;
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

Return the minimum number of dollars you need to travel every day in the given list of days.

 

Example 1:

Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: 
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total you spent $11 and covered all the days of your travel.
Example 2:

Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation: 
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total you spent $17 and covered all the days of your travel.
 

Note:

1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000

https://leetcode.com/problems/minimum-cost-for-tickets/discuss/226670/Java-DP-Solution-with-explanation-O(n)

Similar as Coin Change

If no trip on day i, then minCost(i) = minCost(i-1).
minCost(i)=0 for all i ≤ 0.
Otherwise:
If a 1-day pass on day i. In this case, minCost(i) = minCost(i) + costs[0].
If a 7-day pass ending on day i. then : In this case, minCost(i) = min(minCost(i − 7), minCost(i − 6), …, minCost(i − 1)) + costs[1].
But since since minCost is increasing (adding a day never reduces the minCost) hence:
minCost(i) = minCost(i − 7) + costs[2]

Time complexity: O(M*N)
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int max = days[days.length-1];
        boolean[] travelDay = new boolean[max+1];
        for (int day : days){
            travelDay[day] = true;
        }
        int[] costDay = {1, 7, 30};
        int[] dp = new int[max+1];
        for (int i = 1; i <= max; i++){
            if (!travelDay[i]){
                dp[i] = dp[i-1];
            }else{
                dp[i] = Integer.MAX_VALUE;
                for (int j = 0; j < costs.length; j++){
                    int cand = costs[j];
                    if (i >= costDay[j]){
                       cand += dp[i - costDay[j]];
                    }
                    dp[i] = Math.min(dp[i], cand);
                }
            }
        }
        return dp[max];
    }
}
