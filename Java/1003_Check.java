We are given that the string "abc" is valid.

From any valid string V, we may split V into two pieces X and Y such that X + Y (X concatenated with Y) is equal to V.  
 (X or Y may be empty.)  Then, X + "abc" + Y is also valid.

If for example S = "abc", then examples of valid strings are: "abc", "aabcbc", "abcabc", "abcabcababcc".  
 Examples of invalid strings are: "abccba", "ab", "cababc", "bac".

Return true if and only if the given string S is valid.

 

Example 1:

Input: "aabcbc"
Output: true
Explanation: 
We start with the valid string "abc".
Then we can insert another "abc" between "a" and "bc", resulting in "a" + "abc" + "bc" which is "aabcbc".
Example 2:

Input: "abcabcababcc"
Output: true
Explanation: 
"abcabcabc" is valid after consecutive insertings of "abc".
Then we can insert "abc" before the last letter, resulting in "abcabcab" + "abc" + "c" which is "abcabcababcc".
Example 3:

Input: "abccba"
Output: false
Example 4:

Input: "cababc"
Output: false
 

Note:

1 <= S.length <= 20000
S[i] is 'a', 'b', or 'c'
 
 
Best solution: The same as 20. Valid Parentheses
https://leetcode.com/problems/valid-parentheses/

class Solution {
    public boolean isValid(String S) {
        Stack<Character> stack = new Stack<>();
        for (char c : S.toCharArray()){
            if (c == 'c'){
                if (stack.isEmpty() || stack.pop() != 'b'){
                    return false;
                }
                if (stack.isEmpty() || stack.pop() != 'a'){
                    return false;
                }
            }else{
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}

 Wrong solution below:
try aabbcc, should be false
 class Solution {
    public boolean isValid(String S) {
        int numA = 0;
        int numB = 0;
        int numC = 0;
        for (char c : S.toCharArray()){
            if (c == 'a'){
                numA++;
            }else if (c == 'b'){
                numB++;
            }else{
                numC++;
            }
            if (numA < numB || numA < numC || numB < numC){
                return false;
            }
        }
        return numA == numB && numB == numC;
    }
}
