You are installing a billboard and want it to have the largest height.  The billboard will have two steel supports, one on each side.  Each steel support must be an equal height.

You have a collection of rods which can be welded together.  For example, if you have rods of lengths 1, 2, and 3, you can weld them together to make a support of length 6.

Return the largest possible height of your billboard installation.  If you cannot support the billboard, return 0.

 

Example 1:

Input: [1,2,3,6]
Output: 6
Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.
Example 2:

Input: [1,2,3,4,5,6]
Output: 10
Explanation: We have two disjoint subsets {2,3,5} and {4,6}, which have the same sum = 10.
Example 3:

Input: [1,2]
Output: 0
Explanation: The billboard cannot be supported, so we return 0.
 

Note:

0 <= rods.length <= 20
1 <= rods[i] <= 1000
The sum of rods is at most 5000.

https://leetcode.com/problems/tallest-billboard/discuss/203756/short-java-solution-with-some-explanation!

Time complexity: O(N*sum)
dp's key represents the sum of left billboard - the sum of right billboard, the value represents the largest sum of left billboard
dp
class Solution {
    public int tallestBillboard(int[] rods) {
        if (rods == null || rods.length == 0){
            return 0;
        }
        Map<Integer, Integer> curr = new HashMap<>();//key: the sum of left - the sum of right; value: the largest sum of left
        curr.put(0, 0);
        for (int rod : rods){
            Map<Integer, Integer> prev = new HashMap<>(curr);
            for (int diff : prev.keySet()){
                curr.put(diff + rod, Math.max(curr.getOrDefault(diff + rod, 0), prev.get(diff) + rod));
                curr.put(diff - rod, Math.max(curr.getOrDefault(diff - rod, 0), prev.get(diff)));
            }
        }
        return curr.get(0);
    }
}





Below is not working
class Solution {
    public int tallestBillboard(int[] rods) {
        int n = rods.length;
        if (n == 0){
            return 0;
        }
        int sum = 0;
        for (int i : rods){
            sum += i;
        }
        sum = 10000;
        boolean[][] dp = new boolean[n+1][sum+1];//dp denotes the first i items can reach up to sum or not
        int[][] res = new int[n+1][sum+1]; //res denotes the the max height if dp[i][j] = true, otherwise -1
        for (int i = 0; i <= n; i++){
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= sum; j++){
                if (j >= rods[i-1] && dp[i-1][j-rods[i-1]]){//can take the i-th item and will take it to compare with existing res[i][j]
                    dp[i][j] = true;
                    res[i][j] = Math.max(res[i][j], res[i-1][j-rods[i-1]] + rods[i-1]);
                }
                if (j + rods[i-1] <= sum && dp[i-1][j+rods[i-1]]){
                    dp[i][j] = true;
                    res[i][j] = Math.max(res[i][j], res[i-1][j+rods[i-1]]);
                }
                if (dp[i-1][j]){//will not take i-th item no matter can or can't
                    dp[i][j] = true;
                    res[i][j] = Math.max(res[i][j], res[i-1][j]);
                }
            }
        }
        return res[n][sum/2];
    }
}
