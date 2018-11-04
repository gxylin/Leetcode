This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.

 

Example 1:

Input: 1
Output: 10
Example 2:

Input: 2
Output: 20
Example 3:

Input: 3
Output: 46
 

Note:

1 <= N <= 5000

Top down DP

Probably this is the simplest top down dynamic programming solution
dp[i][j] denotes solution (e.g., distinct numbers) the i-th move and j-th key. The relationship between dp[i][j] and last step dp[i-1]
are explicitly written as below. This solution could be further simplified as 1D DP and array to respresent different cases more 
conciesly. But I think it is more intutivie to put it like this way below.

class Solution {
    public int knightDialer(int N) {
        int mod = (int)Math.pow(10, 9) + 7;
        int[][] dp = new int[N][10];
        for (int j = 0; j <= 9; j++){
            dp[0][j] = 1;
        }
        for (int i = 1; i < N; i++){
            for (int j = 0;j <= 9; j++){
                if (j == 0){
                    dp[i][j] = (dp[i-1][4] + dp[i-1][6]) % mod;
                }else if (j == 1){
                    dp[i][j] = (dp[i-1][6] + dp[i-1][8]) % mod;
                }else if (j == 2){
                    dp[i][j] = (dp[i-1][7] + dp[i-1][9]) % mod;
                }else if (j == 3){
                    dp[i][j] = (dp[i-1][4] + dp[i-1][8]) % mod;
                }else if (j == 4){
                    dp[i][j] = ((dp[i-1][0] + dp[i-1][3]) % mod + dp[i-1][9]) % mod;
                }else if (j == 5){
                    dp[i][j] = 0;
                }else if (j == 6){
                    dp[i][j] = ((dp[i-1][0] + dp[i-1][1]) % mod + dp[i-1][7]) % mod;
                }else if (j == 7){
                    dp[i][j] = (dp[i-1][2] + dp[i-1][6]) % mod;
                }else if (j == 8){
                    dp[i][j] = (dp[i-1][1] + dp[i-1][3]) % mod;
                }else if (j == 9){
                    dp[i][j] = (dp[i-1][2] + dp[i-1][4]) % mod;
                }
            }
        }
        int res = 0;
        for (int j = 0; j <= 9; j++){
            res = (res + dp[N-1][j]) % mod;
        }
        return res;
    }
}
