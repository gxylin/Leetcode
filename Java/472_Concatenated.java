Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.


Method 1: DP solution 284ms
use the code from word break I
Time complexity: O(m^2 * nlogn)
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Arrays.sort(words, new Comparator<String>(){
           public int compare (String s1, String s2){
               return s1.length() - s2.length();
           } 
        });
        for (String word : words){
            if (word.length() != 0 && canBreak(word, set)){
                res.add(word);
            }
            set.add(word);
        }
        return res;
    }
    private boolean canBreak(String word, Set<String> set){
        int len = word.length();
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
        for (int i = 1; i <= len; i++){
            for (int j = 0; j < i; j++){
                if (dp[j] && set.contains(word.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}

Time complexity: O(m^2 * n)
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (String word : words){
            if (word.length() != 0){
                set.add(word);
            }
        }
        for (String word : words){
            if (word.length() != 0 && canBreak(word, set)){
                res.add(word);
            }
        }
        return res;
    }
    private boolean canBreak(String word, Set<String> set){
        int len = word.length();
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
        for (int i = 1; i <= len; i++){
            for (int j = 0; j < i; j++){
                if (dp[j] && set.contains(word.substring(j, i)) && i - j != len){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}


Method 2: DFS + Trie 54ms
https://leetcode.com/problems/concatenated-words/discuss/95644/102ms-java-Trie-+-DFS-solution.-With-explanation-easy-to-understand.

class Solution {
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        //build trie
        TrieNode root = new TrieNode();
        for (String word : words){
            if (word.length() == 0){
                continue;
            }
            TrieNode node = root;
            for (char c : word.toCharArray()){
                if (node.children[c - 'a'] == null){
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isEnd = true;
        }
        //check
        for (String word : words){
            if (word.length() == 0){
                continue;
            }
            if (isValid(root, word, 0, 0)){
                res.add(word);
            }
        }
        return res;
    }
    private boolean isValid(TrieNode root, String word, int start, int count){
        TrieNode node = root;
        for (int i = start; i < word.length(); i++){
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null){
                return false;
            }
            if (node.children[c - 'a'].isEnd){
                if (i == word.length() - 1){
                    return count >= 1;
                }
                if (isValid(root, word, i+1, count+1)){
                    return true;
                }
            }
            node = node.children[c - 'a'];
        }
        return false;
    }
}
