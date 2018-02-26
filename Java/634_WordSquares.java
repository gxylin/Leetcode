Given a set of words without duplicates, find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, 
where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads 
the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
 Notice

There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Have you met this question in a real interview? Yes
Example
Given a set ["area","lead","wall","lady","ball"]
return [["wall","area","lead","lady"],["ball","area","lead","lady"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

Given a set ["abat","baba","atan","atal"]
return [["baba","abat","baba","atan"],["baba","abat","baba","atal"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

思路:
• 裸写DFS很简单:
– 枚举每一行选哪个单词
– 枚举完后check是否满足条件 – 但是时间上过不了最坏1000^5
• 所以怎办? – DFS+剪枝
• DFS中,什么是剪枝?
– 剪枝就是去掉搜索过程中的冗余
• 什么是冗余?
– 就是搜到某个情况下,明显往后继续搜是搜不出结果的
• 这一题有哪些冗余?

• 冗余一:
– 第一个词填了ball后,第二个词必须以a开头 – 第二个词填了area后,第三个词必须以le开头 – 以其他开头的就没必要搜下去了
– 怎么实现?
• 用Hash or Trie树记录下以某个前缀开头的有哪些单词
• 比如以l开头的有lead lady,以le开头的有lead,以lea开头的有lead • 每次只用从特定开头的单词中继续往后搜
• 冗余二:
– 第一个词填了ball
– 第二个词想填area的话
– 字典中必须有以le la开头的单词,否则没有的话就不能填area
– 怎么实现?
• 直接利用冗余一中的Hash or Trie树


public class Solution {
    /*
     * @param words: a set of words without duplicates
     * @return: all word squares
     */
    Map<String, List<String>> map = new HashMap<>();
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<>();
        if (words == null || words.length == 0){
            return result;
        }
        List<String> square = new ArrayList<>();
        int len = words[0].length();
        initPrefix(words);
        dfs(result, square, words, len, 0);
        return result;
    }
    private void dfs(List<List<String>> result, List<String> square,
                        String[] words, int len, int start){
        if (start == len){
            result.add(new ArrayList<String>(square));
            return;
        }
        String pre = "";
        for (int i = 0; i < start; i++){
            pre += square.get(i).charAt(start);
        }
        List<String> candidate = map.get(pre);
        
        for (String can : candidate){
            if (!check(can, start, square)){
                continue;
            }
            square.add(can);
            dfs(result, square, words, len, start + 1);
            square.remove(square.size() - 1);
        }                    
    }
    private void initPrefix(String[] words){
        for (String str : words){
            if (!map.containsKey("")){
                map.put("", new ArrayList<String>());
            }
            map.get("").add(str);
            String pre = "";
            for (int i = 0; i < str.length(); i++){
                pre += str.charAt(i);
                if (!map.containsKey(pre)){
                    map.put(pre, new ArrayList<String>());
                }
                map.get(pre).add(str);
            }
        }
    }
    private boolean check(String str, int start, List<String> square){
        for (int i = start + 1; i < str.length(); i++){
            String pre = "";
            for (int j = 0; j < start; j++){
                pre += square.get(j).charAt(i);
            }
            pre += str.charAt(i);
            if (!map.containsKey(pre)){
                return false;
            }
        }
        return true;
    }
}
