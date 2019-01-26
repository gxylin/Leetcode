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

Method 1: O(N^2)
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
        char badchar = '\0';
        for (Character c : map.keySet()){
            if (map.get(c) < k){
                badchar = c;
                break;
            }
        }
        if (badchar == '\0'){
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


Method 2: sliding window, based on Leetcode 340 template
Time complexity: O(26*N)
class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0){
           return 0;
        }
        int max = 0;
        for (int numTargetDistinct = 1; numTargetDistinct <= 26; numTargetDistinct++){
            int len = longestDistinct(s, k, numTargetDistinct);
            max = Math.max(max, len);
        }
        return max;
    }
    private int longestDistinct(String s, int k, int numTargetDistinct){
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int uniqueNum = 0;
        int noLessThanKNum = 0;
        int max = 0;
        while (end < s.length()){
            char cEnd = s.charAt(end);
            map.put(cEnd, map.getOrDefault(cEnd, 0) + 1);
            if (map.get(cEnd) == 1){
                uniqueNum++;
            }
            if (map.get(cEnd) == k){
                noLessThanKNum++;
            }
            end++;
            while (uniqueNum > numTargetDistinct){
                char cStart = s.charAt(start);
                if (map.get(cStart) == k){
                    noLessThanKNum--;
                }
                if (map.get(cStart) == 1){
                    uniqueNum--;
                }
                map.put(cStart, map.get(cStart) - 1);
                start++;
            }
            if (uniqueNum == noLessThanKNum && uniqueNum == numTargetDistinct){
                max = Math.max(max, end - start);
            }
        }
        return max;
    }
}

Best solution:
class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0){
           return 0;
        }
        int max = 0;
        for (int numTargetDistinct = 1; numTargetDistinct <= 26; numTargetDistinct++){
            int len = longestDistinct(s, k, numTargetDistinct);
            max = Math.max(max, len);
        }
        return max;
    }
    private int longestDistinct(String s, int k, int numTargetDistinct){
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int uniqueNum = 0;
        int noLessThanKNum = 0;
        int max = 0;
        while (end < s.length()){
            char cEnd = s.charAt(end);
            map.put(cEnd, map.getOrDefault(cEnd, 0) + 1);
            if (map.get(cEnd) == 1){
                uniqueNum++;
            }
            if (map.get(cEnd) == k){
                noLessThanKNum++;
            }
            end++;
            while (uniqueNum > numTargetDistinct){
                char cStart = s.charAt(start);
                if (map.get(cStart) == k){
                    noLessThanKNum--;
                }
                if (map.get(cStart) == 1){
                    uniqueNum--;
                }
                map.put(cStart, map.get(cStart) - 1);
                start++;
            }
            if (noLessThanKNum == numTargetDistinct){//make sure every char is no less than k
                max = Math.max(max, end - start);
            }
        }
        return max;
    }
}
