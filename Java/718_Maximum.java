Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].
Note:
1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100

Method: Same idea of Longest Common Substring
dp[i][j] is the length of longest common subarray ending with nums[i-1] and nums[j-1]
class Solution {
    public int findLength(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[][] dp = new int[m+1][n+1];
        int max = 0;
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (A[i-1] == B[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }
}

Note that the following code is for longest common subsequence not longest common substring

dp[i][j] is the length of longest common subarray ending or may not end with nums[i-1] and nums[j-1]

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
