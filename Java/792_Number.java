Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input: 
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
Note:

All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].

Method 1: Brute Force (TLE)
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        for (int i = 0; i < words.length; i++){
            if (isSubsequence(S, words[i])){
                ans++;
            }
        }
        return ans;
    }
    private boolean isSubsequence(String S, String T){
        int indexS = 0;
        int indexT = 0;
        while (indexS < S.length()){
            if (S.charAt(indexS) == T.charAt(indexT)){
                indexT++;
                if (indexT == T.length()){
                    return true;
                }
            }
            indexS++;
        }
        return false;
    }
}
Passed solution:
Time complexity: O(n * m * L) : n: length of words, m : S length, L: average word length
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int count = 0;
        for (String word : words){
            if (isSub(word, S)){
                count++;
            }
        }
        return count;
    }
    private boolean isSub(String T, String S){
        int prev = 0;
        int index = 0;
        while (index < T.length()){
            prev = S.indexOf(T.charAt(index), prev);
            if (prev < 0){
                return false;
            }
            prev++;
            index++;
        }
        return true;
    }
}


Method 3: Accepted (174ms)
Refer to explanation:https://leetcode.com/problems/number-of-matching-subsequences/discuss/117634/Efficient-and-simple-go-through-words-in-parallel-with-explanation
The same time complexity as above
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, LinkedList<String>> map = new HashMap<>();
        for (String word : words){
            char c = word.charAt(0);
            if (!map.containsKey(c)){
                map.put(c, new LinkedList<String>());
            }
            map.get(c).offer(word);
        }
        int ans = 0;
        for (int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            LinkedList<String> queue = map.get(c);
            if (queue == null){
                continue;
            }
            int size  = queue.size();
            for (int j = 0; j < size; j++){
                String str = queue.poll();
                if (str.length() == 1){
                    ans++;
                }else{
                    if (!map.containsKey(str.charAt(1))){
                        map.put(str.charAt(1), new LinkedList<String>());
                    }
                    map.get(str.charAt(1)).offer(str.substring(1));
                }
            }
        }
        return ans;
    }
}
