We have a string S of lowercase letters, and an integer array shifts.

Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a'). 

For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.

Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.

Return the final string after all such shifts to S are applied.

Example 1:

Input: S = "abc", shifts = [3,5,9]
Output: "rpl"
Explanation: 
We start with "abc".
After shifting the first 1 letters of S by 3, we have "dbc".
After shifting the first 2 letters of S by 5, we have "igc".
After shifting the first 3 letters of S by 9, we have "rpl", the answer.
Note:

1 <= S.length = shifts.length <= 20000
0 <= shifts[i] <= 10 ^ 9

Method 1: Brute Force
Time complexity: O(n^2)
Space complexity: O(1)

class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        StringBuilder res = new StringBuilder(S);
        for (int i = 0; i < shifts.length; i++){
            for (int j = 0; j <= i; j++){
                char c = (char)((res.charAt(j) - 'a' + shifts[i])%26 + 'a');
                res.setCharAt(j, c);
            }
        }
        return res.toString();
    }
}

Method 2: Prefix Sum Best solution
Time complexity: O(n)
Space complexity: O(1)

class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        StringBuilder res = new StringBuilder(S);
        for (int i = shifts.length - 2; i >= 0; i--){
            shifts[i] = (shifts[i+1] + shifts[i]) % 26;
        }
        for (int i = 0; i < shifts.length; i++){
            char c = (char)((S.charAt(i) - 'a' + shifts[i]) % 26 + 'a');
            res.setCharAt(i, c);
        }
        return res.toString();
    }
}


class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        int n = shifts.length;
        StringBuilder sb = new StringBuilder();
        int[] preSum = new int[n];
        int sum = 0;
        for (int i = n- 1; i >= 0; i--){
            sum = (sum  + shifts[i]) % 26;
            preSum[i] = sum;
        }
        for (int i = 0; i < n; i++){
            int delta = ((int)(S.charAt(i) - 'a') + preSum[i]) % 26;
            char c = (char)('a' + delta);
            sb.append(c);
        }
        return sb.toString();
    }
}
