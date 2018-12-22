Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"
    
Best solution
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

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0){
            return "";
        }
        int[] res = new int[2];
        for (int i = 0; i < s.length(); i++){
            expand(s, i, i, res);
            expand(s, i, i+1, res);
        }
        return s.substring(res[0], res[0] + res[1]);
    }
    private void expand(String s, int start, int end, int[] res){
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
            start--;
            end++;
        }
        if (res[1] < end - start - 1){
            res[1] = end - start - 1;
            res[0] = start + 1;
        }
    }
}


    
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

Method 4: O(n^2) DP Best Solution
dp(i, j) represents whether s(i ... j) can form a palindromic substring, dp(i, j) is true when s(i) equals to s(j) and 
s(i+1 ... j-1) is a palindromic substring. When we found a palindrome, check if it's the longest one. 
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String res = "";
        for (int i = n-1; i >= 0; i--){
            for (int j = i; j < n; j++){
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j-1]);
                if (dp[i][j] && j - i + 1 > res.length()){
                    res = s.substring(i, j+1);
                }
            }
        }
        return res;
    }
}


class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String res = "";
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= i; j++){
                dp[j][i] = (j+1 > i-1 || dp[j+1][i-1]) && s.charAt(j) == s.charAt(i);
                if (dp[j][i] && res.length() < i - j + 1){
                    res = s.substring(j, i+1);
                }
            }
        }
        return res;
    }
}
