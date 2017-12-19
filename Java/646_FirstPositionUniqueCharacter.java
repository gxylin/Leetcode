Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Have you met this question in a real interview? Yes
Example
Given s = "lintcode", return 0.

Given s = "lovelintcode", return 2.

Method: use char as hash array

public class Solution {
    /*
     * @param s: a string
     * @return: it's index
     */
    public int firstUniqChar(String s) {
        final int MAX_CHAR = 256;
        char[] count = new char[MAX_CHAR];
        for (int i = 0 ; i < s.length(); i++){
            count[s.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++){
            if (count[s.charAt(i)] == 1){
                return i;
            }
        }
        return -1;
    }
}
