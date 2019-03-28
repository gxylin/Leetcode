On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.

Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last
row and column.

Now, we walk in a clockwise spiral shape to visit every position in this grid. 

Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid 
boundary later.) 

Eventually, we reach all R * C spaces of the grid.

Return a list of coordinates representing the positions of the grid in the order they were visited.

 

Example 1:

Input: R = 1, C = 4, r0 = 0, c0 = 0
Output: [[0,0],[0,1],[0,2],[0,3]]


 

Example 2:

Input: R = 5, C = 6, r0 = 1, c0 = 4
Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],
         [4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]


 

Note:

1 <= R <= 100
1 <= C <= 100
0 <= r0 < R
0 <= c0 < C

Intuition

We can walk in a spiral shape from the starting square, ignoring whether we stay in the grid or not. Eventually, 
we must have reached every square in the grid.

Algorithm

Examining the lengths of our walk in each direction, we find the following pattern: 1, 1, 2, 2, 3, 3, 4, 4, ... 
 That is, we walk 1 unit east, then 1 unit south, then 2 units west, then 2 units north, then 3 units east, etc.
 Because our walk is self-similar, this pattern repeats in the way we expect.


class Solution {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] res = new int[R*C][2];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int count = 1;
        int step = 1;
        res[0][0] = r0;
        res[0][1] = c0;
        int r = r0;
        int c = c0;
        while (count < R * C){
            int i = 0;
            while (i < 2){
                int j = 0;
                while (j < step){
                    r += dx[i];
                    c += dy[i];
                    if (r >= 0 && r < R && c >= 0 && c < C){
                        res[count][0] = r;
                        res[count][1] = c;
                        count++;
                    }
                    j++;
                }
                i++;
            }
            step++;
            while (i < 4){
                int j = 0;
                while (j < step){
                    r += dx[i];
                    c += dy[i];
                    if (r >= 0 && r < R && c >= 0 && c < C){
                        res[count][0] = r;
                        res[count][1] = c;
                        count++;
                    }
                    j++;
                }
                i++;
            }
            step++;
        }
        return res;
    }
}

https://leetcode.com/problems/spiral-matrix-iii/discuss/158970/C%2B%2BJavaPython-112233-Steps

