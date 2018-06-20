
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
Note:

S will be a string with length at most 12.
S will consist only of letters or digits.

    https://leetcode.com/problems/letter-case-permutation/discuss/115508/Java-solution-using-recursion
Method 1: recursion
class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        dfs(res, S.toCharArray(), 0);
        return res;
    }
    private void dfs(List<String> res, char[] chars, int pos){
        if (pos == chars.length){
            res.add(String.valueOf(chars));
            return;
        }

        if (Character.isLetter(chars[pos])){
            chars[pos] = Character.toLowerCase(chars[pos]);
            dfs(res, chars, pos+1);
            chars[pos] = Character.toUpperCase(chars[pos]);
        }
        dfs(res, chars, pos + 1);
    }
}
class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        dfs(res, S.toCharArray(), 0);
        return res;
    }
    private void dfs(List<String> res, char[] chars, int pos){
        if (pos == chars.length){
            res.add(String.valueOf(chars));
            return;
        }
        dfs(res, chars, pos + 1);
        if (Character.isLetter(chars[pos])){
            if (Character.isUpperCase(chars[pos])){
                chars[pos] = Character.toLowerCase(chars[pos]);
            }else{
                chars[pos] = Character.toUpperCase(chars[pos]);
            }
            dfs(res, chars, pos + 1);
        }
        
    }
}

Method 2: Backtracking
class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), S, 0);
        return res;
    }
    private void dfs(List<String> res, StringBuilder sb, String S, int pos){
        if (pos == S.length()){
            res.add(sb.toString());
            return;
        }
        char c = S.charAt(pos);
        if (Character.isLetter(c)){
            char lower = Character.toLowerCase(c);
            sb.append(lower);
            dfs(res, sb, S, pos+1);
            sb.deleteCharAt(sb.length() - 1);
            
            char upper = Character.toUpperCase(c);
            sb.append(upper);
            dfs(res, sb, S, pos+1);
            sb.deleteCharAt(sb.length() - 1);
        }else{
            sb.append(c);
            dfs(res, sb, S, pos+1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
