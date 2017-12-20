Given a sorted array of n integers, find the starting and ending position of a given target value.

If the target is not found in the array, return [-1, -1].

Have you met this question in a real interview? Yes
Example
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

public class Solution {
    /*
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        int[] results = new int[2];
        results[0] = -1;
        results[1] = -1;
        if (A == null || A.length == 0){
            return results;
        }
        
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] == target){
                end = mid;
            }else if (A[mid] < target){
                start = mid;
            }else{
                end = mid;
            }
        }
        if (A[start] == target){
            results[0] = start;
        }else if (A[end] == target){
            results[0] = end;
        }
        
        start  = 0;
        end = A.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] == target){
                start = mid;
            }else if (A[mid] < target){
                start = mid;
            }else{
                end = mid;
            }
        }
        if (A[end] == target){
            results[1] = end;
        }else if (A[start] == target){
            results[1] = start;
        }
        return results;
    }
}
