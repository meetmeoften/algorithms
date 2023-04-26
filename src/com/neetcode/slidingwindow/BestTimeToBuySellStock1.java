package com.neetcode.slidingwindow;

public class BestTimeToBuySellStock1 {


	public int maxProfit(int[] prices) {
		if(prices == null || prices.length == 0) {
			return 0;
		}

		int minPrice = prices[0];
		int max = 0;
		for(int i= 1; i < prices.length; i++) {
			int diff = prices[i] - minPrice;
			max = Math.max(max, diff);
			minPrice = Math.min(minPrice, prices[i]);
		}


		return max;
	}

}
