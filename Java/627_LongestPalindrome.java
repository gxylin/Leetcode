Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes 
that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

 Notice
Assume the length of given string will not exceed 1010.

Have you met this question in a real interview? Yes
Example
Given s = "abccccdd" return 7

One longest palindrome that can be built is "dccaccd", whose length is 7.

public class Solution {
    /*
     * @param s: a string which consists of lowercase or uppercase letters
     * @return: the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int[] hash = new int[256];
        int count = 0;
        boolean single = false;
        for (int i = 0; i < s.length(); i++){
            hash[s.charAt(i)]++;
        }
        for (int i = 0; i < 256; i++){
            if (hash[i] % 2 == 0){
                count += hash[i];
            }else{
                count += hash[i] - 1;
                single = true;
            }
        }
        if (single){
            return count + 1;
        }
        return count;
    }
}
