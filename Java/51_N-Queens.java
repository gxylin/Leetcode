The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' 
both indicate a queen and an empty space respectively.

Have you met this question in a real interview? Yes
Example
There exist two distinct solutions to the 4-queens puzzle:

[
  // Solution 1
  [".Q..",
   "...Q",
   "Q...",
   "..Q."
  ],
  // Solution 2
  ["..Q.",
   "Q...",
   "...Q",
   ".Q.."
  ]
]

public class Solution {
    /*
     * @param n: The number of queens
     * @return: All distinct solutions
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <=0){
            return result;
        }
        
        search(result, new ArrayList<Integer>(), n);
        return result;
    }
    private void search(List<List<String>> result, List<Integer> cols, int n){
        if (cols.size() == n){
            result.add(drawChessBoard(new ArrayList<Integer>(cols)));
            return;
        }
        for (int colIndex = 0; colIndex < n; colIndex++){
            if (!isValid(cols, colIndex)){
                continue;
            }
            cols.add(colIndex);
            search(result, cols, n);
            cols.remove(cols.size() - 1);
        }
    }
    private boolean isValid(List<Integer> cols, int colIndex){
        int rowIndex = cols.size();
        for (int i = 0; i < rowIndex; i++){
            if (cols.get(i) == colIndex){
                return false;
            }
            if (cols.get(i) + i == colIndex + rowIndex){
                return false;
            }
            if (cols.get(i) - i == colIndex - rowIndex){
                return false;
            }
        }
        return true;
    }
    private List<String> drawChessBoard(List<Integer> cols){
        ArrayList<String> result = new ArrayList<>();
        int size = cols.size();
        for (int i = 0; i < size; i++){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < size; j++){
                if (j == cols.get(i)){
                    sb.append('Q');
                }else{
                    sb.append('.');
                }
            }
            result.add(sb.toString());
        }
        return result;
    }
}
