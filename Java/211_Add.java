Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

https://www.jiuzhang.com/solution/add-and-search-word/


class WordDictionary {
    class TrieNode {
        TrieNode[] links;
        int R = 26;
        boolean isEnd;
        public TrieNode() {
            links = new TrieNode[R];
            isEnd = false;
        }
        public boolean containsKey(char c){
            return links[c - 'a'] != null;
        }
        public void put(char c, TrieNode node){
            links[c - 'a'] = node;
        }
        public TrieNode get(char c){
            return links[c - 'a'];
        }
        public void setEnd(){
            isEnd = true;
        }
        public boolean getEnd(){
            return isEnd;
        }
        public TrieNode[] getLinks(){
            return links;
        }
    }
    private TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (!node.containsKey(c)){
                node.put(c, new TrieNode());
            }
            node = node.get(c);
        }
        node.setEnd();
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, root, 0);
    }
    private boolean search(String word, TrieNode node, int start) {
        if (start == word.length()){
            return node.getEnd();
        }
        char c = word.charAt(start);
        if (c == '.'){
            for (TrieNode link : node.getLinks()){
                if (link != null && search(word, link, start+1)){
                    return true;
                }
            }
            return false;
        }else if (node.containsKey(c)){
            node = node.get(c);
            return search(word, node, start + 1);
        } else{
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
