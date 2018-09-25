We are given S, a length n string of characters from the set {'D', 'I'}. (These letters stand for "decreasing" and "increasing".)

A valid permutation is a permutation P[0], P[1], ..., P[n] of integers {0, 1, ..., n}, such that for all i:

If S[i] == 'D', then P[i] > P[i+1], and;
If S[i] == 'I', then P[i] < P[i+1].
How many valid permutations are there?  Since the answer may be large, return your answer modulo 10^9 + 7.

 

Example 1:

Input: "DID"
Output: 5
Explanation: 
The 5 valid permutations of (0, 1, 2, 3) are:
(1, 0, 3, 2)
(2, 0, 3, 1)
(2, 1, 3, 0)
(3, 0, 2, 1)
(3, 1, 2, 0)
 

Note:

1 <= S.length <= 200
S consists only of characters from the set {'D', 'I'}.
https://leetcode.com/problems/valid-permutations-for-di-sequence/discuss/168278/C++JavaPython-DP-Solution-O(N2)?page=2


Method: DP
Time complexity: O(N^2)
 
dp[i][j] means the number of possible permutations of first i + 1 digits,
where the i + 1th digit is j + 1th smallest in the rest of digits.
 
class Solution {
    public int numPermsDISequence(String S) {
        int mod = (int)1e9 + 7;
        int n = S.length();
        int[][] dp = new int[n+1][n+1];
        for (int j = 0; j <= n; j++){
            dp[0][j] = 1;
        }
        for (int i = 1; i <= n; i++){
            int cursum = 0; //use cursum to accumulate the sum to avoid one more dimensional iteration
            if (S.charAt(i-1) == 'D'){
                for (int j = n-i; j >= 0; j--){//backward
                    dp[i][j] = (cursum + dp[i-1][j+1]) % mod;  
                    cursum = (cursum + dp[i-1][j+1]) % mod;
                }
            }else {//forward
                for (int j = 0; j < n-i+1; j++){
                    dp[i][j] = (cursum + dp[i-1][j]) % mod;
                    cursum = (cursum + dp[i-1][j]) % mod;
                }
            }
        }
        return dp[n][0];
    }
}
