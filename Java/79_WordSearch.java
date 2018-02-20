Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0){
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (dfs(board, word, i, j, 0, visited)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, int i, int j, int start, boolean[][] visited){
        if (start == word.length()){
            return true;
        }
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0){
            return false;
        }
        if (visited[i][j]){
            return false;
        }
        if (word.charAt(start) != board[i][j]){
            return false;
        }
        visited[i][j] = true;
        boolean left = dfs(board, word, i - 1, j, start + 1, visited);
        boolean right = dfs(board, word, i + 1, j, start + 1, visited);
        boolean top = dfs(board, word, i, j + 1, start + 1, visited);
        boolean bottom = dfs(board, word, i, j - 1, start + 1, visited);
        if (left || right || top || bottom){
            return true;
        }
        visited[i][j] = false; //backtracking
        return false;
    }
}
