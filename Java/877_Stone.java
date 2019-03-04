Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive 
integer number of stones piles[i].

The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.

Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning
or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.

Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.

 

Example 1:

Input: [5,3,4,5]
Output: true
Explanation: 
Alex starts first, and can only take the first 5 or the last 5.
Say he takes the first 5, so that the row becomes [3, 4, 5].
If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
 

Note:

2 <= piles.length <= 500
piles.length is even.
1 <= piles[i] <= 500
sum(piles) is odd.

https://leetcode.com/problems/stone-game/discuss/154610/C++JavaPython-DP-or-Just-return-true?page=1

class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++){
            dp[i][i] = piles[i];
        }
        for (int i = 0; i < n - 1; i++){
            for (int j = n -1; j > i; j--){
                dp[i][j] = Math.max(piles[i] - dp[i+1][j], piles[j] - dp[i][j-1]);
            }
        }
        return dp[0][n-1] > 0;
    }
}

The same as Leetcode 486 Predict the winner
https://github.com/optimisea/Leetcode/blob/master/Java/486_Predict.java

class Solution {
    public boolean stoneGame(int[] piles) {
        int N = piles.length;
        int[][] cache = new int[N][N];
        for (int i = 0; i < N; i++){
            Arrays.fill(cache[i], -1);
        }
        return maxRelativeStone(piles, 0, N-1, cache) >= 0;
    }
    private int maxRelativeStone(int[] piles, int start, int end, int[][] cache){
        if (cache[start][end] != -1){
            return cache[start][end];
        }
        if (start > end){
            return 0;
        }
        if (start == end){
            return piles[start];
        }
        int chooseFront = piles[start] - maxRelativeStone(piles, start+1, end, cache);
        int chooseEnd = piles[end] - maxRelativeStone(piles, start, end-1, cache);
        cache[start][end] = Math.max(chooseFront, chooseEnd);
        return cache[start][end];
    }
}
