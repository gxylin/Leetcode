You are given coins of different denominations and a total amount of money. Write a function to compute 
the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

Note: You can assume that

0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10] 
Output: 1
https://leetcode.com/problems/coin-change-2/discuss/141076/Logical-Thinking-with-Clear-Java-Code
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++){
            for (int j = 1; j <= amount; j++){
                if (j - coins[i] >= 0){
                    dp[j] += dp[j-coins[i]];
                }
            }
        }
  /*  this following code is not working : you will need to derive an equivalent formula to update dp 
  which may not be so obvious since you must deal with duplicated cases. 
  You can check #322 for Coin Change 1 where we could easily swap two for-loops since there we only compute min. numbers.
        for (int i = 1; i <= amount; i++){
            for (int j = 0; j < coins.length; j++){
                if (i - coins[j] >= 0){
                    dp[i] += dp[i-coins[j]];
                }
            }
        }*/
        return dp[amount];
    }
}

/*
Actually the above solution is reduced from 2D space complexity to 1D space complexity
backpack DP:
dp denotes the number of ways to reach amount with the number of count coins:
two cases: 1. take the last coin: the amount must be greater than lastCoin. then
              dp[amount][count] += dp[amount - lastCoin][count-1];
           2. not take the last coin: dp[amount][count] += dp[amount][count-1]
           
dp[amount][count] = dp[amount - lastCoin][count-1] + dp[amount][count-1]
*/
then since count only replies on prevous step, reduced to 1D space dp[amount]



