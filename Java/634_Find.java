 In combinatorial mathematics, a derangement is a permutation of the elements of a set, such that no element appears
 in its original position.

There's originally an array consisting of n integers from 1 to n in ascending order, you need to find the number of 
derangement it can generate.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:

Input: 3
Output: 2
Explanation: The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].

Note:
n is in the range of [1, 106]. 


class Solution {
    public int findDerangement(int n) {
        if (n == 0){
            return 1;
        }
        if (n == 1){
            return 0;
        }
        int mod = (int) Math.pow(10, 9) + 7;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 0;
        for (int i = 2; i <= n; i++){
            dp[i] = (int)(((i-1L) * (dp[i-1] + dp[i-2])) % mod);
        }
        return dp[n];
    }
}
