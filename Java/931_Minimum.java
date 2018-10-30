Given a square array of integers A, we want the minimum sum of a falling path through A.

A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.

 

Example 1:

Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: 12
Explanation: 
The possible falling paths are:
[1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
[2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
[3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
The falling path with the smallest sum is [1,4,7], so the answer is 12.

 

Note:

1 <= A.length == A[0].length <= 100
-100 <= A[i][j] <= 100



Top down DP Solution which is very straightforward.

dp solution:
1)  dp[i][j] denotes the minimum path sum at point [i, j]. 

2) The formula of dp is below
dp[i][j] = A[i][j] + Min(dp[i-1][j-1], dp[i-1][j]. dp[i-1][j+1])

To take care of the boundary case, we need use two if conditions (i.e. if j >= 1 and if j < n -1)

3) Initialize the dp with the first row of A matrix.
4) So the solution will be the minimum value at the last row of dp matrix (i.e., dp[m-1][j]).

```
class Solution {
    public int minFallingPathSum(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int[][] dp = new int[m][n];
        for (int j = 0; j < n; j++){
            dp[0][j] = A[0][j];
        }
        for (int i = 1; i < m; i++){
            for (int j = 0; j < n; j++){
                dp[i][j] = dp[i-1][j];
                if (j >= 1){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
                }
                if (j < n - 1){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j+1]);
                }
                dp[i][j] += A[i][j];
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++){
            res = Math.min(res, dp[m-1][j]);
        }
        return res;
    }
}
```
