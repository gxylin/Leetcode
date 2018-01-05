Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

class Solution {
    public int maxProfit(int[] prices) {
        int minCost1 = Integer.MAX_VALUE;
        int maxProfit1 = 0;
        int minCost2 = Integer.MAX_VALUE;
        int maxProfit2 = 0;
        for (int i = 0; i < prices.length; i++){
            minCost1 = Math.min(minCost1, prices[i]);
            maxProfit1 = Math.max(maxProfit1, prices[i] - minCost1);
            minCost2 = Math.min(minCost2, prices[i] - maxProfit1);
            maxProfit2 = Math.max(maxProfit2,  prices[i] - minCost2);  
        }
        return maxProfit2;
    }
}
