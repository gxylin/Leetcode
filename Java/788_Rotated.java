X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.

Now given a positive number N, how many numbers X from 1 to N are good?

Example:
Input: 10
Output: 4
Explanation: 
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
Note:

N  will be in range [1, 10000].

Method 1: Brute Force (32ms)
class Solution {
    public int rotatedDigits(int N) {
        int ans = 0;
        for (int i = 1; i <= N; i++){
            String s = String.valueOf(i);
            if ((s.indexOf("2") != -1 || s.indexOf("5") != -1 || s.indexOf("6") != -1 || s.indexOf("9") != -1) 
                && (s.indexOf("3") == -1 && s.indexOf("4") == -1 && s.indexOf("7") == -1)){
                ans++;
            }
        }
        return ans;
    }
}

class Solution {
    public int rotatedDigits(int N) {
        // Count how many n in [1, N] are good.
        int ans = 0;
        for (int n = 1; n <= N; ++n)
            if (good(n, false)) ans++;
        return ans;
    }

    // Return true if n is good.
    // The flag is true iff we have an occurrence of 2, 5, 6, 9.
    public boolean good(int n, boolean flag) {
        if (n == 0) return flag;

        int d = n % 10;
        if (d == 3 || d == 4 || d == 7) return false;
        if (d == 0 || d == 1 || d == 8) return good(n / 10, flag);
        return good(n / 10, true);
    }
}

Method 2: DP (9ms) Better solution than brute force
class Solution {
    public int rotatedDigits(int N) {
        int ans = 0;
        int[] dp = new int[N+1];
        for (int i = 0; i <= N; i++){
            if (i < 10){
                if (i == 0 || i == 1 || i == 8){
                    dp[i] = 1;
                }else if (i == 2 || i == 5 || i == 6 || i == 9){
                    dp[i] = 2;
                    ans++;
                }
            }else{
                int a = dp[i/10];
                int b = dp[i%10];
                if (a == 1 && b == 1){
                    dp[i] = 1;
                }else if (a >= 1 && b >= 1){
                    dp[i] = 2;
                    ans++;
                }
            }
        }
        return ans;
    }
}
