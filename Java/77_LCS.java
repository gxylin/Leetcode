Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.

Have you met this question in a real interview? 
Clarification
What's the definition of Longest Common Subsequence?

https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
http://baike.baidu.com/view/2020307.htm
Example
For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.

For "ABCD" and "EACB", the LCS is "AC", return 2.

http://www.jiuzhang.com/solutions/longest-common-subsequence/
public class Solution {
    /**
     * @param A: A string
     * @param B: A string
     * @return: The length of longest common subsequence of A and B
     */
    public int longestCommonSubsequence(String A, String B) {
       int m = A.length();
       int n = B.length();
       int[][] dp = new int[m+1][n+1];
       for (int i = 0; i <= m; i++){
           dp[i][0] = 0;
       }
       for (int j = 0; j <= n; j++){
           dp[0][j] = 0;
       }
       for (int i = 1; i <= m; i++){
           for (int j = 1; j <= n; j++){
               if (A.charAt(i-1) == B.charAt(j-1)){
                   dp[i][j] = Math.max(dp[i-1][j-1] + 1, Math.max(dp[i-1][j], dp[i][j-1]));
               }else{
                   dp[i][j] = Math.max(dp[i-1][j-1], Math.max(dp[i-1][j], dp[i][j-1]));
               }
           }
       }
       return dp[m][n];
    }
}
