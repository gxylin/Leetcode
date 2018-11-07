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

Method 1: O(logm + logn)
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

Method 2:
Time complexity O(log(mn) = logm + logn)
 // we can view this matrix as an sorted array matrix[0, m *n - 1], given a index in array
// we can use (index / n) to get row index and use (index % n) to get column index
// then use binary search to find the target
 class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0;
        int end = m * n - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            int r = mid / n;
            int c = mid % n;
            if (matrix[r][c] == target){
                return true;
            }else if (matrix[r][c] < target){
                start = mid;
            }else{
                end = mid;
            }
        }
        if (matrix[start/n][start%n] == target || matrix[end/n][end%n] == target){
            return true;
        }
        return false;
        
    }
}


Better version:
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
