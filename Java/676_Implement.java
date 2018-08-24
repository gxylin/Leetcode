Implement a magic directory with buildDict, and search methods.

For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.

Example 1:
Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False
Note:
You may assume that all the inputs are consist of lowercase letters a-z.
For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.


Method 1: HashMap (faster)
class MagicDictionary {
    Map<Integer, Set<String>> map;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        map = new HashMap<>();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict){
            int len = word.length();
            if (!map.containsKey(len)){
                map.put(len, new HashSet<>());
            }
            map.get(len).add(word);
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        int len = word.length();
        if (!map.containsKey(len)){
            return false;
        }
        Set<String> set = map.get(len);
        for (String s : set){
            int count = 0;
            for (int i = 0; i < s.length(); i++){
                if (s.charAt(i) != word.charAt(i)){
                    count++;
                }
            }
            if (count == 1){
                return true;
            }
        }
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */
 
 Method 2: Trie (like BFS, search the entire trie)
class MagicDictionary {
    class TrieNode {
        TrieNode[] children;
        boolean isWord;
        public TrieNode (){
            children = new TrieNode[26];
            isWord = false;
        }
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new TrieNode();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict){
            TrieNode node = root;
            for (int i = 0 ;i < word.length(); i++){
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null){
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isWord = true;
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            for (int j = 0; j < 26; j++){
                if ((char)(j + 'a') == c || node.children[j] == null){
                    continue;
                }
                if (isSame(node.children[j], word, i+1)){
                    return true;
                }
            }
            node = node.children[c - 'a'];
            if (node == null){
                return false;
            }
        }
        return false;
    }
    private boolean isSame(TrieNode node, String word, int start){
        for (int i = start ; i < word.length(); i++){
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null){
                return false;
            }
            node = node.children[c - 'a'];
        }
        return node.isWord;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */
 
