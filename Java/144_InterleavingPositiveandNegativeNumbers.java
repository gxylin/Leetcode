Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

 Notice
You are not necessary to keep the original order of positive integers or negative integers.

Have you met this question in a real interview? Yes
Example
Given [-1, -2, -3, 4, 5, 6], after re-range, it will be [-1, 5, -2, 4, -3, 6] or any other reasonable answer.

public class Solution {
    /*
     * @param A: An integer array.
     * @return: nothing
     */
    public void rerange(int[] A) {
        if(A == null || A.length == 0){
            return;
        }
        int start = 0;
        int end = A.length - 1;
        while (start < end){
            while (start < end && A[start] < 0){
                start++;
            }
            while (start < end && A[end] > 0){
                end--;
            }
            if (start < end){
                swap(A, start, end);
                start++;
                end--;
            }
        }
        if (A.length % 2 == 0){
            swapPosNeg(A, 0, A.length - 1);
        }else{
            int mid = A.length / 2;
            if (A[mid] % 2 < 0){
                swapPosNeg(A, 1, A.length - 1);
            }else{
                swapPosNeg(A, 0, A.length - 2);
            }
        }
    }
    private void swapPosNeg(int[] A, int left, int right){
        while (left < right){
            swap(A, left, right);
            left = left + 2;
            right = right - 2;
        }
    }
    private void swap (int[] A, int a, int b){
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
}
