There are G people in a gang, and a list of various crimes they could commit.

The i-th crime generates a profit[i] and requires group[i] gang members to participate.

If a gang member participates in one crime, that member can't participate in another crime.

Let's call a profitable scheme any subset of these crimes that generates at least P profit, and the total number of gang members participating in that subset of crimes is at most G.

How many schemes can be chosen?  Since the answer may be very large, return it modulo 10^9 + 7.

 

Example 1:

Input: G = 5, P = 3, group = [2,2], profit = [2,3]
Output: 2
Explanation: 
To make a profit of at least 3, the gang could either commit crimes 0 and 1, or just crime 1.
In total, there are 2 schemes.
Example 2:

Input: G = 10, P = 5, group = [2,3,5], profit = [6,7,8]
Output: 7
Explanation: 
To make a profit of at least 5, the gang could commit any crimes, as long as they commit one.
There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
 

Note:

1 <= G <= 100
0 <= P <= 100
1 <= group[i] <= 100
0 <= profit[i] <= 100
1 <= group.length = profit.length <= 100

Method 1: 3D DP
class Solution {
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int MOD = (int)1e9 + 7;
        int N = group.length;
        long[][][] dp = new long[N+1][G+1][P+1]; //at iteration k, the schema number of i members and j profit
        dp[0][0][0] = 1;
        for (int k = 0; k < N; k++){
            int currG = group[k];
            int currP = profit[k];            
            for (int i = 0; i <= G - currG; i++){
                for (int j = 0; j <= P; j++){
                    dp[k+1][i+currG][Math.min(j+currP, P)] = (dp[k][i][j] + dp[k][i+currG][Math.min(j+currP, P)]) % MOD;
                }
            }
        }
        long res = 0;
        for (int i = 0; i <= G; i++){
            res = (res + dp[N][i][P]) % MOD;
        }
        return (int) res;
    }
}


Method 2: Best solution 2D DP
We use backward to iteration to save one dimension because the bottom right elements depends on the old top left elements.
class Solution {
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int MOD = (int)1e9 + 7;
        int N = group.length;
        long[][] dp = new long[G+1][P+1]; // the schema number of i members and j profit
        dp[0][0] = 1;
        for (int k = 0; k < N; k++){
            int currG = group[k];
            int currP = profit[k];            
            for (int i = G- currG; i >= 0; i--){
                for (int j = P; j>= 0; j--){
                    dp[i+currG][Math.min(j+currP, P)] = (dp[i][j] + dp[i+currG][Math.min(j+currP, P)]) % MOD;
                }
            }
        }
        long res = 0;
        for (int i = 0; i <= G; i++){
            res = (res + dp[i][P]) % MOD;
        }
        return (int) res;
    }
}
