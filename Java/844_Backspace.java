Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?

Method 1:
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public boolean backspaceCompare(String S, String T) {
        String s = format(S);
        String t = format(T);
        return s.equals(t);
    }
    private String format(String S){
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < S.length()){
            if (S.charAt(i) != '#'){
                stack.push(S.charAt(i));
            }else{
                if (!stack.isEmpty()){
                    stack.pop();
                }
            }
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack){
            sb.append(c);
        }
        return sb.toString();
    }
}

Time complexity: O(n)
Space complexity: O(n)
    class Solution {
    public boolean backspaceCompare(String S, String T) {
        String a = format(S);
        String b = format(T);
        return a.equals(b);
    }
    private String format(String s){
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()){
            if (c != '#'){
                sb.append(c);
            }else{
                if (sb.length() > 0){
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        return sb.toString();
    }
}

Method 2:
Time complexixty: O(n)
Space complexity: O(1)
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length()- 1;
        int j = T.length() - 1;
        int skipS = 0;
        int skipT = 0;
        while (i >= 0 || j >= 0){
            while (i >= 0){
                if (S.charAt(i) == '#'){
                    skipS++;
                    i--;
                }else if (skipS > 0){
                    skipS--;
                    i--;
                }else{
                    break;
                }
            }
            while (j >= 0){
                if (T.charAt(j) == '#'){
                    skipT++;
                    j--;
                }else if (skipT > 0){
                    skipT--;
                    j--;
                }else{
                    break;
                }
                
            }
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)){
                return false;
            }
            if ((i >= 0) != (j >= 0)){
                return false;
            }
            i--;
            j--;
        }
        return true;
    }
}
