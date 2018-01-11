Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++){
            char[] sc = strs[i].toCharArray();
            Arrays.sort(sc);
            String s = String.valueOf(sc);
            if (!map.containsKey(s)){
                map.put(s, new ArrayList<>());
            }
            map.get(s).add(strs[i]);
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }
}
