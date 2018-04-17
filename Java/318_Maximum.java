Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words 
do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.

Method 1:
Time complexity: O(n^2 * m)
Space complexity: O(n)
class Solution {
    public int maxProduct(String[] words) {
        int ans = 0;
        for (int i = 0; i < words.length; i++){
            Set<Character> set = new HashSet<>();
            for (int k = 0; k < words[i].length(); k++){
                set.add(words[i].charAt(k));
            }
            for (int j = i + 1; j < words.length; j++){
                boolean isValid = true;
                for (int m = 0; m < words[j].length(); m++){
                    if (set.contains(words[j].charAt(m))){
                        isValid = false;
                        break;
                    }
                }
                if (isValid){
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }
}

Method 2: use bit manipulation to hash and compare the two words do not share common letters
Time complexity: O(n^2)
Space complexity: O(n)
class Solution {
    public int maxProduct(String[] words) {
        int ans = 0;
        int len = words.length;
        int[] hash = new int[len];
        for (int i = 0; i < len; i++){
            for (int j = 0; j < words[i].length(); j++){
                hash[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        for (int i = 0; i < len; i++){
            for (int j = i + 1; j < len; j++){
                if ((hash[i] & hash[j]) == 0){
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }
}
