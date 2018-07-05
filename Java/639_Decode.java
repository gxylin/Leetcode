A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
Example 2:
Input: "1*"
Output: 9 + 9 = 18
Note:
The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.


class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int n = s.length();
        long[] dp = new long[n+1];
        dp[0] = 1;
        if (s.charAt(0) == '0'){
            dp[1] = 0;
        }else if (s.charAt(0) == '*'){
            dp[1] = 9;
        }else{
            dp[1] = 1;
        }
        for (int i = 2; i <= n; i++){
            char first = s.charAt(i-2);
            char second = s.charAt(i-1);
            if (second != '0'){
                if (second == '*'){
                    dp[i] += dp[i-1] * 9;
                }else{
                    dp[i] += dp[i-1];
                }
            }
            if (first != '*' && second != '*'){
                int twoDigits = (first - '0') * 10 + (second - '0');
                if (twoDigits <= 26 && twoDigits >= 10){
                    dp[i] += dp[i-2];
                }
            }else if (first == '*' && second != '*'){
                if (second - '0' <= 6){
                    dp[i] += dp[i-2] * 2;
                }else{
                    dp[i] += dp[i-2];
                }
            }else if (first != '*' && second == '*'){
                if (first - '0' == 2){
                    dp[i] += dp[i-2] * 6;
                }else if (first -'0' == 1){
                    dp[i] += dp[i-2] * 9;
                }
            }else{
                dp[i] += dp[i-2] * 15;
            }
            dp[i] %= 1000000007;
        }
        return (int)(dp[n]);
    }
}

https://leetcode.com/problems/decode-ways-ii/discuss/105258/Java-O(N)-by-General-Solution-for-all-DP-problems
