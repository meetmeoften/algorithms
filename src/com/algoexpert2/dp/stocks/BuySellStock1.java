package com.algoexpert2.dp.stocks;

import java.util.Arrays;

public class BuySellStock1 {

	static int maximumProfit(int[] arr) {
		// Write your code here.

		int maxProfit = 0;
		int minValue = arr[0];

		for (int i = 1; i < arr.length; i++) {
			int currProfit = arr[i] - minValue;
			maxProfit = Math.max(maxProfit, currProfit);
			minValue = Math.min(minValue, arr[i]);
		}

		return maxProfit;

	}

	public static int helper(int[] arr, int n, int i, int buy, int[][] dp) {
		if(i >= n) {
			return 0;
		}

		if(dp[n][buy] != -1) {
			return dp[n][buy];
		}
		int profit = 0;
		if(buy == 0) {
			profit = Math.max(helper(arr, n, i+1, 0, dp) , -arr[i] + helper(arr, n, i+1, 1, dp));
		}

		if(buy == 1) {
			profit = Math.max(helper(arr, n, i+1, 1, dp) , arr[i] + helper(arr, n, i+1, 0, dp));
		}

		return profit;
	}

	public static void buySell2(int[] arr) {
		int n = arr.length;
		int k = 2;
		int[][] dp = new int[n][k+1];

		for(int[] row: dp) {
			Arrays.fill(row, -1);
		}
		System.out.println(helper(arr, n, 0, 0, dp));


	}

	public static void main(String args[]) {

		int[] Arr = { 7, 1, 5, 3, 6, 4 };
		System.out.println("The maximum profit by selling the stock is " + maximumProfit(Arr));
		buySell2(Arr);
	}

}
