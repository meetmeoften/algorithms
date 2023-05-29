package com.algoexpert.buysell;

import java.util.HashMap;

public class MaxProfitWithKTransactions {

	public static int maxProfitWithKTransactions(int[] prices, int k) {
		// Write your code here
		if (prices.length == 0) {
			return 0;
		}
		int[][] profits = new int[k + 1][prices.length];
		for (int t = 1; t < k + 1; t++) {

			int maxThusFar = Integer.MIN_VALUE;
			for (int d = 1; d < prices.length; d++) {
				maxThusFar = Math.max(maxThusFar, profits[t - 1][d - 1] - prices[d - 1]);

				// System.out.println(maxThusFar);

				profits[t][d] = Math.max(profits[t][d - 1], maxThusFar + prices[d]);

				// System.out.println(profits[t][d] );
			}
		}
		return profits[k][prices.length - 1];
	}

	public int maxProfit(int k, int[] prices) {
		HashMap<String, Integer> map = new HashMap<>();
		int result = getAns(prices, prices.length, 0, 0, k, map);
		return result;
	}

	static int getAns(int[] Arr, int n, int ind, int buy, int cap, HashMap<String, Integer> map) {
		if (ind >= n || cap == 0) {
			return 0; // base case
		}

		String key = ind + "|" + buy + "|" + cap;
		if (map.containsKey(key)) {
			return map.get(key);
		}

		int profit = 0;
		if (buy == 0) {// We can buy the stock
			profit = Math.max(0 + getAns(Arr, n, ind + 1, 0, cap, map),
					-Arr[ind] + getAns(Arr, n, ind + 1, 1, cap, map));
		}

		if (buy == 1) {// We can sell the stock
			profit = Math.max(0 + getAns(Arr, n, ind + 1, 1, cap, map),
					Arr[ind] + getAns(Arr, n, ind + 1, 0, cap - 1, map));
		}
		map.put(key, profit);
		return profit;
	}

	public static void main(String[] args) {
		int[] input = { 5, 11, 3, 50, 60, 90 };
		maxProfitWithKTransactions(input, 4);
	}

}
