Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
https://leetcode.com/problems/palindrome-partitioning-ii/discuss/42213/Easiest-Java-DP-Solution-(97.36)


class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] cut = new int[n];
        boolean[][] dp = new boolean[n+1][n+1];
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
