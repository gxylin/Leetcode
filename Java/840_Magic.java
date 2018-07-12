A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.

Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).

 

Example 1:

Input: [[4,3,8,4],
        [9,5,1,9],
        [2,7,6,2]]
Output: 1
Explanation: 
The following subgrid is a 3 x 3 magic square:
438
951
276

while this one is not:
384
519
762

In total, there is only one magic square inside the given grid.
Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
0 <= grid[i][j] <= 15

class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m-2; i++){
            for (int j = 0; j < n-2; j++){
                if (validMagic(grid, i, j)){
                    count++;
                }
            }
        }
        return count;
    }
    private boolean validMagic(int[][] grid, int x, int y){
        //check distinct
        Set<Integer> set = new HashSet<>();
        for (int i = x; i < x+3; i++){
            for (int j = y; j < y+3; j++){
                if (!set.add(grid[i][j])){
                    return false;
                }
            }
        }
        //check horizal
        for (int i = x; i < x+3; i++){
            int rowSum = 0;
            for (int j = y; j < y+3; j++){
                rowSum += grid[i][j];
            }
            if (rowSum != 15){
                return false;
            }
        }
        //check vertical
        for (int j = y; j < y+3; j++){
            int colSum = 0;
            for (int i = x; i < x+3; i++){
                colSum += grid[i][j];
            }
            if (colSum != 15){
                return false;
            }
        }
        //check diagonal
        int diagSum = grid[x][y] + grid[x+1][y+1] + grid[x+2][y+2];
        if (diagSum != 15){
            return false;
        }
        //check anti-diagonal
        int antidiagSum = grid[x+2][y] + grid[x+1][y+1] + grid[x][y+2];
        if (antidiagSum != 15){
            return false;
        }
        return true;
    }
}
