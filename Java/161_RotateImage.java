You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).

Have you met this question in a real interview? Yes
Example
Given a matrix

[
    [1,2],
    [3,4]
]
rotate it by 90 degrees (clockwise), return

[
    [3,1],
    [4,2]
]
Challenge 
Do it in-place.

• 旋转方法总结:
– 顺时针90:先上下,再对角线 – 逆时针90:先左右,再对角线
￼note the different range for different flips

public class Solution {
    /*
     * @param matrix: a lists of integers
     * @return: 
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return;
        }
        int n = matrix.length;
        for (int i = 0; i < n/2; i++){          //1. flip along x-axis
            for (int j = 0; j < n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-i][j];
                matrix[n-1-i][j] = temp;
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = i; j < n; j++){            //2. flip along diagronal
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}

class Solution {
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int j = 0; j < n; j++){
            for (int i = 0; i < m/2; i++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[m-1-i][j];
                matrix[m-1-i][j] = temp;
            }
        }
        for (int i = 0; i < m; i++){
            for (int j = 0; j < i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
