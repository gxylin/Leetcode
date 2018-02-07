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
