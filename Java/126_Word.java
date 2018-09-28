Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

TLE
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> seen = new HashSet<>();
        List<String> item = new ArrayList<String>();
        seen.add(beginWord);
        item.add(beginWord);
        dfs(res, item, wordSet, seen, beginWord, endWord);
        int min = Integer.MAX_VALUE;
        for(List<String> cand : res){
            int size = cand.size();
            if (size < min){
                min = size;
            }
        }
        List<List<String>> result = new ArrayList<>();
        for (List<String> cand : res){
            int size = cand.size();
            if (size == min){
                result.add(cand);
            }
        }
        return result;
    }
    private void dfs(List<List<String>> res, List<String> item, Set<String> wordSet, Set<String> seen, String begin, String end){
        if (begin.equals(end)){
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = 0; i < begin.length(); i++){
            char[] charArr = begin.toCharArray();
            char c = charArr[i];
            for (char ch = 'a'; ch <= 'z'; ch++){
                if (ch == c){
                    continue;
                }
                charArr[i] = ch;
                String next = new String(charArr);
                if (wordSet.contains(next) && !seen.contains(next)){
                    seen.add(next);
                    item.add(next);
                    dfs(res, item, wordSet, seen, next, end);
                    seen.remove(next);
                    item.remove(item.size() - 1);
                }
            }
        }
    }
}
