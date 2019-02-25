Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters 
without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Example 1:

Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:

As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
Example 2:

Input: S = "babgbag", T = "bag"
Output: 5
Explanation:

As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
    
Method: Dynamic Programming
class Solution {
    public int numDistinct(String s, String t) {
        int m = t.length();
        int n = s.length();
        int[][] dp = new int[m+1][n+1];//the number of cases before and including index j of String t for index i of String t
        for (int j = 0; j <= n; j++){
            dp[0][j] = 1;
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (t.charAt(i-1) == s.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                }else{
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }
}

class Solution {
    public int numDistinct(String s, String t) {
        int m = t.length();
        int n = s.length();
        int[][] dp = new int[m+1][n+1];
        for (int j = 0; j <= n; j++){
            dp[0][j] = 1;
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (t.charAt(i-1) == s.charAt(j-1)){
                    dp[i][j] += dp[i-1][j-1];
                }
                dp[i][j] += dp[i][j-1];
            }
        }
        return dp[m][n];
    }
}


Best solution:
dp[i][j] denotes the number of distinct subsequence with the first i substring from s and the first j substring from t
when s.charAt(i-1) == t.charAt(j-1).
  Two options: if include s.charAt(i-1), then dp[i][j] += dp[i-1][j-1]
               if not include s.charAt(i-1), then dp[i][j] += dp[i-1][j]
 when s.charAt(i-1) != t.charAt(j-1)
  only one option: not include s.charAt(i-1), then dp[i][j] = dp[i-1][j];
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++){
            dp[i][0] = 1;//there  is only one way to match empty string t
        }
        for (int i = 1; i <= m; i++){
            for (int j =1; j <= n; j++){
                if (s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] += dp[i-1][j-1];
                }
                dp[i][j] += dp[i-1][j];
            }
        }
        return dp[m][n];
    }
}

class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++){
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++){
            for (int j =1; j <= n; j++){
                if (s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[m][n];
    }
}
