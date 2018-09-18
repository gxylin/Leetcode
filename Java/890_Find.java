You have a list of words and a pattern, and you want to know which words in words matches the pattern.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

(Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)

Return a list of the words in words that match the given pattern. 

You may return the answer in any order.

 

Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
since a and b map to the same letter.
 

Note:

1 <= words.length <= 50
1 <= pattern.length = words[i].length <= 20

Method 1: Two maps
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        for (String word : words){
            if (isMatch(word, pattern)){
                res.add(word);
            }
        }
        return res;
    }
    private boolean isMatch(String word, String pattern){
        if (word.length() != pattern.length()){
            return false;
        }
        char[] wordArr = word.toCharArray();
        char[] patternArr = pattern.toCharArray();
        Map<Character, Character> forward = new HashMap<>();
        for (int i = 0; i < wordArr.length; i++){
            if (!forward.containsKey(wordArr[i])){
                forward.put(wordArr[i], patternArr[i]);
            }else{
                if (forward.get(wordArr[i]) != patternArr[i]){
                    return false;
                }
            }
        }
        Map<Character, Character> backward = new HashMap<>();
        for (int i = 0; i < wordArr.length; i++){
            if (!backward.containsKey(patternArr[i])){
                backward.put(patternArr[i], wordArr[i]);
            }else{
                if (backward.get(patternArr[i]) != wordArr[i]){
                    return false;
                }
            }
        }
        return true;
    }
}

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        for (String word : words){
            if (isMatch(word, pattern)){
                res.add(word);
            }
        }
        return res;
    }
    private boolean isMatch(String word, String pattern){
        if (word.length() != pattern.length()){
            return false;
        }
        char[] wordArr = word.toCharArray();
        char[] patternArr = pattern.toCharArray();
        Map<Character, Character> forward = new HashMap<>();
        Map<Character, Character> backward = new HashMap<>();
        for (int i = 0; i < wordArr.length; i++){
            if (!forward.containsKey(wordArr[i])){
                forward.put(wordArr[i], patternArr[i]);
            }
            if (!backward.containsKey(patternArr[i])){
                backward.put(patternArr[i], wordArr[i]);
            }
            if (forward.get(wordArr[i]) != patternArr[i] || backward.get(patternArr[i]) != wordArr[i]){
                    return false;
            }
        }
        return true;
    }
}

One map
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        for (String word : words){
            if (isMatch(word, pattern)){
                res.add(word);
            }
        }
        return res;
    }
    private boolean isMatch(String word, String pattern){
        if (word.length() != pattern.length()){
            return false;
        }
        char[] wordArr = word.toCharArray();
        char[] patternArr = pattern.toCharArray();
        Map<Character, Character> forward = new HashMap<>();
        for (int i = 0; i < wordArr.length; i++){
            if (!forward.containsKey(wordArr[i])){
                forward.put(wordArr[i], patternArr[i]);
            }else{
                if (forward.get(wordArr[i]) != patternArr[i]){
                    return false;
                }
            }
        }
        forward.clear();
        for (int i = 0; i < wordArr.length; i++){
            if (!forward.containsKey(patternArr[i])){
                forward.put(patternArr[i], wordArr[i]);
            }else{
                if (forward.get(patternArr[i]) != wordArr[i]){
                    return false;
                }
            }
        }
        return true;
    }
}
