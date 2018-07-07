Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
Example:
X..X
...X
...X
In the above board there are 2 battleships.
Invalid Example:
...X
XXXX
...X
This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
Follow up:
Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?

Method 1: BFS
Time complexity: O(mn)
Space complexity: O(mn)
class Solution {
    public int countBattleships(char[][] board) {
        int count = 0;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == 'X'){
                    board[i][j] = '1';
                    count++;
                    bfs(board, i, j);
                }
            }
        }
        return count;
    }
    private void bfs(char[][] board, int i, int j){
        int m = board.length;
        int n = board[0].length;
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{-1, 0, 1, 0};
        qx.offer(i);
        qy.offer(j);
        while (!qx.isEmpty()){
            int cx = qx.poll();
            int cy = qy.poll();
            for (int k = 0; k < dx.length; k++){
                int nx = cx + dx[k];
                int ny = cy + dy[k];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && board[nx][ny] == 'X'){
                    board[nx][ny] = '1';
                    qx.offer(nx);
                    qy.offer(ny);
                }
            }
        }
    }
}

Method 2: Best solution
https://leetcode.com/problems/battleships-in-a-board/discuss/90902/Simple-Java-Solution
class Solution {
    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int count = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == '.'){
                    continue;
                }
                if (i > 0 && board[i-1][j] == 'X'){
                    continue;
                }
                if (j > 0 && board[i][j-1] == 'X'){
                    continue;
                }
                count++;
            }
        }
        return count;
    }
}
