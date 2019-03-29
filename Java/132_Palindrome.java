Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
https://leetcode.com/problems/palindrome-partitioning-ii/discuss/42213/Easiest-Java-DP-Solution-(97.36)

Best solution:
class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];
        boolean[][] cache = new boolean[n][n];
        for (int i = 0; i < n; i++){
            dp[i] = i;
            for (int j = 0; j <= i; j++){
                if ((j+1 > i - 1 || cache[j+1][i-1]) && s.charAt(j) == s.charAt(i)){
                    cache[j][i] = true;
                    dp[i] = j == 0 ? 0 : Math.min(dp[i], dp[j-1] + 1);
                }
            }
        }
        return dp[n-1];
    }
}


Similar as Longest Increasing Sequence
Time complexity: O(n^2)
Space complexity: O(n^2)
class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] cut = new int[n];
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++){
            cut[i] = i;
            for (int j = 0; j  <= i; j++){
                if ((j + 1 > i - 1 || dp[j+1][i-1]) && s.charAt(j) == s.charAt(i)){
                    dp[j][i] = true;
                    cut[i] = j == 0 ? 0 : Math.min(cut[i], cut[j-1] + 1);
                }
            }
        }
        return cut[n-1];
    }
}

https://leetcode.com/problems/palindrome-partitioning-ii/discuss/42213/Easiest-Java-DP-Solution-(97.36)
This can be solved by two points:

cut[i] is the minimum of cut[j - 1] + 1 (j <= i), if [j, i] is palindrome.
If [j, i] is palindrome, [j + 1, i - 1] is palindrome, and c[j] == c[i].
The 2nd point reminds us of using dp (caching).

a   b   a   |   c  c
                j  i
       j-1  |  [j, i] is palindrome
   cut(j-1) +  1
    
    
Best solution:
class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        boolean[][] cache = new boolean[n][n];
        for (int i = 1; i <= n; i++){
            dp[i] = i-1;
            for (int j = 0; j < i; j++){
                if ((j + 1 >= i - 2 || cache[j+1][i-2]) && s.charAt(i-1) == s.charAt(j)){
                    cache[j][i-1] = true;
                    dp[i] = j == 0 ? 0 : Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n];
    }
}

class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];
        boolean[][] cache = new boolean[n][n];
        for (int i = 0; i < n; i++){
            dp[i] = i;
            for (int j = 0; j <= i; j++){
                if ((j+1 > i - 1 || cache[j+1][i-1]) &&s.charAt(i) == s.charAt(j)){
                    cache[j][i] = true;
                    dp[i] = j == 0 ? 0 : Math.min(dp[i], dp[j-1] + 1);
                }
            }
        }
        return dp[n-1];
    }
}

Method 2:
Time complexity: O(n^3)
Space complexity: O(n) 
class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++){
            dp[i] = i;
            for (int j = 0; j <= i; j++){
                if (isValid(s, j, i)){
                    dp[i] = j == 0 ? 0 : Math.min(dp[i], dp[j-1] + 1);
                }
            }
        }
        return dp[n-1];
    }
    private boolean isValid(String s, int j, int i){
        while (j < i){
            if (s.charAt(j) != s.charAt(i)){
                return false;
            }
            j++;
            i--;
        }
        return true;
    }
}

class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++){
            dp[i] = i-1;
            for (int j = 0; j < i; j++){
                if (isValid(s, j, i-1)){
                    dp[i] = j == 0 ? 0 : Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n];
    }
    private boolean isValid(String s, int j, int i){
        while (j < i){
            if (s.charAt(j) != s.charAt(i)){
                return false;
            }
            j++;
            i--;
        }
        return true;
    }
}

class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++){
            dp[i] = i;
            for (int j = 0; j <= i; j++){
                if (isValid(s, j, i)){
                    dp[i] = j == 0 ? 0 : Math.min(dp[i], dp[j-1] + 1);
                }
            }
        }
        return dp[n-1];
    }
    private boolean isValid(String s, int j, int i){
        while (j < i){
            if (s.charAt(i) != s.charAt(j)){
                return false;
            }
            j++;
            i--;
        }
        return true;
    }
}
