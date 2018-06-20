
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
