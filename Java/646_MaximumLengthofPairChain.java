You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. 
You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]
Note:
The number of given pairs will be in the range [1, 1000].

    
Similar as Longest Increasing Subsequence

class Solution {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0){
            return 0;
        }
        Arrays.sort(pairs, new Comparator<int[]>(){
           public int compare(int[] p1, int[] p2){
               return p1[0] - p2[0];
           }  
        });
        int[] f = new int[pairs.length];
        for (int i = 0; i < f.length; i++){
            f[i] = 1;
        }
        for (int i = 0; i < f.length; i++){
            for (int j = 0; j < i; j++){
                if (pairs[i][0] > pairs[j][1]){
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < f.length; i++){
            max = Math.max(max, f[i]);
        }
        return max;
    }
}

class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>(){
            public int compare (int[] p1, int[] p2){
                return p1[0] - p2[0];
            }
        });
        int n = pairs.length;
        int[] dp = new int[n];
        int max = 1;
        for (int i = 0; i < n; i++){
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (pairs[j][1] < pairs[i][0]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
