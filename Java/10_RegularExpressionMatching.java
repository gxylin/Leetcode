Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

https://leetcode.com/problems/regular-expression-matching/discuss/5651/Easy-DP-Java-Solution-with-detailed-Explanation

1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
3, If p.charAt(j) == '*': 
   here are two sub conditions:
               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
               2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
                           
                           
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null){
            return false;
        }
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        for (int i = 1; i <= n; i++){
            if (pc[i-1] == '*'){
                if (dp[0][i-1] || (i >= 2  && dp[0][i-2])){
                    dp[0][i] = true;
                }
            }
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (sc[i-1] == pc[j-1] || pc[j-1] == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }else if (pc[j-1] == '*'){
                    if (j >= 2 && sc[i-1] != pc[j-2] && pc[j-2] != '.'){
                        dp[i][j] = dp[i][j-2];
                    }else{
                        dp[i][j] = dp[i][j-1] || dp[i-1][j] || dp[i][j-2];
                    }
                }
            }
        }
        return dp[m][n];
    }
}


class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        for (int i = 1; i <= n; i++){
            if (pc[i-1] == '*'){
                dp[0][i] = dp[0][i-1] || dp[0][i-2];
            }
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (sc[i-1] == pc[j-1] || pc[j-1] == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }else if (pc[j-1] == '*'){
                    if (j >= 2 && sc[i-1] != pc[j-2] && pc[j-2] != '.'){
                        dp[i][j] = dp[i][j-2];
                    }else{
                        dp[i][j] = dp[i][j-1] || dp[i][j-2] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
}


Better version:
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        for (int i = 1; i <= n; i++){
            if (pc[i-1] == '*'){
                dp[0][i] = dp[0][i-1] || dp[0][i-2];
            }
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (sc[i-1] == pc[j-1] || pc[j-1] == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }else if (pc[j-1] == '*'){
                    if (j >= 2 && (pc[j-2] == sc[i-1] || pc[j-2] == '.')){
                        dp[i][j] = dp[i][j-2] || dp[i][j-1] || dp[i-1][j];
                    }else if (j >= 2){
                        dp[i][j] = dp[i][j-2];
                    }else{
                        dp[i][j] = dp[i][j-1] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
