package com.algoexpert.buysell;

public class BuySellWithCoolDown {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length <= 1) {
			return 0;
		}

		int b0 = -prices[0], b1 = b0;
		int s0 = 0, s1 = 0, s2 = 0;

		for (int i = 1; i < prices.length; i++) {
			b0 = Math.max(b1, s2 - prices[i]);
			s0 = Math.max(s1, b1 + prices[i]);
			b1 = b0;
			s2 = s1;
			s1 = s0;
		}
		return s0;
	}

	public static int maxProfit2(int[] prices) {
		int sold = 0, rest = 0, hold = Integer.MIN_VALUE;

		for (int i = 0; i < prices.length; i++) {
			int prevSold = sold;

			sold = hold + prices[i];
			hold = Math.max(hold, rest - prices[i]);
			rest = Math.max(rest, prevSold);
		}

		return Math.max(sold, rest);
	}

	public static void main(String[] args) {
		maxProfit2(new int[] { 1, 2, 3, 0, 2 });
	}

}
