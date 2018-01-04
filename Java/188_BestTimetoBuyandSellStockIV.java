Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).


class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k >= prices.length / 2) { // if k >= n/2, then you can make maximum number of transactions
            int profit = 0;
            for (int i = 1; i < prices.length; i++)
                if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
            return profit;
        }
        
        int[] maxProfit = new int[k+1]; 
        int[] minCost = new int[k+1];
        for (int i = 0; i <= k ; i++){
            minCost[i] = Integer.MAX_VALUE;
        }
        
        for (int i = 0; i < prices.length; i++){
            for (int j = 1; j <= k; j++){
                 maxProfit[j] = Math.max(maxProfit[j], prices[i] - minCost[j]);
                 minCost[j] = Math.min(minCost[j], prices[i] - maxProfit[j-1]);
            }
        }
        return maxProfit[k];
    }
}

https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/discuss/54125/
