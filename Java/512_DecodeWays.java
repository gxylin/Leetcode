A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

Have you met this question in a real interview? Yes
Example
Given encoded message 12, it could be decoded as AB (1 2) or L (12).
The number of ways decoding 12 is 2.

Method 1:
Dynamic Programming: 
• 此题解法:考虑最后一位怎么分解?
• f[n]表示从第0层走到第n层一共有多少种方法
• f[i]=f[i-1](条件s[i]!=0) + f[i-2](条件是s[i-1]与s[i]组成的数字在10-26之间)
• Note that the length of f should be s.length + 1

public class Solution {
    /*
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int[] f = new int[s.length() + 1];
        f[0] = 1;
        f[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= s.length(); i++){
            if (s.charAt(i-1) != '0'){
                f[i] += f[i-1];
            }
            int twoDigits = (s.charAt(i-2) - '0') * 10 + (s.charAt(i-1) - '0');
            if (twoDigits >= 10 && twoDigits <= 26){
                f[i] += f[i-2];
            }
        }
        return f[s.length()];
    }
}
