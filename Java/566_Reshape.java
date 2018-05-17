In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.

You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.

The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.

If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

Example 1:
Input: 
nums = 
[[1,2],
 [3,4]]
r = 1, c = 4
Output: 
[[1,2,3,4]]
Explanation:
The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
Example 2:
Input: 
nums = 
[[1,2],
 [3,4]]
r = 2, c = 4
Output: 
[[1,2],
 [3,4]]
Explanation:
There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
Note:
The height and width of the given matrix is in range [1, 100].
The given r and c are all positive.

Note that this implementation is different from Matlab implementation because Matlab implementation is column traversing order
Method 1:
Space complexity O(r*c)
class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null || nums.length == 0){
            return nums;
        }
        int rows = nums.length;
        int cols = nums[0].length;
        if (rows * cols != r * c){
            return nums;
        }
        int[] count = new int[rows * cols];
        int k = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                count[k++] = nums[i][j];
            }
        }
        int[][] result = new int[r][c];
        k = 0;
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                result[i][j] = count[k++];
            }
        }
        return result;
    }
}

Method 2: 
Space complexity: O(1)
class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null || nums.length == 0){
            return nums;
        }
        int rows = nums.length;
        int cols = nums[0].length;
        if (rows * cols != r * c){
            return nums;
        }
        int[][] result = new int[r][c];
        for (int i = 0; i < r*c; i++){
            result[i/c][i%c] = nums[i/cols][i%cols];
        }
        
        return result;
    }
}
