Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. 
You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

    
Best solution:
https://leetcode.com/problems/burst-balloons/discuss/194608/DP-solution-with-detailed-text-and-video-explanation.
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n+2];
        for (int i = 1; i <= n; i++){
            arr[i] = nums[i-1];
        }
        arr[0] = 1;
        arr[n+1] = 1;
        int[][] dp = new int[n+2][n+2];
        for (int len = 0; len <= n; len++){
            for (int i = 1; i + len <= n; i++){
                int j = i + len;
                for (int k = i; k <= j; k++){
                    dp[i][j] = Math.max(dp[i][j], dp[i][k-1] + dp[k+1][j] + arr[k] * arr[i-1] * arr[j+1]);
                }
            }
        }
        return dp[1][n];
    }
}


Method:
区间DP
O(n^2)
http://www.jiuzhang.com/solutions/burst-ballons/
class Solution {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int[] arr = new int[n+2];
        for (int i = 1; i <= n; i++){
            arr[i] = nums[i-1];
        }
        arr[0] = 1;
        arr[n+1] = 1;
        int[][] dp = new int[n+2][n+2];
        int[][] visit = new int[n+2][n+2];
        return memorySearch(dp, visit, arr, 1, n);
    }
    private int memorySearch(int[][] dp, int[][] visit, int[] arr, int l, int r){
        if (visit[l][r] == 1){
            return dp[l][r];
        }
        int ans = 0;
        for (int k = l; k <= r; k++){
            int midValue = arr[l-1] * arr[k] * arr[r+1];
            int left = memorySearch(dp, visit, arr, l, k - 1);
            int right = memorySearch(dp, visit, arr, k + 1, r);
            ans = Math.max(ans, left + right + midValue);
        }
        visit[l][r] = 1;
        dp[l][r] = ans;
        return ans;
    }
}


https://leetcode.com/problems/burst-balloons/discuss/76228/Share-some-analysis-and-explanations

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n+2];
        for (int i = 1; i <= n; i++){
            arr[i] = nums[i-1];
        }
        arr[0] = 1;
        arr[n+1] = 1;
        Map<Integer, Integer> map = new HashMap<>();
        return burst(arr, 1, n, map);
    }
    private int burst(int[] arr, int left, int right, Map<Integer, Integer> map){
        if (left > right){
            return 0;
        }
        int N = arr.length;
        int key = left * N + right;
        if (map.containsKey(key)){
            return map.get(key);
        }
        int res = 0;
        for (int i = left; i <= right; i++){
            int lastBurst = arr[left-1] * arr[i] * arr[right+1];
            int leftBurst = burst(arr, left, i - 1, map);
            int rightBurst = burst(arr, i + 1, right, map);
            res = Math.max(res, lastBurst + leftBurst + rightBurst);
        }
        map.put(key, res);
        return res;
    }
}
