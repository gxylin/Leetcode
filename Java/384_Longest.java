Given a string, find the length of the longest substring without repeating characters.

Have you met this question in a real interview? 
Example
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.

For "bbbbb" the longest substring is "b", with the length of 1.

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        int start = 0;
        int end = 0;
        int ans = 0;
        while (end < s.length()){
            char endCh = s.charAt(end);
            map.put(endCh, map.getOrDefault(endCh, 0) + 1);
            if (map.get(endCh) > 1){
                count++;
            }
            end++;
            while (count > 0){
                
                char startCh = s.charAt(start);
                if (map.get(startCh) > 1){
                    count--;
                }
                map.put(startCh, map.get(startCh) - 1);
                start++;
            }
            if (end - start > ans){
                ans = end - start;
            }
        }
        return ans;
    }
}
