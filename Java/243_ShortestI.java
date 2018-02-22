Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list. 

class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int ans = Integer.MAX_VALUE;
        int word1Ind = -1;
        int word2Ind = -1;
        for (int i = 0; i < words.length; i++){
            if (words[i].equals(word1)){
                word1Ind = i;
            }else if (words[i].equals(word2)){
                word2Ind = i;
            }
            if (word1Ind != -1 && word2Ind != -1){
                ans = Math.min(ans, Math.abs(word1Ind - word2Ind));
            }
        }
        return ans;
    }
}
