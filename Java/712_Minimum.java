Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

Example 1:
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
Example 2:
Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
Note:

0 < s1.length, s2.length <= 1000.
All elements of each string will have an ASCII value in [97, 122].

Method: DP
the same as Edit Distance, Delete Operation for two strings
Convert character to ASCII:

char character = 'a';    
int ascii = (int) character;

Let dp[i][j] be the answer to the problem for the strings s1[i:], s2[j:].

When one of the input strings is empty, the answer is the ASCII-sum of the other string. We can calculate this cumulatively using code like dp[i][s2.length()] = dp[i+1][s2.length()] + s1.codePointAt(i).

When s1[i] == s2[j], we have dp[i][j] = dp[i+1][j+1] as we can ignore these two characters.

When s1[i] != s2[j], we will have to delete at least one of them. We'll have dp[i][j] as the minimum of the answers after both deletion options.

The solutions presented will use bottom-up dynamic programming.
    

class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++){
            dp[i][0] = (int) s1.charAt(i-1) + dp[i-1][0];
        }
        for (int j = 1; j <= n; j++){
            dp[0][j] = (int) s2.charAt(j-1) + dp[0][j-1];
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j] + (int) s1.charAt(i-1), dp[i][j-1] + (int) s2.charAt(j-1));
                }
            }
        }
        return dp[m][n];
    }
}
