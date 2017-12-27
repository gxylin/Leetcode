Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"

Key idea, every time we move to right, we only need to consider whether using this new character as 
tail could produce new palindrome string of length (current length +1) or (current length +2)

class Solution {
    public String longestPalindrome(String s) {
        String ans = "";
        int currLen = 0;
        for (int i = 0; i < s.length(); i++){
            if (isPalindrome(s, i - currLen - 1, i)){
                ans = s.substring(i - currLen - 1, i + 1);
                currLen += 2;
            }else if (isPalindrome(s, i - currLen, i)){
                ans = s.substring(i - currLen, i + 1);
                currLen++;
            }
        }
        return ans;
    }
    private boolean isPalindrome(String s, int start, int end){
        if (start < 0){
            return false;
        }
        while (start < end){
            if (s.charAt(start) == s.charAt(end)){
                start++;
                end--;
            }else{
                return false;
            }
        }
        return true;
    }
}
