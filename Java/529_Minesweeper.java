https://leetcode.com/problems/minesweeper/description/

Method 1:
BFS:
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        int[] dx = {1, 0, -1, -1, -1, 0, 1, 1};
        int[] dy = {1, 1, 1, 0, -1, -1, -1, 0};
        
        qx.offer(click[0]);
        qy.offer(click[1]);
        while (!qx.isEmpty()){
            int cx = qx.poll();
            int cy = qy.poll();
            if (board[cx][cy] == 'M'){
                board[cx][cy] = 'X';
            }else{ //board[cx][cy] == 'E' or 'B'
                int count = getMineNumber(board, cx, cy);
                if (count > 0){
                    board[cx][cy] = (char) (count + '0'); // stop search
                }else{
                    board[cx][cy] = 'B';
                    for (int i = 0; i < dx.length; i++){
                        int nx = cx + dx[i];
                        int ny = cy + dy[i];
                        if (nx >= 0 && nx < m && ny >=0 && ny < n && board[nx][ny] == 'E'){
                            qx.offer(nx);
                            qy.offer(ny);
                            board[nx][ny] = 'B'; //avoid to add into queue again
                        }
                    }
                }
            }
        }
        return board;
    }
    private int getMineNumber(char[][] board, int nx, int ny){
        int count = 0;
        int m = board.length;
        int n = board[0].length;
        int[] dx = {1, 0, -1, -1, -1, 0, 1, 1};
        int[] dy = {1, 1, 1, 0, -1, -1, -1, 0};
        for (int i = 0; i < dx.length; i++){
            int x = nx + dx[i];
            int y = ny + dy[i];
            if (x >= 0 && x < m && y >= 0&& y < n){
                if (board[x][y] == 'M' || board[x][y] == 'X'){
                    count++;
                }
            }
        }
        return count;
    }
}
