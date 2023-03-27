package com.algoexpert.buysell;

import java.util.HashMap;
import java.util.Map;

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

	public static int maxProfit3(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}

		int n = prices.length;
		int[] M = new int[n];
		int maxDiff = Integer.MIN_VALUE; // M[j - 2] - prices[j]
		for (int i = 0; i < n; i++) {
			if (i < 2) {
				maxDiff = Math.max(maxDiff, -prices[i]);
			}
			if (i == 0) {
				M[0] = 0;
			} else if (i == 1) {
				M[1] = Math.max(prices[1] - prices[0], 0);
			} else {
				M[i] = Math.max(M[i - 1], maxDiff + prices[i]);
				maxDiff = Math.max(maxDiff, M[i - 1] - prices[i]);
			}
		}

		return M[n - 1];
	}

	public static int maxProfit4(int[] prices) {
		Map<String, Integer> cache = new HashMap<>();
		return dfs(prices, cache, 0, true);
	}

	public static int dfs(int[] prices, Map<String, Integer> cache, int index, boolean buying) {
		if (index >= prices.length) {
			return 0;
		}
		String key = index + "-" + buying;

		if (cache.containsKey(key)) {
			return cache.get(key);
		}

		int cooldown = dfs(prices, cache, index + 1, buying);
		int buyOsell = Integer.MIN_VALUE;

		if (buying) {
			buyOsell = dfs(prices, cache, index + 1, !buying) - prices[index];
		} else {
			buyOsell = dfs(prices, cache, index + 2, !buying) + prices[index];
		}

		cache.put(key, Math.max(buyOsell, cooldown));
		return cache.get(key);
	}

	public static void main(String[] args) {
		maxProfit2(new int[] { 1, 2, 3, 0, 2 });
		maxProfit3(new int[] { 1, 2, 3, 0, 2 });
	}

}
