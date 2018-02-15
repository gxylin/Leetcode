Find the length of the longest substring T of a given string (consists of lowercase letters only) 
such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0){
            return 0;
        }
        if (k <= 0){
            return s.length();
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        char badchar = 0;
        for (Character c : map.keySet()){
            if (map.get(c) < k){
                badchar = c;
                break;
            }
        }
        if (badchar == 0){
            return s.length();
        }
        String[] strArray = s.split(String.valueOf(badchar));
        int max = 0;
        for (String sub : strArray){
            max = Math.max(max, longestSubstring(sub, k));
        }
        return max;
    }
}
