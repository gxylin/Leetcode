Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] heights = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == '0'){
                    heights[i][j] = 0;
                }else{
                    heights[i][j] = (i == 0) ? 1 : heights[i-1][j] + 1;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < m; i++){
            int area = largestRectangleArea(heights[i]);
            if (area > max){
                max = area;
            }
        }
        return max;
    }
    private int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0){
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i <= heights.length; i++){
            int curr = (i == heights.length) ? -1 : heights[i];
            while (!stack.isEmpty() && curr < heights[stack.peek()]){
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        return max;
    }
}
