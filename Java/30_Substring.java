You are given a string, s, and a list of words, words, that are all of the same length.
Find all starting indices of substring(s) in s that is a concatenation of each word in 
words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).

Time complexity: O(N * M)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words == null || words.length == 0){
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        int len = words[0].length();
        int n = words.length;
        int start = 0;
        int last = s.length() - n * len;
        while (start <= last){
            Map<String, Integer> copy = new HashMap<>(map);
            for (int i = 0; i < words.length; i++){
                String sub = s.substring(start + i * len, start + i * len + len);
                if (copy.containsKey(sub)){
                    copy.put(sub, copy.get(sub) - 1);
                    if (copy.get(sub) == 0){
                        copy.remove(sub);
                    }
                }else{
                    break;
                }
                if (copy.isEmpty()){
                    res.add(start);
                }
            }
            start++;
        }
        return res;
    }
}
