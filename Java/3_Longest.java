Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and 
not a substring.

Method: the basic idea is, keep a hashmap which stores the characters in string as keys and their positions as values, and 
keep two pointers which define the max substring. move the right pointer to scan through the string , and meanwhile 
update the hashmap. If the character is already in the hashmap, then move the left pointer to the right of the 
same character last found. Note that the two pointers can only move forward.

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null | s.length() == 0){
            return 0;
        }
        int count = 0;
        int max = Integer.MIN_VALUE;
        int start = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++){
            if (!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), i);
                count++;
            }else{
                start = Math.max(start, map.get(s.charAt(i)) + 1) ;;
                count = i - start + 1;
                map.put(s.charAt(i), i); 
            }
            max = Math.max(max, count);
        }
        return max;
    }
}

Method 2: Sliding window
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int ans = 0;
        int count = 0;
        while (end < s.length()){
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > 1){
                count++;
            }
            end++;
            while (count > 0){
                char cstart = s.charAt(start);
                if (map.get(cstart) > 1){
                    count--;
                }
                map.put(cstart, map.get(cstart) - 1);
                start++;
            }
            ans = Math.max(ans, end - start);
        }
        return ans;
    }
}

Best solution:
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int start = 0;
        int end = 0;
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        while (end < s.length()){
            char cEnd = s.charAt(end);
            map.put(cEnd, map.getOrDefault(cEnd, 0) + 1);
            if (map.get(cEnd) == 2){
                count++;
            }
            end++;
            while (count > 0){
                char cStart = s.charAt(start);
                if (map.get(cStart) == 2){
                    count--;
                }
                map.put(cStart, map.get(cStart) - 1);
                start++;
            }
            res = Math.max(res, end - start);
        }
        return res;
    }
}


Sliding window
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int start = 0;
        int end = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (end < s.length()){
            char cEnd= s.charAt(end);
            map.put(cEnd, map.getOrDefault(cEnd, 0) + 1);
            while (start < end && map.get(cEnd) > 1){
                res = Math.max(res, end - start);
                char cStart = s.charAt(start);
                map.put(cStart, map.get(cStart) - 1);
                start++;
            }
            end++;
        }
        res = Math.max(res, end - start);
        return res;
    }
}
