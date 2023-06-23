package com.algoexpert2.dp.coin;

public class CoinChangeFiniteSupply {

	/*
	 * Time Complexity : O(N * V * maxFreq) Space Complexity : O(N * V * maxFreq)
	 *
	 * where, N is the size of the vector 'coins', V is the required change and
	 * 'maxFreq' is the maximum supply of any coin.
	 */

	public static int coinChange(int n, int[] coins, int[] freq, int v) {

		int maxFreq = 1;
		for (int i = 0; i < n; i++) {
			maxFreq = Math.max(maxFreq, freq[i]);
		}

		// Initialize a dp array.
		int[][][] dp = new int[n][v + 1][maxFreq + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= v; j++) {
				for (int k = 0; k <= maxFreq; k++) {
					dp[i][j][k] = -1;
				}
			}
		}

		// Function call.
		return coinChangeHelper(coins, freq, v, freq[n - 1], n - 1, dp);
	}

	public static int coinChangeHelper(int[] coins, int[] freq, int v, int supply, int index, int[][][] dp) {

		// Base Case.
		if (v < 0 || supply < 0 || index < 0) {
			return 0;
		}

		// Check if we get the desired change.
		if (v == 0) {
			return 1;
		}

		// Check if the current position is already visited or not.
		if (dp[index][v][supply] != -1) {
			return dp[index][v][supply];
		}

		int answer = 0;

		// Check if we can take the current coin or not.
		if (v - coins[index] >= 0 && supply > 0) {
			answer = coinChangeHelper(coins, freq, v - coins[index], supply - 1, index, dp) % 1000000007;
		}

		// Go to the next coin.
		if (index > 0) {
			answer = (answer + coinChangeHelper(coins, freq, v, freq[index - 1], index - 1, dp)) % 1000000007;
		}

		// Update the dp array.
		dp[index][v][supply] = answer % 1000000007;

		return answer;
	}

	/**
	 * /* Time Complexity : O(2 ^ sumFreq) Space Complexity : O(1)
	 *
	 * Where, sumFreq is the sum of all the coins of each denomination.
	 */

	public static int coinChange2(int n, int[] coins, int[] freq, int target) {
		// Function call.
		return coinChangeHelper2(coins, freq, target, freq[n - 1], n - 1);
	}

	public static int coinChangeHelper2(int[] coins, int[] freq, int target, int supply, int index) {

		// Base Case.
		if (target < 0 || supply < 0 || index < 0) {
			return 0;
		}

		// Check if we get the desired change.
		if (target == 0) {
			return 1;
		}

		// Check if we can take the current coin or not.
		int answer = coinChangeHelper2(coins, freq, target - coins[index], supply - 1, index) % 1000000007;

		// Go to the next coin.
		if (index > 0) {
			answer = (answer + coinChangeHelper2(coins, freq, target, freq[index - 1], index - 1)) % 1000000007;
		}

		return (answer % 1000000007);
	}

	public static void main(String[] args) {
		int[] input = { 1, 5, 4 };
		int[] supply = {2, 1, 1 };

		System.out.println(coinChange2(3, input, supply, 7));
	}

}
