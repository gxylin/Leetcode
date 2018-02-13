 Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3. 

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0){
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int count = 0;
        int max = Integer.MIN_VALUE;
        while (end < s.length()){
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) == 1){
                count++;
            }
            end++;
            while (count > k){
                char cs = s.charAt(start);
                map.put(cs, map.get(cs) - 1);
                if (map.get(cs) == 0){
                    count--;
                }
                start++;
            }
            max = Math.max(max, end - start);
        }
        return max;
    }
}
