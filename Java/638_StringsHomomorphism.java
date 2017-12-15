Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

 Notice

You may assume both s and t have the same length.

Have you met this question in a real interview? Yes
Example
Given s = "egg", t = "add", return true.

Given s = "foo", t = "bar", return false.

Given s = "paper", t = "title", return true.


Method 1: Array HashMap
public class Solution {
    /*
     * @param s: a string
     * @param t: a string
     * @return: true if the characters in s can be replaced to get t or false
     */
    public boolean isIsomorphic(String s, String t) {
        final int MAX_CHAR = 256;
        int[] map1 = new int[MAX_CHAR];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        for (int i = 0; i < s.length(); i++){
            if (map1[sc[i]] == 0){
                map1[sc[i]] = tc[i];
            }else{
                if (map1[sc[i]] != tc[i]){
                    return false;
                }
            }
        }
        int[] map2 = new int[MAX_CHAR];
        for (int i = 0; i < t.length(); i++){
            if (map2[tc[i]] == 0){
                map2[tc[i]] = sc[i];
            }else{
                if (map2[tc[i]] != sc[i]){
                    return false;
                }
            }
        }
        return true;
    }
}

Method 2: HashMap
public class Solution {
    /*
     * @param s: a string
     * @param t: a string
     * @return: true if the characters in s can be replaced to get t or false
     */
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            if (!map1.containsKey(s.charAt(i))){
                map1.put(s.charAt(i), t.charAt(i));
            }else{
                if (map1.get(s.charAt(i)) != t.charAt(i)){
                    return false;
                }
            }
        }
        for (int i = 0; i < t.length(); i++){
            if (!map2.containsKey(t.charAt(i))){
                map2.put(t.charAt(i), s.charAt(i));
            }else{
                if (map2.get(t.charAt(i)) != s.charAt(i)){
                    return false;
                }
            }
        }
        return true;
    }
}
