Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.

Method: 
first binary search => target row
second binary search => target col

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int start = 0;
        int end = matrix.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] == target){
                return true;
            }else if (matrix[mid][0] > target){
                end = mid;
            }else{
                start = mid;
            }
        }
        int row = 0;
        if (matrix[start][0] <= target){
            row = start;
        }
        if (matrix[end][0] <= target){
            row = end;
        }
        start = 0;
        end = matrix[0].length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] == target){
                return true;
            }else if (matrix[row][mid] > target){
                end = mid;
            }else{
                start = mid;
            }
        }
        if (matrix[row][start] == target){
            return true;
        }
        if (matrix[row][end] == target){
            return true;
        }
        return false;
    }
}
