Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.


// k keeps track of how many characters we can remove
        // if the previous character in stk is larger than the current one
        // then removing it will get a smaller number
        // but we can only do so when k is larger than 0
        
class Solution {
    public String removeKdigits(String num, int k) {
        int digits = num.length() - k;
        char[] res = new char[num.length()]; // must define as num.length(), e.g. 123456, k = 1
        int top = 0;
        for (int i = 0; i < num.length(); i++){
            char c = num.charAt(i);
            while (top > 0 && k > 0 && res[top-1] > c){
                top--;
                k--;
            }
            res[top++] = c;
        }
        int idx = 0;
        while (idx < digits && res[idx] == '0'){
            idx++;
        }
        if (idx == digits){
            return "0";
        }
        return new String(res, idx, digits - idx);
    }
}


class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++){
            char c = num.charAt(i);
            while (!stack.isEmpty() && k > 0 && stack.peek() > c){
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        boolean isLeadZero = true;
        for (Character c : stack){
            if (c == '0' && isLeadZero){
                continue;
            }
            isLeadZero = false;
            if (sb.length() == stack.size() - k){
                break;
            }
            sb.append(c);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>(); //montonic increasing stack
        for (int i = 0; i < num.length(); i++){
            char c = num.charAt(i);
            while (!stack.isEmpty() && k > 0 && stack.peek() > c){
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        while (k > 0){//corner case for monotonic increasing e.g, 123456
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        sb.reverse();
        while (sb.length() > 1 && sb.charAt(0) == '0'){
            sb.deleteCharAt(0);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
