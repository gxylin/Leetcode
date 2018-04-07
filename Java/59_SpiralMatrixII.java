Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int top = 0;
        int bottom = n-1;
        int left = 0;
        int right = n-1;
        int count = 1;
        while (top <= bottom && left <= right){
            for (int i = left; i <= right; i++){
                result[top][i] = count;
                count++;
            }
            top++;
            for (int i = top; i <= bottom; i++){
                result[i][right] = count;
                count++;
            }
            right--;
            if (top > bottom || left > right){
                break;
            }
            for (int i = right; i>= left; i--){
                result[bottom][i] = count;
                count++;
            }
            bottom--;
            for (int i = bottom; i>= top; i--){
                result[i][left] = count;
                count++;
            }
            left++;
        }
        return result;
    }
}
