Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false


Method: 2D Dynamic programming

The most confusing part for me is how to deal with ‘*’. At first I couldn’t figure out why 
the condition would be (dp[i-1][j] == true || dp[i][j-1] == true). Hope detailed DP description below helps!

dp[i][j]: true if the first i char in String s matches the first j chars in String p
Base case:
origin: dp[0][0]: they do match, so dp[0][0] = true
first row: dp[0][j]: except for String p starts with *, otherwise all false
first col: dp[i][0]: can't match when p is empty. All false.
Recursion:
Iterate through every dp[i][j] 
dp[i][j] = true:
    if (s[ith] == p[jth] || p[jth] == '?') && dp[i-1][j-1] == true 
    elif p[jth] == '*' && (dp[i-1][j] == true || dp[i][j-1] == true) 
        -for dp[i-1][j], means that * acts like an empty sequence. eg: ab, ab*
        -for dp[i][j-1], means that * acts like any sequences. eg: abcd, ab*
Start from 0 to len Output put should be dp[s.len][p.len], referring to the whole s matches the whole p
Be careful about the difference of index i,j in String (0 to len-1) and the index i, j in dp (0 to len)!

class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null ||  p == null ){
            return false;
        }
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        for (int i = 1; i <= m; i++){
            dp[i][0] = false;
        }
        for (int j = 1; j <= n; j++){
            if (pc[j-1] == '*'){
                dp[0][j] = dp[0][j-1];
            }
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (dp[i-1][j-1] && (sc[i-1] == pc[j-1] || pc[j-1] == '?')){
                    dp[i][j] = true;
                }else if (pc[j-1] == '*' && (dp[i-1][j] || dp[i][j-1])){
                    dp[i][j] = true;
                }
            }
        }
        return dp[m][n];
    }
}
