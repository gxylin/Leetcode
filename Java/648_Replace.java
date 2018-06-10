In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

Example 1:
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
Note:
The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000

Implement Trie


    Time Complexity: O(N) where N is the length of the sentence. Every query of a word is in linear time.

    Space Complexity: O(N), the size of our trie.

class Solution {
    class TrieNode{
        TrieNode[] children;
        String word;
        public TrieNode() {
            children = new TrieNode[26];
        }
    }
    public String replaceWords(List<String> dict, String sentence) {
        TrieNode trie = new TrieNode();
        for (int i = 0; i < dict.size(); i++){
            TrieNode cur = trie;
            for (char c : dict.get(i).toCharArray()){
                if (cur.children[c - 'a'] == null){
                    cur.children[c -'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.word = dict.get(i);
        }
        String[] strArray = sentence.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String str : strArray){
            TrieNode cur = trie;
            for (char c : str.toCharArray()){
                if (cur.children[c - 'a'] == null || cur.word != null){
                    break;
                }
                cur= cur.children[c - 'a'];
            }
            sb.append(cur.word != null ? cur.word : str);
            sb.append(" ");
        }
       // sb.deleteCharAt(sb.length() - 1); //return sb
       // sb.setLength(sb.length() - 1); //return void
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
