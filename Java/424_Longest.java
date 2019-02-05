Given a string that consists of only uppercase English letters, you can replace any letter in the string 
with another letter at most k times. Find the length of a longest substring containing all repeating letters
you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.


class Solution {
    public int characterReplacement(String s, int k) {
        int start = 0;
        int end = 0;
        int ans = 0;
        int maxCount = 0;
        int[] hash = new int[26];
        while (end < s.length()){
            hash[s.charAt(end) - 'A']++;
            maxCount = Math.max(maxCount, hash[s.charAt(end) - 'A']);
            while (end - start + 1 - maxCount > k){
                hash[s.charAt(start) - 'A']--;
                maxCount = Math.max(maxCount, hash[s.charAt(start) - 'A']);
                start++;  
            }
            ans = Math.max(ans, end - start + 1);
            end++;
        }
        return ans;
    }
}


Better version:
class Solution {
    public int characterReplacement(String s, int k) {
        int res = 0;
        int start = 0;
        int end = 0;
        int maxCount = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (end < s.length()){
            char cEnd = s.charAt(end);
            map.put(cEnd, map.getOrDefault(cEnd, 0) + 1);
            maxCount = Math.max(maxCount, map.get(cEnd));
            end++;
            while (end - start - maxCount > k){
                char cStart = s.charAt(start);
                map.put(cStart, map.get(cStart) - 1);
                maxCount = Math.max(maxCount, map.get(cStart));
                start++;
            }
            res = Math.max(res, end - start);
        }
        return res;
    }
}
