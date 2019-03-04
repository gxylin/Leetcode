An encoded string S is given.  To find and write the decoded string to a tape, the encoded string is read one character at a time and the following steps are taken:

If the character read is a letter, that letter is written onto the tape.
If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.

 

Example 1:

Input: S = "leet2code3", K = 10
Output: "o"
Explanation: 
The decoded string is "leetleetcodeleetleetcodeleetleetcode".
The 10th letter in the string is "o".
Example 2:

Input: S = "ha22", K = 5
Output: "h"
Explanation: 
The decoded string is "hahahaha".  The 5th letter is "h".
Example 3:

Input: S = "a2345678999999999999999", K = 1
Output: "a"
Explanation: 
The decoded string is "a" repeated 8301530446056247680 times.  The 1st letter is "a".
 

Note:

2 <= S.length <= 100
S will only contain lowercase letters and digits 2 through 9.
S starts with a letter.
1 <= K <= 10^9
The decoded string is guaranteed to have less than 2^63 letters.


 https://leetcode.com/problems/decoded-string-at-index/discuss/156747/C%2B%2BPython-O(N)-Time-O(1)-Space
If it's S[i] = d is a digit, then N = N / d before repeat and K = K % N is what we want.
If it's S[i] = c is a character, we return c if K == 0 or K == N
 
class Solution {
    public String decodeAtIndex(String S, int K) {
        long N = 0L;
        int i = 0;
        for (i = 0; N < K; i++){
            char c = S.charAt(i);
            if (Character.isLetter(c)){
                N++;
            }else{
                N *= (int) (c - '0');
            }
        }
        i--;
        while (i >= 0){
            char c = S.charAt(i);
            if (Character.isLetter(c) && (K % N == 0)){
                return String.valueOf(c);
            }else if (Character.isDigit(c)){
                N /= (int)(c - '0');
                K %= N;
            }else{
                N--;
            }
            i--;
        }
        return "";
    }
}
