Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces 
in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary
does not contain duplicate words.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

DP = recursion + memorization
Method 1:  DP, the same as Methodd 2
class Solution {
    Map<String, List<String>> map = new HashMap<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0){
            return result;
        }
        if (map.containsKey(s)){
            return map.get(s);
        }
        if (wordDict.contains(s)){//must have, like initialization
            result.add(s);
        }
        for (int i = 1; i < s.length(); i++){
            String sub = s.substring(i);
            if (wordDict.contains(sub)){
                List<String> temp = wordBreak(s.substring(0, i), wordDict);
                if (temp.size() != 0){
                    for (String str : temp){//if size == 0, won't go into loop
                        result.add(str +" " + sub);
                    }
                }
            }
        }
        map.put(s, result);
        return result;
    }
}

Method 2: DP: best solution
note the difference with Leetcode 131 Palindrome Partition
https://github.com/optimisea/Leetcode/blob/master/Java/131_PalindromePartitioning.java

In this case: the return result could be empty due to no valid break.

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (String str : wordDict){
            set.add(str);
        }
        return dfs(s, set, map);
    }
    private List<String> dfs(String s, Set<String> set, Map<String, List<String>> map){
        if (map.containsKey(s)){
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        if (set.contains(s)){
            res.add(s);
        }
        for (int i = 1; i <= s.length(); i++){
            String sub = s.substring(0, i);
            if (set.contains(sub)){
                List<String> temp = dfs(s.substring(i), set, map);
                if (temp.size() != 0){
                    for (String str : temp){
                        res.add(sub + " " + str);
                    }
                }
                
            }
        }
        map.put(s, res);
        return res;
        
    }
}


Method 3: Backtracking TLE
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (String st : wordDict){
            set.add(st);
            min = Math.min(min, st.length());
            max = Math.max(max, st.length());
        }
        backtrack(res, new ArrayList<String>(), s, set, min, max, 0);
        return res;
    }
    private void backtrack(List<String> res, List<String> item, String s, Set<String> set, int min, int max, int pos){
        if (pos == s.length()){
            String str = String.join(" ", item);
            res.add(str);
            return;
        }
     //   System.out.println(pos);
        for (int i = pos; i <= s.length(); i++){
            if (i - pos < min){
                continue;
            }
            if (i - pos > max){
                break;
            }
            String sub = s.substring(pos, i);
      //      System.out.println(sub);
            if (set.contains(sub)){
                item.add(sub);
                backtrack(res, item, s, set, min, max, i);
                item.remove(item.size() - 1);
            }
        }
    }
    
}

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        int min = 0;
        int max = 0;
        for (String str : wordDict){
            set.add(str);
            min = Math.min(min, str.length());
            max = Math.max(max, str.length());
        }
        dfs(res, s, "", set, 0, min, max);
        return res;
    }
    private void dfs(List<String> res, String s, String item, Set<String> set, int start, int min, int max){
        if (start == s.length()){
            res.add(item.trim());
            return;
        }
        for (int i = start; i < s.length(); i++){
            String sub = s.substring(start, i+1);
            if (sub.length() < min){
                continue;
            }
            if (sub.length() > max){
                break;
            }
            if (set.contains(sub)){
                dfs(res, s, item + " " + sub, set, i+1, min, max);
            }
        }
    }
}

Best solution:
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String str : wordDict){
            set.add(str);
        }
        Map<String, List<String>> map = new HashMap<>();
        return dfs(s, set, map);
    }
    private List<String> dfs(String s, Set<String> set, Map<String, List<String>> map){
        if (map.containsKey(s)){
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        if (set.contains(s)){
            res.add(s);
        }
        for (int i = 1; i < s.length(); i++){
            String sub = s.substring(0, i);
            if (set.contains(sub)){
                List<String> list = dfs(s.substring(i), set, map);
                for (String str : list){
                    res.add(sub + " " + str);
                }
            }
        }
        map.put(s, res);
        return res;
    }
}
