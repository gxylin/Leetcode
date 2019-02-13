Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

If there is no answer, return the empty string.
Example 1:
Input: 
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation: 
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:
Input: 
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: 
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
Note:

All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].

Methodd 1: Trie
Similar to Map Sum Trie and Longest Word in Dictionary through Deleting
Time complexity: O(n * log(26)) -> O(n)
Space complexity: O(n)
class Solution {
    class TrieNode{
        TrieNode[] children;
        String str;
        TrieNode (){
            children = new TrieNode[26];
        }
    }
    public String longestWord(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words){
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++){
                if (cur.children[word.charAt(i) - 'a'] == null){
                    cur.children[word.charAt(i) - 'a'] = new TrieNode();
                }
                cur = cur.children[word.charAt(i) - 'a'];
            }
            cur.str = word;
        }
        String ans = "";
        for (String word : words){
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++){ 
                if (cur.children[word.charAt(i) - 'a'] != null){
                    cur = cur.children[word.charAt(i) - 'a'];
                    if (cur.str == null){ 
                        break;
                    }
                }
            }
            if (cur.str != null && cur.str.length() >= ans.length()){;
                if (ans.equals("") || cur.str.length() > ans.length() || cur.str.compareTo(ans) < 0){
                    ans = cur.str;
                }     
            }
        }
        return ans;
    }
}

class Solution {
    class TrieNode{
        TrieNode[] children;
        boolean isEnd;
        public TrieNode(){
            children = new TrieNode[26];
            isEnd = false;
        }
    }
    public String longestWord(String[] words) {
        TrieNode root = new TrieNode();
        for(String word : words){
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null){
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c- 'a'];
            }
            node.isEnd = true;
        }
        String res = "";
        for (String word : words){
            TrieNode node = root;
            boolean found = true;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                node = node.children[c- 'a'];
                if (!node.isEnd){
                    found = false;
                    break;
                }
            }
            if (found){
                if (word.length() > res.length() || word.length() == res.length() && word.compareTo(res) < 0){
                    res = word;
                }
            }
        }
        return res;
    }
}

https://leetcode.com/problems/longest-word-in-dictionary/discuss/109114/JavaC++-Clean-Code
Best solution: build HashMap
Time complexity: O(nlogn)
Space complexity: O(n)
class Solution {
    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> built = new HashSet<String>();
        String res = "";
        for (String w : words) {
            if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
                res = w.length() > res.length() ? w : res;
                built.add(w);
            }
        }
        return res;
    }
}

Method 2: Brute Force
Intuition

For each word, check if all prefixes word[:k] are present. We can use a Set structure to check this quickly.

Algorithm

Whenever our found word would be superior, we check if all it's prefixes are present, then replace our answer.

Alternatively, we could have sorted the words beforehand, so that we know the word we are considering would be the answer if all it's prefixes are present.

Time complexity: O(n*k)
Space complexity: O(n)
class Solution {
    public String longestWord(String[] words) {
        String ans = "";
        Set<String> wordset = new HashSet();
        for (String word: words) wordset.add(word);
        for (String word: words) {
            if (word.length() > ans.length() ||
                    word.length() == ans.length() && word.compareTo(ans) < 0) {
                boolean good = true;
                for (int k = 1; k < word.length(); ++k) {
                    if (!wordset.contains(word.substring(0, k))) {
                        good = false;
                        break;
                    }
                }
                if (good) ans = word;
            }    
        }
        return ans;
    }
}


class Solution {
    public String longestWord(String[] words) {
        Set<String> wordset = new HashSet();
        for (String word: words) wordset.add(word);
        Arrays.sort(words, (a, b) -> a.length() == b.length()
                    ? a.compareTo(b) : b.length() - a.length());
        for (String word: words) {
            boolean good = true;
            for (int k = 1; k < word.length(); ++k) {
                if (!wordset.contains(word.substring(0, k))) {
                    good = false;
                    break;
                }
            }
            if (good) return word;
        }

        return "";
    }
}
