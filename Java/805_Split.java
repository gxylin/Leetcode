In a given integer array A, we must move every element of A to either list B or list C. (B and C initially start empty.)

Return true if and only if after such a move, it is possible that the average value of B is equal to the average value of C, and B and C are both non-empty.

Example :
Input: 
[1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have the average of 4.5.
Note:

The length of A will be in the range [1, 30].
A[i] will be in the range of [0, 10000].

https://leetcode.com/problems/split-array-with-same-average/discuss/120660/Java-accepted-recursive-solution-with-explanation

Key point: avgA = avgB = avgC
The goal is to find array B that the average of B equals to avg A.
So it will be converted to N sum problem: find lenB that sum of lenB equals to sum/n    
Since only split to two arrays, so we try all the possibility of lenB from 1 to n/2

    
class Solution {
    public boolean splitArraySameAverage(int[] A) {
        int sum = 0;
        for (int i : A){
            sum += i;
        }
        Arrays.sort(A);//use sort to prune to accelerate recursion
        int n = A.length;
        for (int i = 1; i <= n / 2; i++){
            if ((sum * i) % n == 0){
                if (canSplit(A, sum * i / n, i, 0)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean canSplit(int[] A, int target, int len, int start){
        if (len == 0){
            return target == 0;
        }
        for (int i = start; i + len  <= A.length; i++){//note that i + len < A.length is wrong because A[i] is not taken yet
            if (A[start] * len > target){
                break;//or return false;
            }
            if (i > start && A[i] == A[i-1]){
                continue;
            }
            if (canSplit(A, target - A[i], len - 1, i + 1)){
                return true;
            }
        }
        return false;
    }
}
