Give an integer array，find the longest increasing continuous subsequence in this array.

An increasing continuous subsequence:

Can be from right to left or from left to right.
Indices of the integers in the subsequence should be continuous.
 Notice
O(n) time and O(1) extra space.

Have you met this question in a real interview? 
Example
For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.

For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.

public class Solution {
    /*
     * @param A: An array of Integer
     * @return: an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }
        int ans = 1;
        int length = 1;
        for (int i = 1; i < A.length; i++){
            if (A[i] > A[i-1]){
                length++;
            }else{
                length = 1;
            }
            ans = Math.max(ans, length);
        }
        length = 1;
        for (int i = A.length - 2; i >= 0; i--){
            if (A[i+1] < A[i]){
                length++;
            }else{
                length = 1;
            }
            ans = Math.max(ans, length);
        }
        return ans;
    }
}
