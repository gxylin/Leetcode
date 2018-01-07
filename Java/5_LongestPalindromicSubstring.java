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

Method 2:
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        String str = "";
        for (int i = 0; i < s.length(); i++){
            String odd = expand(s, i, i);
            String even = expand(s, i, i+1);
            if (str.length() < odd.length()){
                str = odd;
            }
            if (str.length() < even.length()){
                str = even;
            }
        }
        return str;
    }
    private String expand(String s, int i, int j){
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)){
            i--;
            j++;
        }
        return s.substring(i+1, j);
    }
}

Method 3:
public class Solution {
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }
}
