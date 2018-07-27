There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.

Example 1:
Input:m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:

Example 2:
Input:m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:

Note:
Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].

https://leetcode.com/problems/out-of-boundary-paths/solution/

Method 1: Brute Force (TLE)
Time complexity : O(4^n) Size of recursion tree will be 4^n. Here, n refers to the number of moves allowed.
Space complexity : O(n). The depth of the recursion tree can go upto n.

class Solution {
    public int findPaths(int m, int n, int N, int i, int j) {
        if (i == m || j == n || i < 0 || j < 0){
            return 1;
        }
        if (N == 0){
            return 0;
        }
        return findPaths(m, n, N-1, i-1, j) + findPaths(m, n, N-1, i+1, j) + findPaths(m, n, N-1, i, j-1) + findPaths(m, n, N-1, i, j+1);
    }
}

Method 2: Memo
Time complexity : O(m*n*N)O(m∗n∗N). We need to fill the memomemo array once with dimensions mmxnnxNN. Here, mm, nn refer to the number of rows and columns of the given grid respectively. NN refers to the total number of allowed moves.

Space complexity : O(m*n*N)O(m∗n∗N). memomemo array of size m*n*Nm∗n∗N is used.
class Solution {
    int M=1000000007;
    public int findPaths(int m, int n, int N, int i, int j) {
        int[][][] memo = new int[m][n][N+1];
        for (int ii = 0; ii < m; ii++){
            for (int jj = 0; jj < n; jj++){
                Arrays.fill(memo[ii][jj], -1);
            }
        }
        return dfs(m, n, N, i, j, memo);
    }
    private int dfs(int m, int n, int N, int i, int j, int[][][] memo){
        if (i == m || j == n || i < 0 || j < 0){
            return 1;
        }
        if (N == 0){
            return 0;
        }
        if (memo[i][j][N] >= 0){
            return memo[i][j][N];
        }
       //  memo[i][j][N]=((dfs(m,n,N-1,i-1,j,memo)+dfs(m,n,N-1,i+1,j,memo))%M+(dfs(m,n,N-1,i,j-1,memo)+dfs(m,n,N-1,i,j+1,memo))%M)%M;
        memo[i][j][N] = ((dfs(m, n, N-1, i-1, j, memo)%M + dfs(m, n, N-1, i+1, j, memo)%M)%M + (dfs(m, n, N-1, i, j-1, memo)%M
            + dfs(m, n, N-1, i, j+1, memo)%M)%M)%M;
        return memo[i][j][N];
    }
}

Method 3: Dynamic Programming
DP[i][j][k] stands for how many possible ways to walk into cell j,k in step i, DP[i][j][k] only depends on DP[i - 1][j][k], 
so we can compress 3 dimensional dp array to 2 dimensional.
https://leetcode.com/problems/out-of-boundary-paths/discuss/102967/Java-Solution-DP-with-space-compression

Time complexity : O(N*m*n). We need to fill the dp array with dimensions mn N times. 
Here mn refers to the size of the grid and N refers to the number of moves available.

Space complexity : O(m*n). dp and temp array of size mmxnn are used.
class Solution {
    public int findPaths(int m, int n, int N, int i, int j) {
        int MOD = (int)Math.pow(10, 9) + 7;
        int[][] dp = new int[m][n];//record the number of ways to reach to row, col index
        dp[i][j] = 1; //reach to i, j in zero move, so the number of ways is 1
        int count = 0;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int move = 1; move <= N; move++){
            int[][] temp = new int[m][n];
            for (int r = 0; r < m; r++){
                for (int c = 0; c < n; c++){
                    for (int k = 0; k < dx.length; k++){
                        int nx = r + dx[k];
                        int ny = c + dy[k];
                        if (nx < 0 || nx >= m || ny < 0 || ny >= n){
                            count = (count + dp[r][c]) % MOD;
                        }else{
                            temp[nx][ny] = (temp[nx][ny] + dp[r][c]) % MOD; 
                        }
                    }
                }
            }
            dp = temp;
        }
        return count;
    }
}
