Merge two given sorted integer array A and B into a new sorted integer array.

Have you met this question in a real interview? Yes
Example
A=[1,2,3,4]

B=[2,4,5,6]

return [1,2,2,3,4,4,5,6]

public class Solution {
    /*
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        if (A == null || B == null){
            return null;
        }
        int[] result = new int[A.length + B.length];
        int i = 0, j = 0, index = 0;
        while (i < A.length && j < B.length){
            if (A[i] < B[j]){
                result[index++] = A[i++];
            }else{
                result[index++] = B[j++];
            }
        }
        while (i < A.length){
            result[index++] = A[i++];
        }
        while (j < B.length){
            result[index++] = B[j++];
        }
        return result;
    }
}
