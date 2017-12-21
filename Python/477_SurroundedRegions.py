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


class Solution:
    """
    @param: board: board a 2D board containing 'X' and 'O'
    @return: nothing
    """
    def surroundedRegions(self, board):
        if not any(board):
            return
        n, m = len(board), len(board[0])
        q = [ij for k in range(max(n,m)) for ij in [(0, k), (n-1, k), (k, 0), (k, m-1)]]
        while q:
            i,j  = q.pop()
            if 0<= i < n and 0 <= j < m and board[i][j] == 'O':
                board[i][j] = 'W'
                q += [(i, j+1), (i, j-1), (i+1, j), (i-1, j)]
        board[:] = [['XO'[c == 'W'] for c in row] for row in board]
