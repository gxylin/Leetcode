Given an array of integers A and let n to be its length.

Assume Bk to be an array obtained by rotating the array A k positions clock-wise, 
we define a "rotation function" F on A as follow:

F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

Calculate the maximum value of F(0), F(1), ..., F(n-1).

Note:
n is guaranteed to be less than 105.

Example:

A = [4, 3, 2, 6]

F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26

So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.




This is essentially a Math problem.
Consider the array [ A, B, C, D ] with very simple coefficients as following:

f(0) = 0A + 1B + 2C + 3D
f(1) = 3A + 0B + 1C + 2D
f(2) = 2A + 3B + 0C + 1D
f(3) = 1A + 2B + 3C + 0D

We can see from above that:
f(0) -> f(1) -> f(2) -> f(3)
f(i) = f(i - 1) - SUM(A -> D) + N * A[i - 1]


class Solution {
    public int maxRotateFunction(int[] A) {
        int n = A.length;
        int sum = 0;
        int candidate = 0;
        for (int i = 0; i < A.length; i++){
            sum += A[i];
            candidate += A[i] * i;
        }
        int best = candidate;
        for (int i = 0; i < A.length; i++){
            candidate = candidate - sum + n * A[i];
            best = Math.max(best, candidate);
        }
        return best;
    }
}
