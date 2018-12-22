Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

Similar to Longest palindromic substring
Time complexity: O(n^2)
class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++){
            count += expand(s, i, i);
            count += expand(s, i, i+1);
        }
        return count;
    }
    private int expand(String s, int i, int j){
        int ans = 0;
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)){
            i--;
            j++;
            ans++;
        }
        return ans;
    }
}

Method 2: DP Best solution
https://github.com/optimisea/Leetcode/blob/master/Java/5_LongestPalindromicSubstring.java

O(n^2)
class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = n-1; i >= 0; i--){
            for (int j = i; j < n; j++){
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j-1]);
                if (dp[i][j]){
                    count++;
                }
            }
        }
        return count;
    }
}
