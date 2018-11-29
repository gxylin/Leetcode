A string S of lowercase letters is given. We want to partition this string into as many parts as 
possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.

Method: two points
Time complexity: O(n)

class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        if (S == null || S.length() == 0){
            return result;
        }
        Map<Character, Integer> map = new HashMap<>(); // Integer stores the last index
        for (int i = 0; i < S.length(); i++){
            map.put(S.charAt(i), i);
        }
        int start = 0;
        int end = 0;
        int cutIndex = 0;
        while (end < S.length()){
            char c = S.charAt(end);
            cutIndex = map.get(c);
            while (end < cutIndex){
                end++;
                if (end < S.length() && map.get(S.charAt(end)) > cutIndex){
                    cutIndex = map.get(S.charAt(end));
                }
            }
            result.add(cutIndex - start + 1);
            start = cutIndex + 1;
            end = start;
        }
        return result;
    }
}


Better version:
class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++){
            map.put(S.charAt(i), i);
        }
        int start = 0;
        int end = 0;
        int i = 0;
        while (i < S.length()){
            char c = S.charAt(i);
            end = map.get(c);
            while (i < end && map.get(S.charAt(i)) <= end){
                i++;
            }
            if (i == end){
                res.add(end - start + 1);
                start = end + 1;
                i = start;
            }
        }
        return res;
    }
}
