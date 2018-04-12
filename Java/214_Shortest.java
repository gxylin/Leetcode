Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. 
Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd"

https://leetcode.com/articles/shortest-palindrome/
Time complexity: O(n^2)
Space complexity: O(n)
class Solution {
    public String shortestPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for (int i = len - 1; i >= 0; i--){
            sb.append(s.charAt(i));
        }
        String rev = sb.toString();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < len; i++){
            if (s.substring(0, len - i).equals(rev.substring(i))){
                ans.append(rev.substring(0, i));
                ans.append(s);
                break;
            }
        }
        return ans.toString();
    }
}
