Given a string S, count the number of distinct, non-empty subsequences of S .

Since the result may be large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: "abc"
Output: 7
Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
Example 2:

Input: "aba"
Output: 6
Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and "aba".
Example 3:

Input: "aaa"
Output: 3
Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".

https://www.jianshu.com/p/02501f516437
Dynamic Programming

class Solution {
    public int distinctSubseqII(String S) {
        int mod = (int)1e9 + 7;
        int[] end = new int[26];
        for (int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            int sum = 0;
            for (int j = 0; j < 26; j++){
                sum = (sum + end[j]) % mod;
            }
            end[c - 'a'] = sum + 1;// 1 means new SINGLE character
        }
        int res = 0;
        for (int i = 0; i < 26; i++){
            res = (res + end[i]) % mod;
        }
        return res;
    }
}
