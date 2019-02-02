Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets 
is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square
brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that 
digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".


Method 1:
class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        int len = s.length();
        int digit = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                digit = 10 * digit + (int) (c - '0');
            }else if (Character.isLetter(c)){
                sb.append(c);
            }else if (c == '['){
                numStack.push(digit);
                strStack.push(sb.toString());
                sb = new StringBuilder();
                digit = 0;
            }else if (c == ']'){
                int num = numStack.pop();
                String prev = strStack.pop();
                String str = sb.toString();      
                sb = new StringBuilder(prev);
                while (num > 0) {
                    sb.append(str);
                    num--;
                }
            }
        }
        return sb.toString();
    }
}

Can also use Stack<StringBuilder>
class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        int len = s.length();
        int digit = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                digit = 10 * digit + (int) (c - '0');
            }else if (Character.isLetter(c)){
                sb.append(c);
            }else if (c == '['){
                numStack.push(digit);
                strStack.push(sb);
                sb = new StringBuilder();
                digit = 0;
            }else if (c == ']'){
                int num = numStack.pop();
                StringBuilder prev = strStack.pop();
                StringBuilder str = sb;      
                sb = new StringBuilder(prev);
                while (num > 0) {
                    sb.append(str);
                    num--;
                }
            }
        }
        return sb.toString();
    }
}

class Solution {
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        int curr = 0;
        String str = "";
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                curr = curr * 10 + (int)(c - '0');
            }else if (c == '['){
                numStack.push(curr);
                strStack.push(str);
                curr = 0;
                str = "";
            }else if (Character.isLetter(c)){
                str += c;
            }else if (c == ']'){
                int num = numStack.pop();
                String prevStr = strStack.pop();
                StringBuilder sb = new StringBuilder();
                while (num > 0){
                    sb.append(str);
                    num--;
                }
                str = prevStr + sb.toString();
            }
        }
        return str;
    }
}
