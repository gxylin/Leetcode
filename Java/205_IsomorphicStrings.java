Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null){
            return false;
        }
        if (s.length() == 0 && t.length() == 0){
            return true;
        }
        if (s.length() == 0 || t.length() == 0){
            return false;
        }
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            if (!map1.containsKey(s.charAt(i))){
                map1.put(s.charAt(i), t.charAt(i));
            }else{
                if (t.charAt(i) != map1.get(s.charAt(i))){
                    return false;
                }
            }
        }
        for (int i = 0; i < t.length(); i++){
            if (!map2.containsKey(t.charAt(i))){
                map2.put(t.charAt(i), s.charAt(i));
            }else{
                if (s.charAt(i) != map2.get(t.charAt(i))){
                    return false;
                }
            }
        }
        return true;
    }
}
