Given an array of strings, return all groups of strings that are anagrams.

 Notice
All inputs will be in lower-case

Have you met this question in a real interview? Yes
Example
Given ["lint", "intl", "inlt", "code"], return ["lint", "inlt", "intl"].

Given ["ab", "ba", "cd", "dc", "e"], return ["ab", "ba", "cd", "dc"].

Challenge 
What is Anagram?
- Two strings are anagram if they can be the same after change the order of characters.

public class Solution {
    /*
     * @param strs: A list of strings
     * @return: A list of strings
     */
    public List<String> anagrams(String[] strs) {
        List<String> result = new ArrayList<>();
        if (strs == null || strs.length == 0){
            return result;
        }
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++){
            char[] charArray = strs[i].toCharArray();
            Arrays.sort(charArray);
            String s = String.valueOf(charArray);
            if (!map.containsKey(s)){
                map.put(s, new ArrayList<String>());
            }
            map.get(s).add(strs[i]);
        }
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()){
            if (entry.getValue().size() > 1){
                result.addAll(entry.getValue());
            }
        }
        return result;
    }
}
