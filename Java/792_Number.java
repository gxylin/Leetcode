Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input: 
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
Note:

All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].

Method 1: Brute Force (TLE)
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        for (int i = 0; i < words.length; i++){
            if (isSubsequence(S, words[i])){
                ans++;
            }
        }
        return ans;
    }
    private boolean isSubsequence(String S, String T){
        int indexS = 0;
        int indexT = 0;
        while (indexS < S.length()){
            if (S.charAt(indexS) == T.charAt(indexT)){
                indexT++;
                if (indexT == T.length()){
                    return true;
                }
            }
            indexS++;
        }
        return false;
    }
}
