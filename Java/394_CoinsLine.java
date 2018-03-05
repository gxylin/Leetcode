There are n coins in a line. Two players take turns to take one or two coins from right side 
until there are no more coins left. The player who take the last coin wins.

Could you please decide the first play will win or lose?

Have you met this question in a real interview? 
Example
n = 1, return true.

n = 2, return true.

n = 3, return false.

n = 4, return true.

n = 5, return true.

Challenge 
O(n) time and O(1) memory

http://www.jiuzhang.com/solutions/coins-in-a-line/
dp[i] denotes if the first player will win when there are i coins left
public class Solution {
    /**
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
       boolean[] dp = new boolean[n+1];
       if (n <= 0){
           return false;
       }else if (n == 1){
           return true;
       }else if (n ==2){
           return true;
       }else if (n == 3){
           return false;
       }
       dp[0] = false;
       dp[1] = true;
       dp[2] = true;
       dp[3] = false;
       for (int i = 4; i <= n; i++){
           dp[i] = (dp[i-4] && dp[i-3]) || (dp[i-3] && dp[i-2]);
       }
       return dp[n];
    }
}
