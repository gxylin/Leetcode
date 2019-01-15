
We are given two strings, A and B.

A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.

Example 1:
Input: A = 'abcde', B = 'cdeab'
Output: true

Example 2:
Input: A = 'abcde', B = 'abced'
Output: false
Note:

A and B will have length at most 100.

Method 1:
Time complexity: O(AB)
class Solution {
    public boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A+A).contains(B);
    }
}

class Solution {
    public boolean rotateString(String A, String B) {
        if (A.length() == 0 && B.length() == 0){
            return true;
        }
        if (A.length() != B.length()){
            return false;
        }
        String newA = A + A;
        int len = B.length();
        for (int i = 0; i < newA.length() - len; i++){
            String sub = newA.substring(i, i+len);
            if (sub.equals(B)){
                return true;
            }
        }
        return false;
    }
}

Method 2:
Time complexity: O(AB)
class Solution {
    public boolean rotateString(String A, String B) {
        if (A.length() == 0 && B.length() == 0){
            return true;
        }
        if (A.length() != B.length()){
            return false;
        }
        int idx = 0;
        while (idx < A.length()){
            while (idx < A.length() && A.charAt(idx) != B.charAt(0)){
                idx++;
            }
            String rotated = A.substring(idx) + A.substring(0, idx);
            if (rotated.equals(B)){
                return true;
            }
            idx++;
        }
        return false;
    }
}
