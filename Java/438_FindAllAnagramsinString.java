Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

Method 1: 
Time complexity: O(s.length() * p.length())
class Solution {
    final static int  MAX_CHAR = 256;
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] map = new int[MAX_CHAR];
        int len = p.length();
        for (int i = 0; i < len; i++){
            map[p.charAt(i)]++;
        }
        
        for (int i = 0 ; i <= s.length() - len; i++){
            int[] temp = new int[MAX_CHAR];
            for (int j = i; j < i + len; j++){
                temp[s.charAt(j)]++;
            }
            if (Arrays.equals(map,temp)){
                result.add(i);
            }
        }
        return result;
    }
}

Method 2:
Time complexity: O(n) 
Slide window template
https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007?page=1
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); i++){
            map.put(p.charAt(i), map.getOrDefault(p.charAt(i), 0) + 1);
        }
        int start = 0;
        int end = 0;
        int count = map.size();
        while (end < s.length()){
            char endCh = s.charAt(end);
            if (map.containsKey(endCh)){
                map.put(endCh, map.get(endCh) - 1);
                if (map.get(endCh) == 0){
                    count--;
                }
            }
            end++;
            while (count == 0){
                if (end - start == p.length()){
                    result.add(start);
                }
                char startCh = s.charAt(start);
                if (map.containsKey(startCh)){
                    if (map.get(startCh) == 0){
                        count++;
                    }
                    map.put(startCh, map.get(startCh) + 1);
                }
                start++;
            }
           
        }
        return result;
    }
}

Method 3:
Time complexity: O(n)
class Solution {
    final static int  MAX_CHAR = 256;
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()){
            return result;
        }
        int[] map = new int[MAX_CHAR];
        int len = p.length();
        for (int i = 0; i < len; i++){
            map[p.charAt(i)]++;
        }
        int left = 0,  right = 0;
        int count = len;
        while(right < s.length()){
            if(map[s.charAt(right)] > 0){ 
                count--;
            }
            map[s.charAt(right)]--;
            right++;
            if(count == 0){
                result.add(left);
            }
            if(right - left == len){
                if (map[s.charAt(left)] >= 0){
                    count++;
                }
                map[s.charAt(left)]++;
                left++;                
            }
        }
        return result;
    }
}
