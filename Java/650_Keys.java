Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.

Example 1:
Input: 3
Output: 3
Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.

Method 1: 
Time complexity: O(n^2)
Space complexity: O(n)
class Solution {
    public int minSteps(int n) {
        int[] dp = new int[n+1];
        for (int i = 2; i <= n; i++){
            dp[i] = i;
            for (int j = 2; j < i; j++){
                if (i % j == 0){
                    dp[i] = Math.min(dp[i], dp[j] + i/j);
                }
            }
        }
        return dp[n];
    }
}

class Solution {
    public int minSteps(int n) {
        int[] dp = new int[n+1];
        for (int i = 2; i <= n; i++){
            dp[i] = i;
            for (int j = i-1; j >= 2; j--){
                if (i % j == 0){
                    dp[i] = Math.min(dp[i], dp[j] + i/j);
                    break;
                }
            }
        }
        return dp[n];
    }
}

Method 2: Best solution
Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public int minSteps(int n) {
        int ans = 0;
        int d = 2;
        while (n > 1){
            while (n % d == 0){
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }
}
