Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
  
  
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> number = new Stack<>();
        int first, second;
        for (int i = 0; i < tokens.length; i++){
            switch (tokens[i]){
                case "+":
                    first = number.pop();
                    second = number.pop();
                    number.push(first + second);
                    break;
                case "-":
                    first = number.pop();
                    second = number.pop();
                    number.push(second - first);
                    break;
                case "*":
                    first = number.pop();
                    second = number.pop();
                    number.push(first * second);
                    break;
                case "/":
                    first = number.pop();
                    second = number.pop();
                    number.push(second / first);
                    break;
                default:
                    number.push(Integer.parseInt(tokens[i]));
            }
        }
        return number.pop();
    }
}

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String token : tokens){
            char c = token.charAt(token.length()-1);
            if (Character.isDigit(c)){
                stack.push(token);
            }else{
                int s2 = Integer.parseInt(stack.pop());
                int s1 = Integer.parseInt(stack.pop());
                if (c == '+'){
                    stack.push(String.valueOf(s1 + s2));
                }else if (c == '-'){
                    stack.push(String.valueOf(s1 - s2));
                }else if (c == '*'){
                    stack.push(String.valueOf(s1 * s2));
                }else{
                    stack.push(String.valueOf(s1 / s2));
                }
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
