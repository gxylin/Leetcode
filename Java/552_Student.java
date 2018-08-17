Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8 
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times. 
Note: The value of n won't exceed 100,000.

https://leetcode.com/problems/student-attendance-record-ii/discuss/101643/Share-my-O(n)-C++-DP-solution-with-thinking-process-and-explanation?page=2

Time complexity: O(n)
Space complexity: O(n)

class Solution {
    public int checkRecord(int n) {
        int M = (int) Math.pow(10, 9) + 7;
        int[] A = new int[n+1];
        int[] P = new int[n+1];
        int[] L = new int[n+1];
        P[1] = 1;
        A[1] = 1;
        L[1] = 1;
        if (n == 1){
            return A[1] + L[1] + P[1];
        }
        P[2] = 3;
        A[2] = 2;
        L[2] = 3;
        if (n == 2){
            return A[2] + L[2] + P[2];
        }
        P[3] = 8;
        A[3] = 4;
        L[3] = 7;
        if (n == 3){
            return A[3] + L[3] + P[3];
        }
        for (int i = 4; i <= n; i++){
            P[i-1] %= M;
            A[i-1] %= M;
            L[i-1] %= M;
            P[i] = ((P[i-1] + A[i-1])%M + L[i-1])%M;
            L[i] = ((P[i-1] + A[i-1])%M + (P[i-2] + A[i-2])%M)%M;
            A[i] = ((A[i-1] + A[i-2])%M + A[i-3])%M;
        }
        return (((A[n]%M) + (P[n]%M))%M + (L[n]%M))%M;
    }
}

Finally, the recursive formula group becomes

P(n) = A(n - 1) + P(n - 1) + L(n - 1), n ≥ 2.
L(n) = A(n - 1) + P(n - 1) + A(n - 2) + P(n - 2), n ≥ 3.
A(n) = A(n - 1) + A(n - 2) + A(n - 3), n ≥ 4.


How to deduce 
      When n ≥ 4, the 3 formulas

      A(n) = noAP(n - 1) + noAL(n - 1), n ≥ 2.
      noAP(n) = noAP(n - 1) + noAL(n - 1), n ≥ 2.
      noAL(n) = noAP(n - 1) + noAP(n - 2), n ≥ 3.
      can be simplified to

      A(n) = A(n - 1) + A(n - 2) + A(n - 3), n ≥ 4.

Answer:
A(n) = noAP(n - 1) + noAL(n - 1), n ≥ 2.
noAP(n) = noAP(n - 1) + noAL(n - 1), n ≥ 2.
which means

A(n) = noAP(n), n ≥ 2.
As

noAL(n) = noAP(n - 1) + noAP(n - 2), n ≥ 3.
Then

noAL(n) = A(n - 1) + A(n - 2), n ≥ 3.
As

A(n) = noAP(n - 1) + noAL(n - 1), n ≥ 2.
Then I get

A(n) = A(n - 1) + A(n - 2) + A(n - 3), n ≥ 4.



