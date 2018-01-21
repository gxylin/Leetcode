Given an array of strings, group anagrams together.

 Notice
All inputs will be in lower-case.

Have you met this question in a real interview? Yes
Example
Given strs = ["eat", "tea", "tan", "ate", "nat", "bat"],
Return 
[
    ["ate", "eat","tea"],
    ["nat","tan"],
    ["bat"]
]

public class Solution {
    /**
     * @param strs: the given array of strings
     * @return: The anagrams which have been divided into groups
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0){
            return result;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++){
            char[] sc = strs[i].toCharArray();
            Arrays.sort(sc);
            String s = String.valueOf(sc);
            if (!map.containsKey(s)){
                map.put(s, new ArrayList<String>());
            }
            map.get(s).add(strs[i]);
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }
}
