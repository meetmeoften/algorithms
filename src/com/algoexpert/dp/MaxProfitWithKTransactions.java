package com.algoexpert.dp;

public class MaxProfitWithKTransactions {

	public static int maxProfitWithKTransactions(int[] prices, int k) {
		// Write your code here
		if(prices.length ==0) {
			return 0;
		}
		int[][] profits = new int[k+1][prices.length];

		for(int t=1; t< k+1; t++) {
			int maxThusFar = Integer.MIN_VALUE;

			for(int d = 1; d < prices.length; d++) {
				maxThusFar = Math.max(maxThusFar, profits[t-1][d-1] - prices[d-1]);
				System.out.println(maxThusFar);
				profits[t][d] = Math.max(profits[t][d-1], maxThusFar + prices[d]);
				System.out.println(profits[t][d] );
			}
		}
		return profits[k][prices.length-1];
	}

	public static void main(String[] args) {
		int[] input = {5, 11, 3, 50, 60, 90};
		maxProfitWithKTransactions(input, 4);
	}

}
