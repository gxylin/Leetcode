Given two integers A and B, return any string S such that:

S has length A + B and contains exactly A 'a' letters, and exactly B 'b' letters;
The substring 'aaa' does not occur in S;
The substring 'bbb' does not occur in S.
 

Example 1:

Input: A = 1, B = 2
Output: "abb"
Explanation: "abb", "bab" and "bba" are all correct answers.
Example 2:

Input: A = 4, B = 1
Output: "aabaa"
 

Note:

0 <= A <= 100
0 <= B <= 100
It is guaranteed such an S exists for the given A and B.


class Solution {
    public String strWithout3a3b(int A, int B) {
        StringBuilder sb = new StringBuilder();
        int countA = 0;
        int countB = 0;
        while (A > 0 && B > 0){
            if (A > B){
                if (countA < 2){
                    sb.append('a');
                    A--;
                    countA++;
                    countB = 0;
                }else{
                    sb.append('b');
                    B--;
                    countB = 1;
                    countA = 0;
                }
            }else if (A < B){
                if (countB < 2){
                    sb.append('b');
                    B--;
                    countB++;
                    countA = 0;
                }else{
                    sb.append('a');
                    A--;
                    countA = 1;
                    countB = 0;
                }
            }else{
                if (countA < 2){
                    sb.append('a');
                    countA++;
                    countB = 0;
                    A--;
                }else {
                    sb.append('b');
                    countB++;
                    countA = 0;
                    B--;
                }
            }
        }
        while (A > 0){
            sb.append('a');
            A--;
        }
        while (B > 0){
            sb.append('b');
            B--;
        }
        return sb.toString();
    }
}
