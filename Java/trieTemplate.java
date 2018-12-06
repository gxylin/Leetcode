class Trie {
        class TrieNode {
            TrieNode[] children;
            boolean isEnd;
            public TrieNode (){
                children = new TrieNode[26];
                isEnd = false;
            }
        }
        private TrieNode root;
        public Trie (){
            root = new TrieNode();
        }
        public void insert(String word){
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++){
                if (node.children[word.charAt(i) - 'a'] == null){
                    node.children[word.charAt(i) - 'a'] = new TrieNode();
                }
                node = node.children[word.charAt(i) - 'a'];
            }
            node.isEnd = true;
        }
        public boolean startWith(String prefix){
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }
        public boolean search(String word){
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd;
        }
        public TrieNode searchPrefix(String prefix){
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); i++){
                if (node.children[prefix.charAt(i) - 'a'] == null){
                    return null;
                }
                node = node.children[prefix.charAt(i) - 'a'];
            }
            return node;
        }
    }
    
    Refer to Leetcode 212 word search II for Trie + backtracking


Better version:
class Trie {
        class TrieNode {
            TrieNode[] children;
            boolean isEnd;
            public TrieNode () {
                children = new TrieNode[26];
                isEnd = false;
            }
        }
        TrieNode root;
        public Trie () {
            root = new TrieNode();
        }
        public void insert(String word){
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
        public boolean startWith(String word){
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null){
                    return false;
                }
                node = node.children[c - 'a'];
            }
            return true;
        }
        public boolean search(String word){
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null){
                    return false;
                }
                node = node.children[c - 'a'];
            }
            return node.isEnd;
        }
    }
