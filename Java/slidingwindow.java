https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007

https://github.com/optimisea/Leetcode/edit/master/Java/159_Longest.java
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null){
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int max = 0;
        int count = 0;
        int k = 2;
        while (end < s.length()){
            char charEnd = s.charAt(end);
            map.put(charEnd, map.getOrDefault(charEnd, 0) + 1);
            if (map.get(charEnd) == 1){
                count++;
            }
            end++;
            while (count > k){
                char charStart = s.charAt(start);
                if (map.get(charStart) == 1){
                    count--;
                }
                map.put(charStart, map.get(charStart) - 1);
                start++;
            }
            max = Math.max(max, end - start); // if the question is to ask about longest or max, put check condition here.
        }
        return max;
    }
}


https://github.com/optimisea/Leetcode/blob/master/Java/76_MinimumWindowSubstring.java
Minimum Window Substring (not like Minimum Window Subsequence which cares about the order of characters)
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int count = map.size();
        int start = 0;
        int end = 0;
        int head = 0;
        int minLen = Integer.MAX_VALUE;
        while (end < s.length()){
            char cEnd = s.charAt(end);
            if (map.containsKey(cEnd)){
                map.put(cEnd, map.get(cEnd) - 1);
                if (map.get(cEnd) == 0){
                    count--;
                }
            }
            end++;
            while (count == 0){
                if (end - start < minLen){ //if the question is to ask about the min, put check condition here
                    minLen = end - start;
                    head = start;
                }
                char cStart = s.charAt(start);
                if (map.containsKey(cStart)){
                    if  (map.get(cStart) == 0){
                        count++;
                    }
                    map.put(cStart, map.get(cStart) + 1);
                }
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(head, head + minLen);
    }
}
