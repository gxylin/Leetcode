There are n coins with different value in a line. Two players take turns to take one or two coins
from left side until there are no more coins left. The player who take the coins with the most value wins.

Could you please decide the first player will win or lose?

Have you met this question in a real interview? 
Example
Given values array A = [1,2,2], return true.

Given A = [1,2,4], return false.

http://www.jiuzhang.com/solutions/coins-in-a-line-ii/
Method 1:
State:
• dp[i] 现在还剩i个硬币,现在先手取硬币的人最后最多取硬币价值
Function:
• i 是所有硬币数目
• coin[n-i] 表示倒数第i个硬币
• dp[i] = max(min(dp[i-2], dp[i-3])+coin[n-i] ) , (min(dp[i-3],dp[i-4])+coin[n-i]+coin[n-i+1] )
Initialize:
• dp[0] = 0
• dp[1] = coin[i-1]
• dp[2] = coin[i-2] + coin[i-1] • dp[3] = coin[i-2] + coin[i-3]
Answer:
• dp[n] > sum/2

    
Take 5 coins as an example
Note that opponent always wants to minimize yours
you want to maximize yours
public class Solution {
    /*
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        int n = values.length;
        int[] f = new int[n+1];
        if (n <= 0){
            return false;
        }else if (n == 1){
            return true;
        }else if (n == 2){
            return true;
        }else if (n == 3){
            return (values[0] + values[1]) > values[2];
        }
        f[0] = 0;
        f[1] = values[n-1];
        f[2] = values[n-1] + values[n-2];
        f[3] = values[n-2] + values[n-3];
        for (int i = 4; i <= n; i++){
            f[i] = Math.max(Math.min(f[i-2], f[i-3]) + values[n-i],
                Math.min(f[i-3], f[i-4]) + values[n-i] + values[n-i+1]);
        }
        int sum = 0;
        for (int i = 0; i < n; i++){
            sum += values[i];
        }
        return 2* f[n] > sum;
    }
}
