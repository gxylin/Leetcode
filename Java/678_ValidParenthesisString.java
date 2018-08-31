https://leetcode.com/problems/valid-parenthesis-string/discuss/

Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].

Method 1: Best solution
https://leetcode.com/problems/valid-parenthesis-string/discuss/107577/Short-Java-O(n)-time-O(1)-space-one-pass
low keeps the minimum number of UNBALANCED open braces
high keeps the maximum number of UNBALANCED open braces
class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                low++;
                high++;
            }else if (s.charAt(i) == ')'){
                if (low > 0){
                   low--; 
                }
                high--;
            }else{
                if (low > 0){
                   low--; 
                }
                high++;
            }
            if (high < 0){
                return false;
            }
        }
        
        return low == 0;
    }
}

Method 2: best solution too and easiest logic
https://leetcode.com/problems/valid-parenthesis-string/discuss/139759/Java-Very-easy-solution.-No-recursion-dp.
class Solution {
    public boolean checkValidString(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(' || s.charAt(i) == '*'){
                count++;
            }else{
                count--;
            }
            if (count < 0){
                return false;
            }
        }
        //not necessary to have these three lines
//        if (count == 0){
 //           return true;
 //       }
        count = 0;
        for (int i = s.length() - 1; i >= 0; i--){
            if (s.charAt(i) == ')' || s.charAt(i) == '*'){
                count++;
            }else{
                count--;
            }
            if (count < 0){
                return false;
            }
        }
        return true;
    }
}

Method 3: Recursion, slowest solution
class Solution {
    public boolean checkValidString(String s) {
        return check(s, 0, 0);
    }
    private boolean check(String s, int start, int count){
        if (count < 0){
            return false;
        }
        while (start < s.length()){
            if (s.charAt(start) == '('){
                count++;
                start++;
            }else if (s.charAt(start) == ')'){
                count--;
                start++;
            }else{
                break;
            }
            if (count < 0){
                return false;
            }
        }
        if (start == s.length()){
            return count == 0;
        }
        if (check(s, start + 1, count - 1) || check(s, start + 1, count) || check(s, start + 1, count + 1)){
            return true;
        }
        return false;
    }
}

class Solution {
    public boolean checkValidString(String s) {
        return check(s, 0, 0);
    }
    private boolean check(String s, int start, int count){
        if (count < 0){
            return false;
        }
        for (int i = start; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '('){
                count++;
            }else if (c == ')'){
                count--;
            }else{
                if (check(s, i + 1, count - 1) || check(s, i + 1, count) || check(s, i + 1, count + 1)){
                    return true;
                }else{
                    return false;
                }
            }
            if (count < 0){
                return false;
            }
        }
        return count == 0;
    }
}

