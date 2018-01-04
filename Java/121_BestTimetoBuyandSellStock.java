Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.

Method 1: brute force
Time complexity: O(n^2)
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int[] f = new int[prices.length];
        for (int i = 0; i < prices.length; i++){
            for (int j = 0; j < i; j++){
                f[i] = Math.max(f[i], prices[i] - prices[j]);
            }
        }
        for (int i = 0; i < prices.length; i++){
            max = Math.max(max, f[i]);
        }
        return max;
    }
}

Method 2: dynamic programming
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0){
            return 0;
        }
        int max = 0;
        int min = prices[0];
        for (int i = 0; i < prices.length; i++){
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }
}

Why DP?
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/discuss/39112

