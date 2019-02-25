Given a string s and a dictionary of words dict, determine if s can be break into a space-separated sequence of 
one or more dictionary words.

Have you met this question in a real interview? Yes
Example
Given s = "lintcode", dict = ["lint", "code"].

Return true because "lintcode" can be break as "lint code".

Tags 
String Dynamic Programming


public class Solution {
    /*
     * @param s: A string
     * @param dict: A dictionary of words dict
     * @return: A boolean
     */
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0){
            return true;
        }
        int len = s.length();
        boolean[] canBreak = new boolean[len + 1];
        canBreak[0] = true;
        for (int i = 1; i <= len; i++){
            for (int lastWord = 1; lastWord <= i && lastWord <= getMaxLen(dict); lastWord++){
                if (!canBreak[i-lastWord]){
                    continue;
                }
                String str = s.substring(i-lastWord, i);
                if (dict.contains(str)){
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return canBreak[len];
    }
    private int getMaxLen(Set<String> dict){
        int max = Integer.MIN_VALUE;
        for (String s : dict){
            max = Math.max(max, s.length());
        }
        return max;
    }
}


Method 2:
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++){
            for (int j = 0; j < i; j++){
                if (dp[j] && wordDict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        for (String word : wordDict){
            set.add(word);
            min = Math.min(min, word.length());
        }
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for (int i = min; i <= n; i++){
            for (int j = i - min; j >= 0; j--){
                if (set.contains(s.substring(j,i))){
                    dp[i] = dp[i] || dp[j] ;
                }
            }
        }
        return dp[n];
    }
}

Best solution
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        for (String word : wordDict){
            set.add(word);
            min = Math.min(min, word.length());
        }
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for (int i = min; i <= n; i++){
            for (int j = i - min; j >= 0; j--){
                if (set.contains(s.substring(j,i)) && dp[j]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
         Set<String> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        for (String word : wordDict){
            set.add(word);
            min = Math.min(min, word.length());
        }
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for (int i = min; i <= n; i++){
            for (int j = 0; j <= i - min; j++){
                if (set.contains(s.substring(j,i)) && dp[j]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
