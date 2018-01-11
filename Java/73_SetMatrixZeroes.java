Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

Method 1: 
space complexity: O(mn)
create a new matrix and scan the original matrix, update new matrix

Method 2:
space complexity: O(m+n)
class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] col = new int[n];
        int[] row = new int[m];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == 0){
                    col[j] = 1;
                    row[i] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (row[i] == 1 || col[j] == 1){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

Method 3:
space complexity: O(1)
    // fr = first row
    // fc = first col
    
    // Use first row and first column as markers. 
    // if matrix[i][j] = 0, mark respected row and col marker = 0; indicating
       that later this respective row and col must be marked 0;
    // And because you are altering first row and collumn, 
       you need to  have two variables to track their own status. 
    // So, for ex, if any one of the first row is 0, fr = 0, 
       and at the end set all first row to 0;
       
class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == 0){
                    if (i == 0) firstRowZero = true;
                    if (j == 0) firstColZero = true;
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstRowZero){
            for (int i = 0; i < n; i++){
                matrix[0][i] = 0;
            }
        }
        if (firstColZero){
            for (int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }
    }
}

