Given a string, find the length of the longest substring without repeating characters.

Have you met this question in a real interview? 
Example
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.

For "bbbbb" the longest substring is "b", with the length of 1.

public class Solution {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        int ans = Integer.MIN_VALUE;
        int count = 0;
        while (j < s.length()){
            char c = s.charAt(j);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) == 2){
                count++;
            }
            j++;
            while (count > 0){
                char cstart = s.charAt(i);
                map.put(cstart, map.get(cstart) - 1);
                if (map.get(cstart) == 1){
                    count--;
                }
                i++;
            }
            ans = Math.max(ans, j - i);
        }
        return ans;
    }
}
