Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5

https://leetcode.com/problems/basic-calculator-ii/discuss/63003/Share-my-java-solution

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        int number = 0; //denote the previous number
        char sign = '+'; //denote the previous sign
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                number = 10 * number + (int) (c - '0');
            }
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1){
                if (sign == '+'){
                    stack.push(number);
                }else if (sign == '-'){
                    stack.push(-number);
                }else if (sign == '*'){
                    stack.push(stack.pop() * number);
                }else if (sign == '/'){
                    stack.push(stack.pop() / number);
                }
                sign = c;
                number = 0;
            }
        }
        while (!stack.isEmpty()){
            ans += stack.pop();
        }
        return ans;
    }
}
