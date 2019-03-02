Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  Here, we have groups, of adjacent letters that are all the same character, and adjacent characters to the group are different.  A group is extended if that group is length 3 or more, so "e" and "o" would be extended in the first example, and "i" would be extended in the second example.  As another example, the groups of "abbcccaaaa" would be "a", "bb", "ccc", and "aaaa"; and "ccc" and "aaaa" are the extended groups of that string.

For some given string S, a query word is stretchy if it can be made to be equal to S by extending some groups.  Formally, we are allowed to repeatedly choose a group (as defined above) of characters c, and add some number of the same character c to it so that the length of the group is 3 or more.  Note that we cannot extend a group of size one like "h" to a group of size two like "hh" - all extensions must leave the group extended - ie., at least 3 characters long.

Given a list of query words, return the number of words that are stretchy. 

Example:
Input: 
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation: 
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not extended.
Notes:

0 <= len(S) <= 100.
0 <= len(words) <= 100.
0 <= len(words[i]) <= 100.
S and all words in words consist only of lowercase letters

class Solution {
    public int expressiveWords(String S, String[] words) {
        int ans = 0;
        for (String word : words){
            if (isExtended(S, word)){
                ans++;
            }
        }
        return ans;
    }
    private boolean isExtended(String S, String word){
        int i = 0;
        int j = 0;
        while (i < S.length() && j < word.length()){
            int iStart = i;
            i++;
            while (i < S.length() && S.charAt(i) == S.charAt(i-1)){
                i++;
            }
            int jStart = j;
            j++;
            while (j < word.length() && word.charAt(j) == word.charAt(j-1)){
                j++;
            }
            if (S.charAt(iStart) != word.charAt(jStart)){
                return false;
            }
            int iDiff = i - iStart;
            int jDiff = j - jStart;
            if (jDiff > iDiff){
                return false;
            }
            if (jDiff < iDiff && iDiff  < 3){
                return false;
            }
        }
        if (i < S.length() || j < word.length()){
            return false;
        }
        return true;
    }
}


class Solution {
    public int expressiveWords(String S, String[] words) {
        int ans = 0;
        for (String word : words){
            if (isExtended(S, word)){
                ans++;
            }
        }
        return ans;
    }
    private boolean isExtended(String S, String word){
        int i = 0;
        int j = 0;
        int iStart = 0;
        int jStart = 0;
        while (i < S.length() && j < word.length()){
            if (S.charAt(iStart) != word.charAt(jStart)){
                return false;
            }
            i = iStart + 1;
            while (i < S.length() && S.charAt(i) == S.charAt(i-1)){
                i++;
            }
            j = jStart + 1;
            while (j < word.length() && word.charAt(j) == word.charAt(j-1)){
                j++;
            }
            int iDiff = i - iStart;
            int jDiff = j - jStart;
            if (jDiff > iDiff){
                return false;
            }
            if (jDiff < iDiff && iDiff < 3){
                return false;
            }
            iStart = i;
            jStart = j;
        }
        if ((i < S.length()) ^ (j < word.length())){
            return false;
        }
        return true;
    }
}
