Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.

 

Example 1:

Input: "())"
Output: 1
Example 2:

Input: "((("
Output: 3
Example 3:

Input: "()"
Output: 0
Example 4:

Input: "()))(("
Output: 4
 

Note:

S.length <= 1000
S only consists of '(' and ')' characters.

class Solution {
    public int minAddToMakeValid(String S) {
        int left = 0;
        int right = 0;
        int res = 0;
        for (char c : S.toCharArray()){
            if (c == '('){
                if (right > left){
                    right = 0;
                    left = 0;
                }
                left++;
            }else{
                right++;
            }
            if (right > left){
                res++;
            }
        }
        return left > right ? res + (left - right) : res;
    }
}


Method 2:
class Solution {
    public int minAddToMakeValid(String S) {
        int diff = 0; //left - right;
        int res = 0;
        for (char c : S.toCharArray()){
            if (c == '('){
                diff++;
            }else if (diff == 0){ // ensure that diff is always equal or greater than 0
                res++;
            }else{
                diff--;
            }
        }
        return res + diff;
    }
}
