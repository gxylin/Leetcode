Implement a MapSum class with insert, and sum methods.

For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.

For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.

Example 1:
Input: insert("apple", 3), Output: Null
Input: sum("ap"), Output: 3
Input: insert("app", 2), Output: Null
Input: sum("ap"), Output: 5

class MapSum {
    class TrieNode{
        TrieNode[] children;
        int[] nums;
        public TrieNode (){
            children = new TrieNode[26];
            nums = new int[26];
        }
    }
    /** Initialize your data structure here. */
    TrieNode trie;
    Map<String, Integer> map;
    public MapSum() {
        trie = new TrieNode();
        map = new HashMap<String, Integer>();
    }
    
    public void insert(String key, int val) {
        TrieNode cur = trie;
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        for (int i = 0; i < key.length(); i++){
            if (cur.children[key.charAt(i) - 'a'] == null){
                cur.children[key.charAt(i) - 'a'] = new TrieNode();
            }
            cur.nums[key.charAt(i) - 'a'] += delta;
            cur = cur.children[key.charAt(i) - 'a'];
        }
    }
    
    public int sum(String prefix) {
        TrieNode cur = trie;
        for (int i = 0; i < prefix.length() - 1; i++){
            if (cur.children[prefix.charAt(i) - 'a'] != null){
                cur = cur.children[prefix.charAt(i) - 'a'];
            }else{
                return 0;
            }
        }
        return cur.nums[prefix.charAt(prefix.length() - 1) - 'a'];
    }
}


Modified version: Better
class MapSum {
    class TrieNode{
        TrieNode[] children;
        int score;
        public TrieNode (){
            children = new TrieNode[26];
        }
    }
    /** Initialize your data structure here. */
    TrieNode trie;
    Map<String, Integer> map;
    public MapSum() {
        trie = new TrieNode();
        map = new HashMap<String, Integer>();
    }
    
    public void insert(String key, int val) {
        TrieNode cur = trie;
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        for (int i = 0; i < key.length(); i++){
            if (cur.children[key.charAt(i) - 'a'] == null){
                cur.children[key.charAt(i) - 'a'] = new TrieNode();
            }
            cur = cur.children[key.charAt(i) - 'a'];
            cur.score += delta;
        }
    }
    
    public int sum(String prefix) {
        TrieNode cur = trie;
        for (int i = 0; i < prefix.length() ; i++){
            if (cur.children[prefix.charAt(i) - 'a'] != null){
                cur = cur.children[prefix.charAt(i) - 'a'];
            }else{
                return 0;
            }
        }
        return cur.score;
    }
}


/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
 
 
 class MapSum {
    HashMap<String, Integer> map;
    TrieNode root;
    public MapSum() {
        map = new HashMap();
        root = new TrieNode();
    }
    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        TrieNode cur = root;
        cur.score += delta;
        for (char c: key.toCharArray()) {
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);
            cur.score += delta;
        }
    }
    public int sum(String prefix) {
        TrieNode cur = root;
        for (char c: prefix.toCharArray()) {
            cur = cur.children.get(c);
            if (cur == null) return 0;
        }
        return cur.score;
    }
}
class TrieNode {
    Map<Character, TrieNode> children = new HashMap();
    int score;
}
