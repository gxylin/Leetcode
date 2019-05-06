This problem is an interactive problem new to the LeetCode platform.

We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.

Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.

Example 1:
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

Explanation:

master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Note:  Any solutions that attempt to circumvent the judge will result in disqualification.

Refer to Google Doc https://docs.google.com/document/d/1qxA2wps0IhVRWULulQ55W4SGPMu2AE5MkBB37h8Dr58/edit
当一个单词和其他单词match number为0的次数越多，那么这个单词越不好，因为match number为0时我们减少搜索空间的速度最慢。

假如现在有无限多长度为6的单词，对于word X，和他match number为0的单词有25^6这么多个，然而和X match number为1的单词则减少到了25^5 * 6这么多个，
为2时为 C(6, 2) * 25^4，以此类推，match number越大我们下一轮的搜索空间会越小，所以这里我们每一轮都挑选出当前搜索空间中和其他单词match number
为0的次数最少的单词作为guess word来猜，这样minimize了每次猜词的worse case


/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
class Solution {
    class Pair {
        String str;
        int freq;
        public Pair (String str, int freq){
            this.str = str;
            this.freq = freq;
        }
    }
    public void findSecretWord(String[] wordlist, Master master) {
        List<String> list = new ArrayList<>();
        for (String word : wordlist){
            list.add(word);
        }
        for (int i = 0; i < 10; i++){
            Map<String, Integer> zeroMatch = new HashMap<>();
            for (String s1 : list){
                zeroMatch.putIfAbsent(s1, 0);
                for (String s2 : list){
                    if (match(s1, s2) == 0){
                        zeroMatch.put(s1, zeroMatch.get(s1) + 1);
                    }
                }
            }
            Pair p = new Pair("", 1000);
            for (String key : zeroMatch.keySet()){
                if (p.freq > zeroMatch.get(key)){
                    p = new Pair(key, p.freq);
                }
            }
            int matchNum = master.guess(p.str);
            if (matchNum == 6){
                return;
            }
            List<String> temp = new ArrayList<>();
            for (String str : list){
                if (match(str, p.str) == matchNum){
                    temp.add(str);
                }
            }
            list = temp;
        }
    }
    private int match(String s1, String s2){
        int count = 0;
        for (int i = 0; i < s1.length(); i++){
            if (s1.charAt(i) == s2.charAt(i)){
                count++;
            }
        }
        return count;
    }
}
