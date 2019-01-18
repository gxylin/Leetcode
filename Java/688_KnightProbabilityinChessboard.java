On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. T
he rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, 
then one square in an orthogonal direction.


Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece 
would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. 
Return the probability that the knight remains on the board after it has stopped moving.

Example:
Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.
Note:
N will be between 1 and 25.
K will be between 0 and 100.
The knight always initially starts on the board.

Method: BFS will lead to TLE, so use DP
https://www.youtube.com/watch?v=MyJvMydR2G4

dp[i][j] is the number of ways to move to [i][j]

class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        double[][] dpPrev = new double[N][N];
        dpPrev[r][c] = 1.0;
    
        int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
        
        for (int k = 0; k < K; k++){
            double[][] dpCur = new double[N][N];
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    for (int l = 0; l < dx.length; l++){
                        int nx = i + dx[l];
                        int ny = j + dy[l];
                        if (inBound(N, nx, ny)){
                            dpCur[i][j] += dpPrev[nx][ny];
                        }
                    }
                    
                }
            }
            dpPrev = dpCur;
        }
        double sum  = 0.0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                sum += dpPrev[i][j];
            }
        }
        return sum / Math.pow(8, K);
    }
    private boolean inBound(int N, int nx, int ny){
        return nx >= 0 && nx < N && ny >=0 && ny < N;
    }
}


class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        double[][] dpPrev = new double[N][N];
        dpPrev[r][c] = 1.0;
        int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
        for (int k = 0; k < K; k++){
            double[][] dpCur = new double[N][N];
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    for (int l = 0; l < dx.length; l++){
                        int nx = i - dx[l];
                        int ny = j - dy[l];
                        if (nx >= 0 && nx < N && ny >= 0 && ny < N){
                            dpCur[i][j] += dpPrev[nx][ny];
                        }
                    }
                }
            }
            dpPrev = dpCur;
        }
        double sum = 0.0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                sum += dpPrev[i][j];
            }
        }
        return sum / Math.pow(8, K);
    }
}
