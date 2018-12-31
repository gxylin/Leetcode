Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.

For a given query word, the spell checker handles two categories of spelling mistakes:

Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
In addition, the spell checker operates under the following precedence rules:

When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
When the query matches a word up to capitlization, you should return the first such match in the wordlist.
When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
If the query has no matches in the wordlist, you should return the empty string.
Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].

 

Example 1:

Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 

Note:

1 <= wordlist.length <= 5000
1 <= queries.length <= 5000
1 <= wordlist[i].length <= 7
1 <= queries[i].length <= 7
All strings in wordlist and queries consist only of english letters.

https://leetcode.com/problems/vowel-spellchecker/discuss/211189/JavaC++Python-Two-HashMap

class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        int n = queries.length;
        String[] res = new String[n];
        Set<String> set = new HashSet<>();
        Map<String, String> lowerMap = new HashMap<>();
        Map<String, String> devowelMap = new HashMap<>();
        for (String w : wordlist){
            set.add(w);
            String lower = w.toLowerCase();
            String devowel =  lower.replaceAll("[aeiou]", "#");
            if (!lowerMap.containsKey(lower)){
                lowerMap.put(lower, w);
            }
            if (!devowelMap.containsKey(devowel)){
                devowelMap.put(devowel, w);
            }
        }
        for (int i = 0; i < n; i++){
            if (set.contains(queries[i])){
                res[i] = queries[i];
                continue;
            }
            String lower = queries[i].toLowerCase();
            String devowel = lower.replaceAll("[aeiou]", "#");
            if (lowerMap.containsKey(lower)){
                res[i] = lowerMap.get(lower);
            }else if (devowelMap.containsKey(devowel)){
                res[i] = devowelMap.get(devowel);
            }else{
                res[i] = "";
            }
        }
        return res;
    }
}
