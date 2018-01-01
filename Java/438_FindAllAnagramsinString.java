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
https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007?page=1

