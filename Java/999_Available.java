On an 8 x 8 chessboard, there is one white rook.  There also may be empty squares, white bishops, and black pawns.  These are given 
as characters 'R', '.', 'B', and 'p' respectively. Uppercase characters represent white pieces, and lowercase characters represent
black pieces.

The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south), then moves in
that direction until it chooses to stop, reaches the edge of the board, or captures an opposite colored pawn by moving to the 
same square it occupies.  Also, rooks cannot move into the same square as other friendly bishops.

Return the number of pawns the rook can capture in one move.

 
 
class Solution {
    public int numRookCaptures(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int x = -1;
        int y = -1;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == 'R'){
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        int count = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] dir : dirs){
            int nx = x + dir[0];
            int ny = y + dir[1];
            while (nx >= 0 && nx < m && ny >= 0 && ny < n && board[nx][ny] != 'B'){
                if (board[nx][ny] == 'p'){
                    count++;
                    break;
                }
                nx += dir[0];
                ny += dir[1];
            }
        }
        
        return count;
    }
}
