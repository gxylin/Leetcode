Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

Method 1: 50ms
O(mn)
class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0){
            return true;
        }
        int indexS = 0;
        int indexT = 0;
        while (indexT < t.length()){
            if (s.charAt(indexS) == t.charAt(indexT)){
                indexS++;
                if (indexS == s.length()){
                    return true;
                }
            }
            indexT++;
        }
        return false;
    }
}
https://leetcode.com/problems/is-subsequence/discuss/87297/Java.-Only-2ms.-Much-faster-than-normal-2-pointers.
Difference between Method 1 and Method 2:
the origin code of func "indexOf" and "charAt". These two solution both traversed the char of String one by one to search 
the first occurrence specific char.
The difference is that indexOf only call once function then traversed in "String.value[]" arr, 
but we used multiple calling function "charAt" to get the value in "String.value[]" arr.
The time expense of calling function made the difference.
    
Good solution
Method 2: 2ms
class Solution {
    public boolean isSubsequence(String s, String t) {
        if (t.length() < s.length()){
            return false;
        }
        int prev = 0;
        int indexS = 0;
        while (indexS < s.length()){
            prev = t.indexOf(s.charAt(indexS), prev);
            if (prev == - 1){
                return false;
            }
            indexS++;
            prev++;
        }
        return true;
    }
}
