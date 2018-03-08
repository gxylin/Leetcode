Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

 Notice
You can not divide any item into small pieces.

Have you met this question in a real interview? 
Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size
we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.

Challenge 
O(n x m) time and O(m) memory.

O(n x m) memory is also acceptable if you do not know how to optimize memory.

Method 1:非滚动
Time complexity: O(n*m)
Space complexity: O(n*m)
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        int n = A.length;
        boolean[][] dp = new boolean[n+1][m+1];
        for (int i = 0; i <= n; i++){
            dp[i][0] = true;
        }
        for (int j = 1; j <= m; j++){
            dp[0][j] = false;
        }
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++){
                if (j >= A[i-1]){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j - A[i-1]];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
                
            }
        }
        for (int i = m; i >= 0; i--){
            if (dp[n][i]){
                return i; 
            }
        }
        return 0;
    }
}

Method 2:
Method 1:滚动数组优化
Time complexity: O(n*m)
Space complexity: O(m)
State: f[i][S] “前i”个物品,取出一些能否组成和为S
Function: a[i-1] 是第i个物品下标是i-1 
  f[i][S] = f[i-1][S - a[i-1]] or f[i-1][S]
Intialize: f[i][0] = true; f[0][1..target] = false
Answer: 检查所有的f[n][j]

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        int n = A.length;
        boolean[][] dp = new boolean[2][m+1];
        for (int i = 0; i <= 1; i++){
            dp[i][0] = true;
        }
        for (int j = 1; j <= m; j++){
            dp[0][j] = false;
        }
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++){
                if (j >= A[i-1]){
                    dp[i%2][j] = dp[(i-1)%2][j] || dp[(i-1)%2][j - A[i-1]];
                }else{
                    dp[i%2][j] = dp[(i-1)%2][j];
                }
                
            }
        }
        for (int i = m; i >= 0; i--){
            if (dp[n%2][i]){
                return i; 
            }
        }
        return 0;
    }
}
