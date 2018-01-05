Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".

Method 1:
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public String reverseString(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        char[] sc = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        while (start < end){
            char temp = sc[start];
            sc[start] = sc[end];
            sc[end] = temp;
            start++;
            end--;
        }
        return String.valueOf(sc);
    }
}

Method 2:
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}

Method 3:
public class Solution {
    public String reverseString(String s) {
        int length = s.length();
        if (length <= 1) return s;
        String leftStr = s.substring(0, length / 2);
        String rightStr = s.substring(length / 2, length);
        return reverseString(rightStr) + reverseString(leftStr);
    }
}
Time complexity:
O(n log(n)) (Average Case) and O(n * log(n)) (Worst Case) where n is the total number character in the
input string.
The recurrence equation is T(n) = 2 * T(n/2) + O(n). O(n) is due to the fact that concatenation function
takes linear time.
The recurrence equation can be solved to get O(n * log(n)).
Space complexity:
O(h) space is used where h is the depth of recursion tree generated which is log(n). Space is needed for
activation stack during recursion calls.
