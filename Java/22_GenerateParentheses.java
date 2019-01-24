Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
https://leetcode.com/problems/generate-parentheses/solution/

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        char[] combination = new char[2*n];
        dfs(result, combination, 0);
        return result;
    }
    private void dfs(List<String> result, char[] combination, int start){
        if (start == combination.length){
            if (isValid(combination)){
                result.add(new String(combination));
            }
        }else{
            combination[start] = '(';
            dfs(result, combination, start+1);
            combination[start] = ')';
            dfs(result, combination, start+1);
        }
    }
    private boolean isValid(char[] combination){
        int balance = 0;
        for (int i = 0; i < combination.length; i++){
            if (combination[i] == '('){
                balance++;
            }else{
                balance--;
            }
            if (balance < 0){
                return false;
            }
        }
        return (balance == 0);
    }
    
}

Best solution:
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(res, "", n, n);
        return res;
    }
    private void helper(List<String> res, String str, int leftRemain, int rightRemain){
        if (leftRemain == 0 && rightRemain == 0){
            res.add(str);
            return;
        }
        if (leftRemain > 0){
            helper(res, str + "(", leftRemain - 1, rightRemain);
        }
        if (rightRemain > leftRemain){ //note that it is not rightRemain > 0 in order to eliminate invalid cases
            helper(res, str + ")", leftRemain, rightRemain - 1);
        }
    }
}
