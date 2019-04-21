Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)

Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:

0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 

Example 1:

Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
Output: 20
Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
Example 2:

Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
Output: 29
Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
Example 3:

Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
Output: 31
Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.
 

Note:

L >= 1
M >= 1
L + M <= A.length <= 1000
0 <= A[i] <= 1000

Method 1: DP
Time complexity: O(N)
Space complexity: O(N)
 

 class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int N = A.length;
        int[] preSum = new int[N+1];
        for (int i = 1; i <= N; i++){
            preSum[i] = preSum[i-1] + A[i-1];
        }
        int res = preSum[L+M] or 0; //denote the result until the first L + M elements
        int maxL = preSum[L] or 0; // denote the max sum of L element before the last M element
        int maxM = preSum[M] or 0; // denote the max sum of M element before the last L element
        for (int i = L + M; i <= N; i++){
            maxL = Math.max(maxL, preSum[i-M] - preSum[i-L-M]);
            maxM = Math.max(maxM, preSum[i-L] - preSum[i-L-M]);
            res = Math.max(res, Math.max(maxL + preSum[i] - preSum[i-M], maxM + preSum[i]- preSum[i-L]));
        }
        return res;
        
    }
}

Method 2: Brute force:
Time complexity: O(N^2)
Space complexity: O(N)
class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int N = A.length;
        int[] preSum = new int[N+1];
        for (int i = 1; i <= N; i++){
            preSum[i] = preSum[i-1] + A[i-1];
        }
        int res = 0;
        for (int i = 0; i < N - L - M + 1; i++){
            int sumL = preSum[i+L] - preSum[i];
            for (int j = i + L; j < N - M + 1; j++){
                int sumM = preSum[j+M] - preSum[j];
                res = Math.max(res, sumL + sumM);
            }
        }
        
        for (int i = 0; i < N - L - M + 1; i++){
            int sumM = preSum[i+M] - preSum[i];
            for (int j = i + M; j < N- L + 1; j++){
                int sumL = preSum[j+L] - preSum[j];
                res = Math.max(res, sumL + sumM);
            }
        }
        return res;
    }
}
