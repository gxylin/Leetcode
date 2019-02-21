We have a two dimensional matrix A where each value is 0 or 1.

A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.

After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

Return the highest possible score.

 

Example 1:

Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
Output: 39
Explanation:
Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 

Note:

1 <= A.length <= 20
1 <= A[0].length <= 20
A[i][j] is 0 or 1.

Greedy: Notice that a 1 in the iith column from the right, contributes 2^i2
​i
​​  to the score.

Since 2^n > 2^{n-1} + 2^{n-2} + \cdots + 2^02
​n
​​ >2
​n−1
​​ +2
​n−2
​​ +⋯+2
​0
​​ , maximizing the left-most digit is more important than any other digit.
Thus, the rows should be toggled such that the left-most column is either all 0 or all 1 (so that after toggling the left-most column [if necessary], the left column is all 1.)

Algorithm

If we toggle rows by the first column (A[r][c] ^= A[r][0]), then the first column will be all 0.

Afterwards, the base score is max(col, R - col) where col is the column sum; and (1 << (C-1-c)) is the power of 2 that each 1 in that column contributes to the score.
class Solution {
    public int matrixScore(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int ans = 0;
        for (int j = 0; j < n; j++){
            int zeros = 0;
            for (int i = 0; i < m; i++){
                zeros += A[i][j] ^ A[i][0];
            }
            ans += Math.max(zeros, m - zeros) * (1 << (n-1-j));
        }
        return ans;
    }
}


O(m*n)
 class Solution {
    public int matrixScore(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        for (int i = 0; i < m; i++){
            if (A[i][0] == 0){
                for (int j = 0; j < n; j++){
                    A[i][j] ^= 1;
                }
            }
        }
        for (int j = 1; j < n; j++){
            int count = 0;
            for (int i = 0; i < m; i++){
                if (A[i][j] == 0){
                    count++;
                }
            }
            if (count > m - count){
                for (int i = 0; i < m; i++){
                    A[i][j] ^= 1;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (A[i][j] == 1){
                    res += (1 << n-1-j);
                }   
            }
        }
        return res;
    }
}
