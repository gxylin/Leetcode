Given an array of integers A, consider all non-empty subsequences of A.

For any sequence S, let the width of S be the difference between the maximum and minimum element of S.

Return the sum of the widths of all subsequences of A. 

As the answer may be very large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: [2,1,3]
Output: 6
Explanation:
Subsequences are [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3].
The corresponding widths are 0, 0, 0, 1, 1, 2, 2.
The sum of these widths is 6.
 

Note:

1 <= A.length <= 20000
1 <= A[i] <= 20000
 
The order in initial arrays doesn't matter, So sort it.

class Solution {
    public int sumSubseqWidths(int[] A) {
        long MOD = (long)1e9 + 7;
        int N = A.length;
        long res = 0;
        Arrays.sort(A);
        long c = 1;
        for (int i = 0; i < N; i++){
            //res = (res + (A[i] * (long)(Math.pow(2, i)  - (long)Math.pow(2, N-1-i) % MOD)) %MOD) % MOD;
            res = (res + (A[i] * c - A[N-1-i] * c)) % MOD;
            c = (c << 1) % MOD;
        }
        return (int)((res + MOD) % MOD);
    }
}
