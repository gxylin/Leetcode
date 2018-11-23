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

Method 2: one bfs

class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0){
            return;
        }
        int n = board.length;
        int m = board[0].length;
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        
        int[] dx = {1, 0 , -1 ,0};
        int[] dy = {0, 1, 0, -1};
        
        for (int i = 0; i < m; i++){
            if (board[0][i] == 'O'){
                qx.offer(0);
                qy.offer(i);
                board[0][i] = 'W';
            }
            if (board[n-1][i] == 'O'){
                qx.offer(n-1);
                qy.offer(i);
                board[n-1][i] = 'W';
            }
        }
        for (int i = 0; i < n; i++){
            if (board[i][0] == 'O'){
                qx.offer(i);
                qy.offer(0);
                board[i][0] = 'W';
            }
            if (board[i][m-1] == 'O'){
                qx.offer(i);
                qy.offer(m-1);
                board[i][m-1] = 'W';
            }
        }
        
        while (!qx.isEmpty()){
            int cx = qx.poll();
            int cy = qy.poll();
            for (int i = 0; i < 4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (inBound(n, m, nx, ny) && board[nx][ny] == 'O'){
                    qx.offer(nx);
                    qy.offer(ny);
                    board[nx][ny] = 'W';
                }
            }
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
    private boolean inBound(int n, int m, int nx, int ny){
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }
}


Better version:
class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0){
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++){
            if (board[i][0] == 'O'){
                bfs(board, i, 0);
            }
            if (board[i][n-1] == 'O'){
                bfs(board, i, n-1);
            }
        }
        for (int j = 0; j < n; j++){
            if (board[0][j] == 'O'){
                bfs(board, 0, j);
            }
            if (board[m-1][j] == 'O'){
                bfs(board, m-1, j);
            }
        }
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if (board[i][j] == 'W'){
                    board[i][j] = 'O';
                }
            }
        }
    }
    private void bfs(char[][] board, int x, int y){
        int m = board.length;
        int n = board[0].length;
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        board[x][y] = 'W';
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int[] dir : dirs){
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && board[nx][ny] == 'O'){
                    queue.offer(new int[]{nx, ny});
                    board[nx][ny] = 'W';      
                }
            }
        }
    }
}
