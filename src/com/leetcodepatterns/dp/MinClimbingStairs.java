package com.leetcodepatterns.dp;

public class MinClimbingStairs {

	public static int minCostClimbingStairs(int[] cost) {
		int[] dp = new int[cost.length];

		dp[0] = cost[0];
		dp[1] = cost[1];

		for (int i = 2; i < cost.length; i++) {
			dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
		}

		return Math.min(dp[cost.length - 1], dp[cost.length - 2]);

	}

	public static void main(String[] args) {
		int[] input2 = new int[] {10,15,20};
		int[] input = new int[] {1,100,1,1,1,100,1,1,100,1};
		minCostClimbingStairs(input);
	}

}
