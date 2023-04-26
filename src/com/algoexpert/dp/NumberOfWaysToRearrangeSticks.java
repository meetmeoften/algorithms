package com.algoexpert.dp;

public class NumberOfWaysToRearrangeSticks {

	int mod = (int) (Math.pow(10, 9) + 7);

	public int rearrangeSticks(int n, int k) {
		int[][] map = new int[n + 1][k + 1];

		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < k + 1; j++) {
				map[i][j] = -1;
			}
		}
		return dfs(n, k, map);
	}

	public int dfs(int n, int k, int[][] dp) {
		if (k > n || k == 0) {
			return 0;
		}
		if (n <= 2) {
			return 1; // if there are 1 and 2
			// remaining there is 1 way to keep them
		}

		if (dp[n][k] != -1) {
			return dp[n][k];
		}

		int ans = 0;

		// IF we take this value then there is 1 less
		// and since we took big stick
		// the k-1 goes biggest stick
		ans = (ans + dfs(n - 1, k - 1, dp)) % mod;
		// If we don't take the biggest stick the ways are n-1
		ans = (ans + (n - 1) * dfs(n - 1, k, dp)) % mod;

		return dp[n][k] = ans;
	}


	public int rearrangeSticks2(int n, int k) {
		final int MOD = (int) (Math.pow(10, 9) + 7);
		long[][] M = new long[k + 1][n + 1];
		M[0][0] = 1;
		for (int i = 1; i <= k; i++) {
			for (int j = 1; j <= n; j++) {
				M[i][j] = ((j - 1) * M[i][j - 1] % MOD + M[i - 1][j - 1]) % MOD;
			}
		}
		return (int) M[k][n];
	}


	public static void main(String[] args) {
		NumberOfWaysToRearrangeSticks re = new NumberOfWaysToRearrangeSticks();
		int n = 3, k = 2;
		//		re.rearrangeSticks(n , k);
		re.rearrangeSticks2(n , k);
	}

}
