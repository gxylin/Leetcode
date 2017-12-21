Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O''s into 'X''s in that surrounded region.

Have you met this question in a real interview? Yes
Example
X X X X
X O O X
X X O X
X O X X
After capture all regions surrounded by 'X', the board should be:

X X X X
X X X X
X X X X
X O X X

• 找到所有被 ‘X’ 围绕的区域 == 找到被平原围绕的盆地 
– 反向思维:找到没有被平原围绕的盆地


public class Solution {
    /*
     * @param board: board a 2D board containing 'X' and 'O'
     * @return: nothing
     */
    int n, m;
    public void surroundedRegions(char[][] board) {
        n = board.length;
        if (n == 0){
            return;
        }
        m = board[0].length;
        
        for (int i = 0; i < n; i++){
            bfs(board, i, 0);
            bfs(board, i, m - 1);
        }
        for (int j = 0; j < m; j++){
            bfs(board, 0, j);
            bfs(board, n - 1, j);
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (board[i][j] == 'W'){
                    board[i][j] = 'O';
                }else{
                    board[i][j] = 'X';
                }
            }
        }
    }
    private void bfs(char[][] board, int x, int y){
        if (board[x][y] != 'O'){
            return;
        }
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        
        int[] dx = {0, 1, 0 , -1};
        int[] dy = {1, 0, -1, 0};
        
        board[x][y] = 'W';
        qx.offer(x);
        qy.offer(y);
        
        while (!qx.isEmpty()){
            int nx = qx.poll();
            int ny = qy.poll();
            
            for (int i = 0; i < 4; i++){
                int newx = nx + dx[i];
                int newy = ny + dy[i];
                if (newx >= 0 && newx < n && newy >= 0 && newy < m 
                        && board[newx][newy] == 'O'){
                    board[newx][newy] = 'W';
                    qx.offer(newx);
                    qy.offer(newy);
                }
            }
        }
    }
}
