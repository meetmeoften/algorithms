package com.algoexpert.dp;

public class MinimumCostForTickets {

	public static int mincostTickets(int[] days, int[] costs) {
		int n = days[days.length - 1];

		boolean[] travelDay = new boolean[n + 1];
		for (int day : days) {
			travelDay[day] = true;
		}

		int[] dp = new int[n + 1];
		dp[0] = 0;

		for (int i = 1; i <= n; i++) {
			if (!travelDay[i]) {
				dp[i] = dp[i - 1];
				continue;
			}

			int minCost = dp[i - 1] + costs[0];
			minCost = Math.min(minCost, dp[Math.max(0, i - 7)] + costs[1]);
			minCost = Math.min(minCost, dp[Math.max(0, i - 30)] + costs[2]);
			dp[i] = minCost;
		}

		return dp[n];
	}

	// ------------

	public int mincostTickets2(int[] days, int[] costs) {
		int dp[] = new int[days.length + 1];
		return check(days, costs, 0, dp);
	}

	public static int check(int d[], int c[], int day, int dp[]) {
		if (day >= d.length) {
			return 0;
		}
		if (dp[day] != 0) {
			return dp[day];
		}
		int d1 = check(d, c, day + 1, dp) + c[0];
		int i;
		for (i = day; i < d.length; i++) {
			if (d[i] >= d[day] + 7) {
				break;
			}
		}
		int d7 = check(d, c, i, dp) + c[1];
		for (i = day; i < d.length; i++) {
			if (d[i] >= d[day] + 30) {
				break;
			}
		}
		int d30 = check(d, c, i, dp) + c[2];
		return dp[day] = Math.min(d1, Math.min(d7, d30));
	}

	public static void main(String[] args) {
		//int[] days = { 1, 4, 6, 7, 8, 20 }, costs = { 2, 7, 15 };
		int[] days = { 1, 4, 9 }, costs = { 2, 3, 15 };
		mincostTickets(days, costs);

	}

}
