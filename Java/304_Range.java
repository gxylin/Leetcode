Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12

Method 1: Best solution
dp on consructor
class NumMatrix {
    int[][] preSum;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        preSum = new int[m+1][n+1];

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + matrix[i-1][j-1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2+1][col2+1] - preSum[row1][col2+1] - preSum[row2+1][col1] + preSum[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */

Method 2:
use sum to store the row preSum

class NumMatrix {
    int[][] preSum;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        preSum = new int[m+1][n+1];
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                sum += matrix[i][j];
                preSum[i + 1][j + 1] = sum + preSum[i][j + 1]; 
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2+1][col2+1] - preSum[row1][col2+1] - preSum[row2+1][col1] + preSum[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
 
 Method 3:
 First pass to get the row preSum
 Second pass to get the row+col preSum
 class NumMatrix {
    int[][] preSum;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        preSum = new int[m+1][n+1];
        for (int i = 1; i <= m; i++){ 
            for (int j = 1; j <= n; j++){
                preSum[i][j] = preSum[i][j-1] + matrix[i-1][j-1];
            }
        }
        for (int j = 1; j <= n; j++){
            for (int i = 1; i <= m; i++){
                preSum[i][j] += preSum[i-1][j];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2+1][col2+1] - preSum[row1][col2+1] - preSum[row2+1][col1] + preSum[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
