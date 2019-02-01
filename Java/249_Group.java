Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"

Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
A solution is:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]

Similar as group anagram

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings){
           //first find the key by baselining every char in str
           int offset = (int) (str.charAt(0) - 'a');
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++){
                char c = (char) (str.charAt(i) - offset);
                if (c < 'a'){
                    c += 26;
                }
                sb.append(c);
            }
            String key = sb.toString();
            if (!map.containsKey(key)){
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(str);
        }
        for (String s : map.keySet()){
            result.add(map.get(s));
        }
        return result;
    }
}
