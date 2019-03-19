
In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting
of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used 
at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.
Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".


Method 1: recursion OLE, so need use DP to save solved problem to remove duplicate calculation
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        return maxForm(strs, m, n, 0);
    }
    private int maxForm(String[] strs, int m , int n, int start){
        if (start == strs.length || m+n == 0){
            return 0;
        }
        int ones = 0;
        int zeros = 0;
        String s = strs[start];
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '0'){
                zeros++;
            }else if (c == '1'){
                ones++;
            }
        }
        int maxWithCurrStr= 0;
        if (m >= zeros && n >= ones){
            maxWithCurrStr =  maxForm(strs, m - zeros, n - ones, start + 1) + 1;
        }
        int maxWithoutCurrStr = maxForm(strs, m, n, start + 1);
        return Math.max(maxWithCurrStr, maxWithoutCurrStr);
    }
}
https://leetcode.com/problems/ones-and-zeroes/discuss/95845/Easy-to-understand-Recursive-Solutions-in-Java-with-Explanation

Method 2:
class Solution {
    private int[][][] hash;
    public int findMaxForm(String[] strs, int m, int n) {
        hash = new int[m+1][n+1][strs.length];
        return maxForm(strs, m, n, 0);
    }
    private int maxForm(String[] strs, int m , int n, int start){
        if (start == strs.length || m+n == 0){
            return 0;
        }
        if (hash[m][n][start] != 0){
            return hash[m][n][start];
        }
        int ones = 0;
        int zeros = 0;
        String s = strs[start];
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '0'){
                zeros++;
            }else if (c == '1'){
                ones++;
            }
        }
        int maxWithCurrStr= 0;
        if (m >= zeros && n >= ones){
            maxWithCurrStr =  maxForm(strs, m - zeros, n - ones, start + 1) + 1;
        }
        int maxWithoutCurrStr = maxForm(strs, m, n, start + 1);
        hash[m][n][start] = Math.max(maxWithCurrStr, maxWithoutCurrStr);
        return hash[m][n][start];
    }
}

https://leetcode.com/problems/ones-and-zeroes/discuss/95807/0-1-knapsack-detailed-explanation.

Method 3: Best,similar to a backpack(Partition Equal Subset Sum) problem
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][][] dp = new int[l+1][m+1][n+1];
        for (int i = 1; i <= l; i++){
            int zeros = 0;
            int ones = 0;
            String s = strs[i-1];
            for (int j = 0; j < s.length(); j++){
                if (s.charAt(j) == '0'){
                       zeros++;
                }else{
                    ones++;
                }
            }
            for (int j = 0; j <= m; j++){
                for (int k = 0; k <= n; k++){
                    if (j >= zeros && k >= ones){
                        dp[i][j][k] = Math.max(dp[i-1][j-zeros][k-ones] + 1, dp[i-1][j][k]);
                    }else{
                        dp[i][j][k] = dp[i-1][j][k];
                    }
                }
            }
        }
        return dp[l][m][n];
    }
}
Method 4: reduce space complexity by using rolling array
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][][] dp = new int[2][m+1][n+1];
        for (int i = 1; i <= l; i++){
            int zeros = 0;
            int ones = 0;
            String s = strs[i-1];
            for (int j = 0; j < s.length(); j++){
                if (s.charAt(j) == '0'){
                       zeros++;
                }else{
                    ones++;
                }
            }
            for (int j = 0; j <= m; j++){
                for (int k = 0; k <= n; k++){
                    if (j >= zeros && k >= ones){
                        dp[i%2][j][k] = Math.max(dp[(i-1)%2][j-zeros][k-ones] + 1, dp[(i-1)%2][j][k]);
                    }else{
                        dp[i%2][j][k] = dp[(i-1)%2][j][k];
                    }
                }
            }
        }
        return dp[l%2][m][n];
    }
}

Method 5:

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < l; i++){
            int zeros = 0;
            int ones = 0;
            String s = strs[i];
            for (int j = 0; j < s.length(); j++){
                if (s.charAt(j) == '0'){
                    zeros++;
                }else{
                    ones++;
                }
            }
            for (int j = m; j >=0 ; j--){
                for (int k = n; k >= 0; k--){
                    if (j >= zeros && k >= ones){
                        dp[j][k] = Math.max(dp[j-zeros][k-ones] + 1, dp[j][k]);
                    }else{
                        dp[j][k] = dp[j][k];
                    }
                }
            }
        }
        return dp[m][n];
    }
}

