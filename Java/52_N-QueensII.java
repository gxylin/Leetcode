Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.

class Solution {
    public int totalNQueens(int n) {
        if (n <= 0){
            return 0;
        }
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<Integer>(), n);
        return result.size();
    }
    private void dfs(List<List<Integer>> result, List<Integer> cols, int n){
        if (cols.size() == n){
            result.add(new ArrayList<Integer>(cols));
            return;
        }
        for (int i = 0; i < n; i++){
            if (isValid(cols, i)){
                cols.add(i);
                dfs(result, cols, n);
                cols.remove(cols.size() - 1);
            }
        }
    }
    private boolean isValid(List<Integer> cols, int colIndex){
        int rowIndex = cols.size();
        for (int i = 0; i < rowIndex; i++){
            if (colIndex == cols.get(i)){
                return false;
            }
            if (colIndex + rowIndex == cols.get(i) + i){
                return false;
            }
            if (colIndex - rowIndex == cols.get(i) - i){
                return false;
            }
        }
        return true;
    }
}
