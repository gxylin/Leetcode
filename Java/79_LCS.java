Given two strings, find the longest common substring.

Return the length of it.

 Notice
The characters in substring should occur continuously in original string. This is different with subsequence.

Have you met this question in a real interview? 
Example
Given A = "ABCD", B = "CBCE", return 2.

Challenge 
O(n x m) time and memory.

https://www.jiuzhang.com/solution/longest-common-substring/

public class Solution {
    /**
     * @param A: A string
     * @param B: A string
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (A.charAt(i-1) == B.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
