package com.algoexpert.buysell;

public class BuySell2 {

	public static int maxProfit1(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}

		int minPrices = prices[0], profit = 0;
		for (int i = 1; i < prices.length; i++) {
			int cost = prices[i] - minPrices;
			profit = Math.max(profit, cost);
			minPrices = Math.min(minPrices, prices[i]);

		}

		return profit;
	}

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int i = 0;
		int peak = prices[0];
		int valley = prices[0];
		int maxProfit = 0;
		int len = prices.length;
		while (i < len - 1) {
			while (i < len - 1 && prices[i] >= prices[i + 1]) {// down
				i++;
			}
			valley = prices[i];
			while (i < len - 1 && prices[i] <= prices[i + 1]) {// up
				i++;
			}
			peak = prices[i];
			maxProfit += peak - valley;
		}
		return maxProfit;
	}

	public int maxProfit2(int[] prices) {
		int profit = 0;
		for (int i = prices.length - 1; i > 0; i--) {
			if (prices[i] > prices[i - 1]) {
				profit += (prices[i] - prices[i - 1]);
			}
		}
		return profit;
	}

	public static void main(String[] args) {
		maxProfit(new int[] { 7, 2, 1, 5, 3, 6, 4 });
	}

}
