Implement the StreamChecker class as follows:

StreamChecker(words): Constructor, init the data structure with the given words.
query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.
 

Example:

StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist
 

Note:

1 <= words.length <= 2000
1 <= words[i].length <= 2000
Words will only consist of lowercase English letters.
Queries will only consist of lowercase English letters.
The number of queries is at most 40000


Method 1: brute force
class StreamChecker {
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        public TrieNode(){
            children = new TrieNode[26];
            isEnd = false;
        }
    }
    TrieNode root;
    Map<String, TrieNode> prefixMap;
    public StreamChecker(String[] words) {
        root = new TrieNode();
        prefixMap = new HashMap<>();
        prefixMap.put("", root);
        for (String word : words){
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null){
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isEnd = true;
        }
    }
    
    public boolean query(char letter) {
        boolean isExist = false;
        Map<String, TrieNode> temp = new HashMap<>();
        for (String key : prefixMap.keySet()){
            TrieNode node = prefixMap.get(key);
            if (node.children[letter - 'a'] != null){
                node = node.children[letter - 'a'];
                temp.put(key + letter, node);
                if (node.isEnd){
                    isExist = true;
                }
            }
        }
        if (root.children[letter -'a'] != null){
            temp.put(String.valueOf(letter), root.children[letter - 'a']);
        }
        temp.putIfAbsent("", root);
        prefixMap = temp;
        return isExist;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
 
 
 Method 2: reverse order
 Store the words in the trie with reverse order, and check the query string from the end
 
 class StreamChecker {
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        public TrieNode(){
            children = new TrieNode[26];
            isEnd = false;
        }
    }
    TrieNode root;
    StringBuilder sb;
    public StreamChecker(String[] words) {
        root = new TrieNode();
        sb = new StringBuilder();
        for (String word : words){
            TrieNode node = root;
            for (int i = word.length() - 1; i >= 0; i--){
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null){
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isEnd = true;
        }
    }
    
    public boolean query(char letter) {
        sb.append(letter);
        TrieNode node = root;
        for (int i = sb.length() - 1; i >= 0; i--){
            char c = sb.charAt(i);
            if (node.children[c - 'a'] == null){
                return false;
            }
            node = node.children[c - 'a'];
            if (node.isEnd){
                return true;
            }
        }
        return false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
 
