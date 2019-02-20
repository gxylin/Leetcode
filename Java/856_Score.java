Given a balanced parentheses string S, compute the score of the string based on the following rule:

() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.
 

Example 1:

Input: "()"
Output: 1
Example 2:

Input: "(())"
Output: 2
Example 3:

Input: "()()"
Output: 2
Example 4:

Input: "(()(()))"
Output: 6
 

Note:

S is a balanced parentheses string, containing only ( and ).
2 <= S.length <= 50

Method 1: Better
class Solution {
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            if (c == '('){
                stack.push(-1);
            }else{
                int cur = 0; //store the sum between ( and ), if there is no value, push 1 otherwise push value * 2
                while (stack.peek() != -1){
                    cur += stack.pop();
                }
                stack.pop();
                stack.push(cur == 0 ? 1 : cur * 2);
            }
        }
        int ans = 0;
        while (!stack.isEmpty()){
            ans += stack.pop();
        }
        return ans;
    }
}

Method 2:
class Solution {
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        int cur = 0;//store 
        for (int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            if (c == '('){
                stack.push(cur);
                cur = 0;
            }else{
                cur = stack.pop();
                if (S.charAt(i-1) == '('){
                    cur += 1;
                }else{
                    cur += res * 2;
                }
                res = cur;
            }
        }
        return res;
    }
}

Method 3: 
Our goal is to maintain the score at the current depth we are on. When we see an opening bracket, we increase our depth, 
and our score at the new depth is 0. When we see a closing bracket,
we add twice the score of the previous deeper part - except when counting (), which has a score of 1.
class Solution {
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (char c : S.toCharArray()){
            if (c == '('){
                stack.push(0);
            }else{
                int deeperLevel = stack.pop();
                int currLevel = stack.pop();
                stack.push(currLevel + Math.max(deeperLevel * 2, 1));
            }
        }
        return stack.pop();
    }
}



class Solution {
    public int scoreOfParentheses(String S) {
        int res[] = new int[30], i = 0;
        for (char c : S.toCharArray())
            if (c == '(') 
                res[++i] = 0;
            else 
                res[i - 1] += Math.max(res[i--] * 2, 1);
        return res[0];
        
    }
}

Best solution:
O(1)
class Solution {
    public int scoreOfParentheses(String S) {
        int res = 0;
        int layer = 0;
        for (int i = 0; i < S.length(); i++){
            if (S.charAt(i) == '('){
                layer++;
            }else{
                layer--;
            }
            if (S.charAt(i) == '(' && S.charAt(i+1) == ')'){
                res += 1 << (layer - 1);
            }
        }
        return res;
    }
}
