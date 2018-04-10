Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.


Method 1: Recursion Time complexity: O(2^n)
class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)){
            return true;
        }
        int[] hash = new int[26];
        for (int i = 0; i < s1.length(); i++){
            hash[s1.charAt(i) - 'a']++;
            hash[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++){
            if (hash[i] != 0){
                return false;
            }
        }
        for (int i = 1; i < s1.length(); i++){
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) //non-swap case
               && isScramble(s1.substring(i), s2.substring(i))){
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) // swap case
                && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))){
                return true;
            }
        }
        return false;
    }
}

Method 2: dp time complexity: O(n^4)
https://leetcode.com/problems/scramble-string/discuss/29396/Simple-iterative-DP-Java-solution-with-explanation

