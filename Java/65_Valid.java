Validate if a given string can be interpreted as a decimal number.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
    However, here is a list of characters that can be in a valid decimal number:

Numbers 0-9
Exponent - "e"
Positive/negative sign - "+"/"-"
Decimal point - "."
Of course, the context of these characters also matters in the input.

class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        if (s.length() == 0){
            return false;
        }
        boolean hasPoint = false;
        boolean hasExp = false;
        boolean hasSign = false;
        boolean hasNum = false;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (Character.isLetter(c)){
                if (c != 'e' || hasExp || i == 0 || !hasNum){
                    return false;
                }
                hasExp = true;
                if ((!Character.isDigit(s.charAt(i-1)) && s.charAt(i-1) != '.') || i == s.length() - 1 || s.charAt(i+1) == '.' || s.charAt(i+1) == ' '){
                    return false;
                }
            }else if (c == '+' || c == '-'){
                if ((hasSign && !hasExp) || i == s.length() - 1 || (i != 0 && s.charAt(i-1) != 'e')){
                    return false;
                }
                hasSign = true;
            }else if (c == '.'){
                if (hasPoint || hasExp){
                    return false;
                }
                hasPoint = true;
            }else if (c == ' '){
                return false;
            }else {
                hasNum = true;
            }
        }
        return hasNum == true;
    }
}
