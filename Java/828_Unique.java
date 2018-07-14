A character is unique in string S if it occurs exactly once in it.

For example, in string S = "LETTER", the only unique characters are "L" and "R".

Let's define UNIQ(S) as the number of unique characters in string S.

For example, UNIQ("LETTER") =  2.

Given a string S with only uppercases, calculate the sum of UNIQ(substring) over all non-empty substrings of S.

If there are two or more equal substrings at different positions in S, we consider them different.

Since the answer can be very large, return the answer modulo 10 ^ 9 + 7.

 

Example 1:

Input: "ABC"
Output: 10
Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
Evey substring is composed with only unique letters.
Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
Example 2:

Input: "ABA"
Output: 8
Explanation: The same as example 1, except uni("ABA") = 1.
 

Note: 0 <= S.length <= 10000.

Method 1: Brute Force, TLE
Time complexity: O(N^3)
Space complexity: O(N)
class Solution {
    public int uniqueLetterString(String S) {
        int count = 0;
        char[] arr = S.toCharArray();
        for (int i = 0; i < arr.length; i++){
            for (int j = i; j < arr.length; j++){
                count += unique(arr, i, j) % (Math.pow(10, 9) + 7);
            }
        }
        return count;
    }
    private int unique(char[] arr, int start, int end){
        Map<Character, Integer> map = new HashMap<>();
        for (int i = start; i <= end; i++){
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        int count = 0;
        for (char key : map.keySet()){
            if (map.get(key) == 1){
                count++;
            }
        }
        return count;
    }
}

Method 2: 
Time complexity: O(n)
Space complexity: O(n)
https://leetcode.com/problems/unique-letter-string/discuss/128952/One-pass-O(N)-Straight-Forward

class Solution {
    public int uniqueLetterString(String S) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++){
            if (!map.containsKey(S.charAt(i))){
                map.put(S.charAt(i), new ArrayList<Integer>());
            }
            List<Integer> list = map.get(S.charAt(i));
            list.add(i);
        }
        long count = 0;
        for (char c : map.keySet()){
            List<Integer> list = map.get(c);
            for (int i = 0; i < list.size() ; i++){
                long prev = i > 0 ? list.get(i-1) : -1;
                long next = i < list.size() - 1 ? list.get(i+1) : S.length();
                count += (list.get(i) - prev) * (next - list.get(i)) % (Math.pow(10, 9) + 7);
            }
        }
        return (int) (count % (Math.pow(10, 9) + 7));
    }
}
