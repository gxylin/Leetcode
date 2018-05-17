Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. 
In other words, one of the first string's permutations is the substring of the second string.
Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False
Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].

Method: slidng window, the same as 438. Find All Anagrams in a String and 76. Minimum Window Substring
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0;  i< s1.length(); i++){
            char ch = s1.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int count = map.size();
        int start = 0;
        int end = 0;
        while (end < s2.length()){
            char charEnd = s2.charAt(end);
            if (map.containsKey(charEnd)){
                map.put(charEnd, map.get(charEnd) - 1);
                if (map.get(charEnd) == 0){
                    count--;
                }
            }
            end++;
            while (count == 0){
                if (end - start == s1.length()){
                    return true;
                }
                char charStart = s2.charAt(start);
                if (map.containsKey(charStart)){
                    if (map.get(charStart) == 0){
                        count++;
                    }
                    map.put(charStart, map.get(charStart) + 1);
                }
                start++;
            }
        }
        return false;
    }
}
