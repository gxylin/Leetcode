Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.


A sudoku puzzle...


...and its solution numbers marked in red.

Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.

Method: backtracking
Time complexity:Try 1 through 9 for each cell. The time complexity should be 9 ^ m 
(m represents the number of blanks to be filled in), since each blank can have 9 choices.

class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0){
            return;
        }
        solve(board);
    }
    private boolean solve(char[][] board){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == '.'){
                    for (char c = '1'; c <= '9'; c++){
                        if (isValid(board, i, j, c)){
                            board[i][j] = c;
                            if (solve(board)){
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isValid(char[][] board, int r, int c, char ch){
        for (int i = 0; i < board.length; i++){
            if (board[i][c] == ch){
                return false;
            }
        }
        for (int j = 0; j < board[0].length; j++){
            if (board[r][j] == ch){
                return false;
            }
        }
        int rStart = r/3 * 3;
        int cStart = c/3 * 3;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[rStart+i][cStart+j] == ch){
                    return false;
                }
            }
        }
        return true;
    }
}
