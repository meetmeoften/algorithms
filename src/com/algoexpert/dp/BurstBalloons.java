package com.algoexpert.dp;

public class BurstBalloons {

	public static int maxCoins(int[] nums) {

		int n = nums.length;
		int arr[] = new int[n + 2];
		for (int i = 0; i < n; i++) {
			arr[i + 1] = nums[i];
		}
		arr[0] = 1;
		arr[n + 1] = 1;
		int dp[][] = new int[n + 2][n + 2];
		// dp[i][j] - storing the max coins u can earn in this interval/subarray

		for (int wlen = 1; wlen <= n; wlen++) {
			for (int left = 1; left <= n - wlen + 1; left++) {
				int right = left + wlen - 1;

				// now iterate this window, and give chance to every ballon to burst at last
				for (int k = left; k <= right; k++) {
					System.out.println("BEFORE " + left + " " + right + " " + dp[left][right]);
					dp[left][right] = Math.max(dp[left][right], dp[left][k - 1] + // left subarray

							dp[k + 1][right] + // right subarray

							(arr[k] * arr[left - 1] * arr[right + 1]) // bursting k index ballon at last , and intervals
							// side balloons will bust with last balloon
							);

					System.out.println("AFTER " + left + " " + right + " " + dp[left][right]);
				}
			}
		}

		return dp[1][n]; // returning answer stored for orginal size problem

	}

	// --------------

	public static int maxCoins2(int[] nums) {
		int n = nums.length;

		int[] newNums = new int[n + 2];
		newNums[0] = newNums[n + 1] = 1;

		for (int i = 0; i < n; i++) {
			newNums[i + 1] = nums[i];
		}

		int[][] memo = new int[n + 2][n + 2];
		int res =  helper(newNums, memo, 1, n);
		return res;
	}

	private static int helper(int[] nums, int[][] memo, int start, int end) {
		if (start > end) {
			return 0;
		}
		if (memo[start][end] != 0) {
			return memo[start][end];
		}

		for (int i = start; i <= end; i++) {
			int value = nums[start - 1] * nums[i] * nums[end + 1];
			System.out.println(value);
			value += helper(nums, memo, start, i - 1);
			value += helper(nums, memo, i + 1, end);
			System.out.println("START " + start +  " END " + end   +  " " + memo[start][end] +  " " + value);
			memo[start][end] = Math.max(memo[start][end], value);
		}

		return memo[start][end];
	}

	public static void main(String[] args) {
		//		int[] nums = { 3, 1, 5, 8 };
		int[] nums = { 3, 5 };
		maxCoins2(nums);
		//maxCoins(nums);
	}

}
