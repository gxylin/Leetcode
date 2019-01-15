Given many words, words[i] has weight i.

Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.

Examples:
Input:
WordFilter(["apple"])
WordFilter.f("a", "e") // returns 0
WordFilter.f("b", "") // returns -1
Note:
words has length in range [1, 15000].
For each test case, up to words.length queries WordFilter.f may be made.
words[i] has length in range [1, 10].
prefix, suffix have lengths in range [0, 10].
words[i] and prefix, suffix queries consist of lowercase letters only.

Method 1: Trie of Suffix Wrapped Words
Consider the word 'apple'. For each suffix of the word, we could insert that suffix, followed by '#', followed by the word,
all into the trie.

For example, we will insert '#apple', 'e#apple', 'le#apple', 'ple#apple', 'pple#apple', 'apple#apple' into the trie.
Then for a query like prefix = "ap", suffix = "le", we can find it by querying our trie for le#ap.

Time Complexity: O(NK^2 + QK) where N is the number of words, K is the maximum length of a word, and Q is the number of queries.

Space Complexity: O(NK^2), the size of the trie.


class WordFilter {
    class TrieNode {
        TrieNode[] children;
        int weight;
        public TrieNode (){
            children = new TrieNode[27];//initialize 27 to include character '{'' which is next to 'z'
            weight = 0;
        }
    }
    TrieNode root;
    public WordFilter(String[] words) {
        root = new TrieNode();
        for (int w = 0; w < words.length; w++){           
            String word = words[w] + "{";
            int len = word.length();
            for (int i = 0; i < len; i++){
                TrieNode node = root;
                for (int j = i; j < 2 * len - 1; j++){
                    char c = word.charAt(j % len);
                    if (node.children[c - 'a'] == null){
                        node.children[c - 'a'] = new TrieNode();
                    }
                    node = node.children[c - 'a'];
                    node.weight = w;
                }
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        TrieNode node = root;
        String target = suffix + "{" + prefix;
        for (char c : target.toCharArray()){
            if (node.children[c - 'a'] == null){
                return -1;
            }
            node = node.children[c - 'a'];
        }
        return node.weight;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
 
 

Method 2: HashMap and others
https://leetcode.com/problems/prefix-and-suffix-search/discuss/110044/Three-ways-to-solve-this-problem-in-Java

TLE
class WordFilter {
    String[] input;
    public WordFilter(String[] words) {
        input = words;
    }
    
    public int f(String prefix, String suffix) {
        for (int i = input.length - 1; i >= 0; i--){
            String word = input[i];
            if (word.startsWith(prefix) && word.endsWith(suffix)){
                return i;
            }
        }
        return -1;
    }
}


WordFilter: Time = O(NL)
f: Time = O(N)
Space = O(NL)
 
class WordFilter {
    Map<String, List<Integer>> prefixMap;
    Map<String, List<Integer>> suffixMap;
    public WordFilter(String[] words) {
        prefixMap = new HashMap<>();
        suffixMap = new HashMap<>();
        for (int w = 0; w < words.length; w++){
            String word = words[w];
            for (int i = 0; i <= word.length(); i++){
                String str = word.substring(0, i);
                if (!prefixMap.containsKey(str)){
                    prefixMap.put(str, new ArrayList<Integer>());
                }
                prefixMap.get(str).add(w);
            }
            for (int i = 0; i <= word.length(); i++){
                String str = word.substring(i);
                if (!suffixMap.containsKey(str)){
                    suffixMap.put(str, new ArrayList<Integer>());
                }
                suffixMap.get(str).add(w);
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        if (!prefixMap.containsKey(prefix) || !suffixMap.containsKey(suffix)){
            return -1;
        }
        List<Integer> pList = prefixMap.get(prefix);
        List<Integer> sList = suffixMap.get(suffix);
        int p = pList.size() - 1;
        int s = sList.size() - 1;
        while (p >= 0 && s >= 0){
            if (pList.get(p) > sList.get(s)){
                p--;
            }else if (pList.get(p) < sList.get(s)){
                s--;
            }else{
                return pList.get(p);
            }
        }
        return -1;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
