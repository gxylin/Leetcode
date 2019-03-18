Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically 
neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

click to show hint.

You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. 
What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? 
If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0){
            return result;
        }
        Trie trie = new Trie();
        for (String word : words){
            trie.insert(word);
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                dfs(board, result, trie, "", i, j, visited);
            }
        }
        return result;
    }
    private void dfs(char[][] board, List<String> result, Trie trie, String str, int i, int j, boolean[][] visited){
        int m = board.length;
        int n = board[0].length;
        if (i >= m || i < 0 || j >= n || j < 0){
            return;
        }
        if (visited[i][j]){
            return;
        }
        str += board[i][j];
        if (!trie.startsWith(str)){
            return;
        }
        if (trie.search(str)){
            if (!result.contains(str)){
                result.add(str);
            }
        }
        visited[i][j] = true;
        int[] dx = {1, 0 , -1 , 0};
        int[] dy = {0, 1, 0, - 1};
        for (int k = 0; k < 4; k++){
            dfs(board, result, trie, str, i + dx[k], j + dy[k], visited);
        }
        visited[i][j] = false;
    }
    
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
}



Clean version:
class Solution {
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
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0){
            return new ArrayList<String>();
        }
        int m = board.length;
        int n = board[0].length;
        Set<String> res = new HashSet<>();
        Trie trie = new Trie();
        for (String word : words){
            trie.insert(word);
        }
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                dfs(board, trie, res, "", visited, i, j);
            }
        }
        return new ArrayList<>(res);
    }
    private void dfs(char[][] board, Trie trie, Set<String> res, String word, boolean[][] visited, int x, int y){
        word += board[x][y];
        if (!trie.startWith(word)){
            return;
        }
        if (trie.search(word)){
            res.add(word);
        }
        int m = board.length;
        int n = board[0].length;
        int[][] dirs = {{1,0}, {0, 1}, {-1, 0}, {0, -1}};
        visited[x][y] = true;
        for (int[] dir : dirs){
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]){
                dfs(board, trie, res, word, visited, nx, ny);
            }
        }
        visited[x][y] = false;
    }
}


Better version:
class Solution {
    class Trie {
        class TrieNode {
            TrieNode[] children;
            boolean isEnd;
            public TrieNode(){
                children = new TrieNode[26];
                isEnd = false;
            }
        }
        TrieNode root;
        public Trie (){
            root = new TrieNode();
        }
        public void insert (String word){
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
        public boolean search (String word){
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null){
                    return false;
                }
                node = node.children[c- 'a'];
            }
            return node.isEnd;
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
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        Trie trie = new Trie();
        for (String str : words){
            trie.insert(str);
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                visited[i][j] = true;
                backtrack(res, board, trie, visited, i, j, "" + board[i][j], dirs);
                visited[i][j] = false;
            }
        }
        return new ArrayList<String>(res);
    }
    private void backtrack(Set<String> res, char[][] board, Trie trie, boolean[][] visited, int x, int y, String item, int[][] dirs){
        if (!trie.startWith(item)){
            return;
        }
        if (trie.search(item)){
            res.add(item);
        }
        int m = board.length;
        int n = board[0].length;
        for (int[] dir : dirs){
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]){
                visited[nx][ny] = true;
                backtrack(res, board, trie, visited, nx, ny, item + board[nx][ny], dirs);
                visited[nx][ny] = false;
            }
        }
    }
}
