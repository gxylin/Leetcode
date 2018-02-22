This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list. 

class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int word1Ind = -1;
        int word2Ind = -1;
        int ans = Integer.MAX_VALUE;
        boolean equal = false;
        if (word1.equals(word2)){
            equal = true;
        }
        for (int i = 0; i < words.length; i++){
            if (words[i].equals(word1)){
                if (equal && word1Ind != -1){
                    ans = Math.min(ans, i - word1Ind);
                }
                word1Ind = i;
            }else if (words[i].equals(word2)){
                if (equal && word2Ind != -1){
                    ans = Math.min(ans, i - word2Ind);
                }
                word2Ind = i;
            }
            if (word1Ind != -1 && word2Ind != -1){
                ans = Math.min(ans, Math.abs(word1Ind - word2Ind));
            }
        }
        return ans;
    }
}
