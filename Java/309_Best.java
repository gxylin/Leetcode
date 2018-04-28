Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like
(ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]

https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75931/Easiest-JAVA-solution-with-explanations

buy[i]: Max profit till index i. The series of transaction is ending with a buy.
sell[i]: Max profit till index i. The series of transaction is ending with a sell.
Till index i, the buy / sell action must happen and must be the last action. It may not happen at index i. 
    It may happen at i - 1, i - 2, ... 0.
In the end n - 1, return sell[n - 1]. Apparently we cannot finally end up with a buy. 
    In that case, we would rather take a rest at n - 1.
For special case no transaction at all, classify it as sell[i], so that in the end, we can still return sell[n - 1]. 


Method 1: DP
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2){
            return 0;
        }
        int n = prices.length;
        int[] buy = new int[n]; //maxProfit which transaction ends with buy
        int[] sell = new int[n]; //maxProfit which transaction ends with sell
        buy[0] = -prices[0];
        sell[0] = 0;
        buy[1] = Math.max(-prices[0], -prices[1]);
        sell[1] = Math.max(sell[0], prices[1] - prices[0]);
        for (int i = 2; i < n; i++){
            buy[i] = Math.max(buy[i-1], sell[i-2] - prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]);
        }
        return sell[n-1];
    }
}

Method 2: DP
Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2){
            return 0;
        }
        int n = prices.length;
        int b2 = -prices[0];
        int s2 = 0;
        int b1 = Math.max(-prices[0], -prices[1]);
        int s1 = Math.max(0, prices[1] - prices[0]);
        int buy = b1;
        int sell = s1;
        for (int i = 2; i < n; i++){
            buy = Math.max(b1, s2 - prices[i]);
            sell = Math.max(s1, b1 + prices[i]);
            b2 = b1;
            b1 = buy;
            s2 = s1;
            s1 = sell;
        }
        return sell;
    }
}
