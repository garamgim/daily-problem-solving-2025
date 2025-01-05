/*
 * LeetCode Easy 121 Best Time to Buy and Sell Stock
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 * */

public class L121 {
    class Solution {
        public int maxProfit(int[] prices) {
            int maxVal = 0;
            int currMin = -1;
            for (int i = 0; i < prices.length; i++) {
                if (currMin < prices[i] && currMin != -1) {
                    maxVal = Math.max(maxVal, prices[i] - currMin);
                } else {
                    currMin = prices[i];
                }
            }
            return maxVal;
        }
    }
}
