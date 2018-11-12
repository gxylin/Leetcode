Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.


class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0){
            return -1;
        }
        int MAX_CHAR = 26;
        int[] alphabet = new int[MAX_CHAR];
        for (int i = 0; i < s.length(); i++){
            alphabet[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++){
            if (alphabet[s.charAt(i) - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }
}

class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (map.get(c) == 1){
                return i;
            }
        }
        return -1;
    }
}
