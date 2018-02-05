Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.


class Solution {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0){
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++){
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
        int count = map.size();
        int start = 0;
        int end = 0;
        int len = Integer.MAX_VALUE;
        int head = 0;
        while (end < s.length()){
            char c = s.charAt(end);
            if (map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0){
                    count--;
                }
            }
            end++;
            while (count == 0){
                if (end - start < len){
                    len = end - start;
                    head = start;
                }
                
                char cstart = s.charAt(start);
                if (map.containsKey(cstart)){
                    map.put(cstart, map.get(cstart) + 1);
                    if (map.get(cstart) > 0){
                        count++;
                    }
                }
                start++;
            }
        }
        if (len == Integer.MAX_VALUE){
            return "";
        }
        return s.substring(head, head + len);
        
    }
}
