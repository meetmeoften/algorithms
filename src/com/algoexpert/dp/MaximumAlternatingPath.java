package com.algoexpert.dp;

import java.util.Arrays;

public class MaximumAlternatingPath {

	public long maxAlternatingSum(int[] nums) {
		int n = nums.length;

		long oddEven = 0;
		long evenOdd = 0;

		oddEven = nums[0];

		for (int i = 1; i < n; i++) {

			long noe= Math.max(oddEven, evenOdd + nums[i]);
			long neo= Math.max(evenOdd, oddEven - nums[i]);

			oddEven = noe;
			evenOdd = neo;
		}

		return Math.max(oddEven, evenOdd);
	}

	// -------------------------------------------------

	public long maxAlternatingSum2(int[] nums) {
		int n = nums.length;
		long dp[][] = new long[n][2];
		dp[0][0] = nums[0];

		for(int i=1;i<n;i++){
			dp[i][0] = Math.max(dp[i-1][0] , Math.max(dp[i-1][1]+nums[i] , nums[i]));
			dp[i][1] = Math.max(dp[i-1][1] , Math.max(dp[i-1][0]-nums[i] , 0));
		}
		return Math.max(dp[n-1][0] , dp[n-1][1]);
	}

	// -------------------------------------------------

	long[][] dp;

	public long maxAlternatingSum3(int[] nums) {
		int n = nums.length;
		dp = new long[n][2];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], -1);
		}

		return helper(nums, 0, 0);
	}

	public long helper(int[] nums, int p, int idx) {
		if (idx >= nums.length) {
			return 0;
		}

		if (dp[idx][p] != -1) {
			return dp[idx][p];
		}

		long max = 0;
		if (p % 2 == 0) {
			max = helper(nums, 1, idx + 1) + nums[idx];
		} else {
			max = helper(nums, 0, idx + 1) - nums[idx];
		}

		max = Math.max(max, helper(nums, p, idx + 1));
		dp[idx][p] = max;
		return dp[idx][p];
	}

	public static void main(String[] args) {
		int[] nums = { 4, 2, 5, 3 };
		MaximumAlternatingPath path = new MaximumAlternatingPath();
		path.maxAlternatingSum(nums);
		path.maxAlternatingSum2(nums);
		path.maxAlternatingSum3(nums);

	}

}
