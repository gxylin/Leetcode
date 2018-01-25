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
