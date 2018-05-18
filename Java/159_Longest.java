Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.

Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.

Sliding window

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
        while (end < s.length()){
            char charEnd = s.charAt(end);
            map.put(charEnd, map.getOrDefault(charEnd, 0) + 1);
            if (map.get(charEnd) == 1){
                count++;
            }
            while (count > 2){
                if (max < end - start){
                    max = end - start;
                }
                char charStart = s.charAt(start);
                if (map.get(charStart) == 1){
                    count--;
                }
                map.put(charStart, map.get(charStart) - 1);
                start++;
            }
            end++;
        }
        return Math.max(max, end - start);
    }
}

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
        while (end < s.length()){
            char charEnd = s.charAt(end);
            map.put(charEnd, map.getOrDefault(charEnd, 0) + 1);
            if (map.get(charEnd) == 1){
                count++;
            }
            end++;
            while (count > 2){
                char charStart = s.charAt(start);
                if (map.get(charStart) == 1){
                    count--;
                }
                map.put(charStart, map.get(charStart) - 1);
                start++;
            }
            max = Math.max(max, end - start);
        }
        return max;
    }
}
