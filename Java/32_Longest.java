Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) 
parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"


Method 1: TLE
Time complexity: O(n^3) 
Space complexity: O(n)
class Solution {
    public int longestValidParentheses(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++){
            for (int j = i+2; j <= s.length(); j += 2){
                if (isValid(s.substring(i, j))){
                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(')');
            } else if (stack.empty() || stack.pop() != s.charAt(i)) {
                return false;
            }
        }
        return stack.empty();
    }
}

Method 2:
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int n = s.length();
        int max = 0;
        int[] dp = new int[n]; // denotes the longest valid parentheses ending at index i (but include index i)
        for (int i = 1; i < n; i++){
            if (s.charAt(i) == ')'){
                if (s.charAt(i-1) == '('){
                    dp[i] = (i - 2 >= 0 ? dp[i-2] : 0) + 2;
                }else if (i - dp[i-1] - 1 >= 0 && s.charAt(i-dp[i-1]-1) == '('){
                    dp[i] = (i-dp[i-1]-2 >= 0 ? dp[i-dp[i-1]-2]: 0) + dp[i-1] + 2;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

Method 3:
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int ans = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                stack.push(i);
            }else{
                stack.pop();
                if (stack.isEmpty()){
                    stack.push(i);
                }
                ans = Math.max(ans, i - stack.peek());
            }
        }
        return ans;
    }
}

Method 4: Best solution
Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, ans = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                left++;
            }else {
                right++;
            }
            if (left == right){
                ans = Math.max(ans, left + right);
            }else if (left < right){
                left = 0;
                right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--){
            if (s.charAt(i) == '('){
                left++;
            }else{
                right++;
            }
            if (left == right){
                ans = Math.max(ans, left + right);
            }else if (left > right){
                left = 0;
                right = 0;
            }
        }
        return ans;
    }
}
