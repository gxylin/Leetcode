Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false

Method: binary search
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0;
        int high = m * n - 1;
        while (low <= high){
            int mid = low + (high - low) / 2;
            int num = converter(matrix, mid);
            if (num == target){
                return true;
            }else if (num < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return false;
    }
    private int converter(int[][] matrix, int index){
        int n = matrix[0].length;
        int row = index / n;
        int col = index % n;
        return matrix[row][col];
    }
}
