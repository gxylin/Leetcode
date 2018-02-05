Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].


class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return result;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = m - 1;
        while (top <= bottom && left <= right){
            for (int i = left; i <= right; i++){
                result.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++){
                result.add(matrix[i][right]);
            }
            right--;
            if (top > bottom || left > right){
                break;
            }
            for (int i = right; i>= left; i--){
                result.add(matrix[bottom][i]);
            }
            bottom--;
            for (int i = bottom; i >= top; i--){
                result.add(matrix[i][left]);
            }
            left++;
        }
        return result;
    }
}
