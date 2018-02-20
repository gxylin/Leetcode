Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]


https://leetcode.com/problems/palindrome-pairs/discuss/79210/The-Easy-to-unserstand-JAVA-Solution
There are several cases to be considered that isPalindrome(s1 + s2):

Case1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.

Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.

Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] , then s2+s1 is palindrome.

Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut] , 
then s1+s2 is palindrome.

To make the search faster, build a HashMap to store the String-idx pairs.




class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length == 0){
            return result;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++){
            map.put(words[i], i);
        }
        if (map.containsKey("")){
            int blankInd = map.get("");
            for (int i = 0; i < words.length; i++){
                if (i == blankInd){
                    continue;
                }
                if (isPalindrome(words[i])){
                    result.add(Arrays.asList(blankInd, i));
                    result.add(Arrays.asList(i, blankInd));
                }
            }
        }
        for(int i = 0; i < words.length; i++){
            if (map.containsKey(reverse(words[i]))){
                int found = map.get(reverse(words[i]));
                if (found == i){
                    continue;
                }
                result.add(Arrays.asList(i, found));
            }
        }
        for (int i = 0; i < words.length; i++){
            for (int j = 1; j < words[i].length(); j++){
                String sub1 = words[i].substring(0, j);
                String sub2 = words[i].substring(j);
                if (isPalindrome(sub1)){
                    String reverseSub2 = reverse(sub2);
                    if (map.containsKey(reverseSub2)){
                        result.add(Arrays.asList(map.get(reverseSub2), i));
                    }
                }
                if (isPalindrome(sub2)){
                    String reverseSub1 = reverse(sub1);
                    if (map.containsKey(reverseSub1)){
                        result.add(Arrays.asList(i, map.get(reverseSub1)));
                    }
                }
            }
        }
        return result;
    }
    private String reverse(String str){
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }
    private boolean isPalindrome(String str){
        int start = 0;
        int end = str.length() - 1;
        while (start < end){
            if (str.charAt(start) != str.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
