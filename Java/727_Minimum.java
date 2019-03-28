Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length
windows, return the one with the left-most starting index.

Example 1:

Input: 
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation: 
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.

 

Note:

    All the strings in the input will only contain lowercase letters.
    The length of S will be in the range [1, 20000].
    The length of T will be in the range [1, 100].

Different from minmium window substring, here is the difference: T is a subsequence of W which requires to keep the order
https://github.com/optimisea/Leetcode/blob/master/Java/76_MinimumWindowSubstring.java

Method 1: DP
Time complexity: O(mn)
Space complexity: O(mn)
class Solution {
    public String minWindow(String S, String T) {
        int m = T.length();
        int n = S.length();
        int[][] dp = new int[m+1][n+1];
     // the largest starting index in String s that the first i in String T and first j in String S could match. 
        for (int j = 0; j <= n; j++){
            dp[0][j] = j+1; //initalize by adding 1 to differentiate between index and invalid cases
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (T.charAt(i-1) == S.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        int head = 0;
        int len = Integer.MAX_VALUE;
        for (int j = 1; j <= n; j++){
            if (dp[m][j] != 0){
                int cand = j - (dp[m][j] - 1);
                if (cand < len){
                    head = dp[m][j] - 1;
                    len = cand;
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : S.substring(head, head + len);
    }
}

Best solution:
class Solution {
    public String minWindow(String S, String T) {
        int m = T.length();
        int n = S.length();
        int[][] dp = new int[m+1][n+1];
// the largest START index + 1 (or length)
 //in String s that the first i (ending at i)in String T and first j (ending at j) in String S could match. 
        for (int i = 0; i <= m; i++){
            Arrays.fill(dp[i], -1);
        }
        for (int j = 0; j <= n; j++){
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (T.charAt(i-1) == S.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        int head = 0;
        int len = Integer.MAX_VALUE;
        for (int j = 1; j <= n; j++){
            if (dp[m][j] != -1){
                int cand = j - dp[m][j];
                if (cand < len){
                    head = dp[m][j];
                    len = cand;
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : S.substring(head, head + len);
    }
}
