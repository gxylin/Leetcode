Given a 2D integer matrix M representing the gray scale of an image, you need to design a 
smoother to make the gray scale of each cell becomes the average gray scale (rounding down)
of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

Example 1:
Input:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
Explanation:
For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0
Note:
The value in the given matrix is in the range of [0, 255].
The length and width of the given matrix are in the range of [1, 150].


class Solution {
    public int[][] imageSmoother(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0){
            return M;
        }
        int m = M.length;
        int n = M[0].length;
        int[][] result = new int[m][n];
        int[] dx = {0, 1, 1, 0, -1, -1, -1, 0, 1};
        int[] dy = {0, 0, 1, 1, 1, 0, -1, -1, -1};
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                int count = 0;
                for (int k = 0; k < dx.length; k++){
                    int cx = i + dx[k];
                    int cy = j + dy[k];
                    if (0 <= cx && cx < m && 0 <= cy && cy < n){
                        count++;
                        result[i][j] += M[cx][cy];
                    }
                }
                result[i][j] = (int) (result[i][j] / count); 
            }
        }
        return result;
    }
}
