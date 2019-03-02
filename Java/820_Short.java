Given a list of words, we may encode it by writing a reference string S and a list of indexes A.

For example, if the list of words is ["time", "me", "bell"], we can write it as S = "time#bell#" and indexes = [0, 2, 5].

Then for each index, we will recover the word by reading from the reference string from that index until we reach a "#" character.

What is the length of the shortest reference string S possible that encodes the given words?

Example:

Input: words = ["time", "me", "bell"]
Output: 10
Explanation: S = "time#bell#" and indexes = [0, 2, 5].
Note:

1 <= words.length <= 2000.
1 <= words[i].length <= 7.
Each word has only lowercase letters.

Method 1: Trie Best solution
class Solution {
    class TrieNode {
        TrieNode[] children;
        int childrenNum;
        public TrieNode(){
            children = new TrieNode[26];
            childrenNum = 0; //use childrenNum to directly find number of children
        }
    }
    public int minimumLengthEncoding(String[] words) {
        Map<TrieNode, Integer> map = new HashMap<>(); // use map to directly find the leaves
        //build Trie
        TrieNode root = new TrieNode();
        for (int j = 0; j < words.length; j++){
            TrieNode cur = root;
            for (int i = words[j].length() - 1; i >= 0; i--){
                char c = words[j].charAt(i);
                if (cur.children[c - 'a'] == null){
                    cur.children[c - 'a'] = new TrieNode();
                    cur.childrenNum++;
                }
                cur = cur.children[c - 'a'];
            }
            map.put(cur, j); 
        }
        int res = 0;
        for (TrieNode node : map.keySet()){
            if (node.childrenNum == 0){
                res += words[map.get(node)].length() + 1; //plus 1 for additional #
            }
        }
        return res;
    }
}

Method 2: 
class Solution {
    public int minimumLengthEncoding(String[] words) {
        Set<String> good = new HashSet<>(Arrays.asList(words));
        for (String word : words){
            for (int i = 1; i < word.length(); i++){
                good.remove(word.substring(i));
            }
        }
        int res = 0;
        for (String word : good){
            res += word.length() + 1;
        }
        return res;
    }
}
