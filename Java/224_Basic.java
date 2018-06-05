Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, 
non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23

https://leetcode.com/problems/basic-calculator/discuss/62361/Iterative-Java-solution-with-stack

class Solution {
    public int calculate(String s) {
        int ans = 0;
        int number = 0; //denote the current number
        int sign = 1; //denote the previous sign
        Stack<Integer> stack = new Stack<>(); // store the sign before "(" and the sum before "(" 
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                number = 10 * number + (int) (c - '0');
            }else if (c == '+'){
                ans += sign * number;
                number = 0;
                sign = 1;
            }else if (c == '-'){
                ans += sign * number;
                number = 0;
                sign = -1;
            }else if (c == '('){
                stack.push(ans);
                stack.push(sign);
                ans = 0;
                number = 0;
                sign = 1;
            }else if (c == ')'){
                ans += sign * number;
                number = 0;
                sign = 1;
                ans *= stack.pop();
                ans += stack.pop();
            }
        }
        ans += sign * number;
        return ans;
                      
    }
}
