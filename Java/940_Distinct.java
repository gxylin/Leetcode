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


Method 2: general form
class Solution {
    public int distinctSubseqII(String S) {
        int mod = (int)1e9 + 7;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()){
            int sum = 0;
            for (int val : map.values()){
                sum = (sum + val)%mod;
            }
            map.put(c, sum + 1);
        }
        int res = 0;
        for (int val : map.values()){
            res = (res + val) % mod;
        }
        return res;
    }
}


Method 2: 
https://leetcode.com/problems/distinct-subsequences-ii/discuss/192030/Java-DP-O(N2)-time-greater-O(N)-time-greater-O(1)-space

In the original post, 
if s[j] == s[i], do nothing to avoid duplicates.

The sentence is really confusing and misleading. and conceptually wrong.

When s[j] == s[i], the correct explanation is to add existing substring with one more letter, so the number will be the same as previous sum and hence no need to add more.

Take abcc as example,

ending:
a : a
b: ab b
c: ac abc bc c
c: ac abc bc c ====when scan to the first c, add one more letter, acc abcc bcc cc

O(n^2)
class Solution {
    public int distinctSubseqII(String S) {
        int mod = (int)1e9 + 7;
        int n = S.length();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < i; j++){
                if (S.charAt(i) != S.charAt(j)){
                    dp[i] = (dp[i] + dp[j])%mod;
                }
            }
            res = (res + dp[i]) % mod;
        }
        return res;
    }
}

The refer to Method 1 for O(N) solution
