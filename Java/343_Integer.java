Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58.

Method 1: O(n^2)
https://leetcode.com/problems/integer-break/discuss/80694/Java-DP-solution
class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        // 1.Init except dp[n], since it cannot be itself and must break into two positive
        for (int i = 1; i < n; i++){
            dp[i] = i; // note that in the result, itself could be the largest one in product.
        }
        for (int i = 2; i <= n; i++){
            for (int j = 1; j <= i - j; j++){
                dp[i] = Math.max(dp[i], dp[j] * dp[i-j]);
            }
        }
        return dp[n];
    }
}

Method 2: O(n)
https://leetcode.com/problems/integer-break/discuss/80689/A-simple-explanation-of-the-math-part-and-a-O(n)-solution
class Solution {
    public int integerBreak(int n) {
        if (n== 2){
            return 1;
        }
        if (n == 3){
            return 2;
        }
        int ans = 1;
        while (n > 4){
            ans *= 3;
            n-= 3;
        }
        return ans * n;
    }
}
