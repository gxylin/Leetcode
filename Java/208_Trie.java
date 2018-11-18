mplement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.

https://leetcode.com/problems/implement-trie-prefix-tree/solution/

class Trie {
    class TrieNode{
        private TrieNode[] links;
        private final int R = 26;
        private boolean isEnd;
        TrieNode(){
            links = new TrieNode[26];
            isEnd = false;
        }
        public boolean containsKey(char ch){
            return links[ch - 'a'] != null;
        }
        public TrieNode get(char ch){
            return links[ch - 'a'];
        }
        public void put(char ch, TrieNode node){
            links[ch - 'a'] = node;
        }
        public void setEnd(){
            isEnd = true;
        }
        public boolean getEnd(){
            return isEnd;
        }
    }
    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++){
            if (!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i), new TrieNode());
            }
            node = node.get(word.charAt(i));
        }
        node.setEnd();
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.getEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
    
    private TrieNode searchPrefix(String prefix){
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++){
            if (!node.containsKey(prefix.charAt(i))){
                return null;
            }
            node = node.get(prefix.charAt(i));
        }
        return node;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */


Better version:
class Trie {
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        public TrieNode (){
            children = new TrieNode[26];
            isEnd = false;
        }
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
       root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()){
            if (node.children[c - 'a'] == null){
               node.children[c- 'a'] = new TrieNode(); 
            }
            node = node.children[c - 'a']; 
        }
        node.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()){
            if (node.children[c - 'a'] == null){
                return false;
            }
            node = node.children[c - 'a'];
        }
        return node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()){
            if (node.children[c - 'a'] == null){
                return false;
            }
            node = node.children[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
