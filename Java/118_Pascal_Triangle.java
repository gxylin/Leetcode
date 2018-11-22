Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

Method 1:
DP
Time complexity: O(n^2)
Space complexity: O(n^2)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        int[][] dp = new int[numRows+1][numRows+1];
        for (int i = 1; i <= numRows; i++){
            List<Integer> item = new ArrayList<>();
            for (int j = 1; j <= i; j++){
                if (j == 1 || j == i){
                    dp[i][j] = 1;
                }else{
                   dp[i][j] =  dp[i-1][j-1] + dp[i-1][j];
                }
                item.add(dp[i][j]);
            }
            result.add(item);
        }
        return result;
    }
}
Method 2:
DP + rolling array
Time complexity: O(n^2)
Space complexity: O(n)

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        int[][] dp = new int[2][numRows+1];
        for (int i = 1; i <= numRows; i++){
            List<Integer> item = new ArrayList<>();
            for (int j = 1; j <= i; j++){
                if (j == 1 || j == i){
                    dp[i%2][j] = 1;
                }else{
                   dp[i%2][j] =  dp[(i-1)%2][j-1] + dp[(i-1)%2][j];
                }
                item.add(dp[i%2][j]);
            }
            result.add(item);
        }
        return result;
    }
}

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0){
            return res;
        }
        List<Integer> l = new ArrayList<>();
        l.add(1);
        res.add(l);
        if (numRows == 1){
            return res;
        }
        l = new ArrayList<>();
        l.add(1);
        l.add(1);
        res.add(l);
        if (numRows == 2){
            return res;
        }
        for (int i = 2; i < numRows; i++){
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 1; j < i; j++){
                list.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
            }
            list.add(1);
            res.add(list);
        }
        return res;
    }
}
