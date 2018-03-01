Given an expression s includes numbers, letters and brackets. Number represents the number of 
repetitions inside the brackets(can be a string or another expression)．Please expand expression to be a string.

Have you met this question in a real interview? 
Example
s = abc3[a] return abcaaa
s = 3[abc] return abcabcabc
s = 4[ac]dy, return acacacacdy
s = 3[2[ad]3[pf]]xyz, return adadpfpfpfadadpfpfpfadadpfpfpfxyz

Challenge 
Can you do it without recursion?


public class Solution {
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        Stack<Object> stack = new Stack<>();
        int num = 0;
        for (char c : s.toCharArray()){
            if (Character.isDigit(c)){
                num = num * 10 + c - '0';
            }else if (c == '['){
                stack.push(Integer.valueOf(num));
                num = 0;
            }else if (c == ']'){
                String str = popStack(stack);
                Integer count = (Integer) stack.pop();
                for (int i = 0; i < count; i++){
                    stack.push(str);
                }
            }else{
                stack.push(String.valueOf(c));
            }
        }
        return popStack(stack);
    }
    private String popStack(Stack<Object> stack){
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty() && (stack.peek() instanceof String)){
            sb.insert(0, (String) stack.pop());
        }
        return sb.toString();
    }
}
