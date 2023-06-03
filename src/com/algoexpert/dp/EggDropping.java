package com.algoexpert.dp;

import java.util.Arrays;

public class EggDropping {

	// public static int eggDrop(int n, int k) {
	// // Your code here
	// int[][] dp = new int[k + 1][n + 1];
	// for (int i = 1; i <= n; ++i) {
	// dp[0][i] = 0;
	// dp[1][i] = 1;
	// }
	// for (int i = 1; i <= k; ++i) {
	// dp[i][1] = i;
	// }
	// for (int i = 2; i <= k; ++i) {
	// for (int j = 2; j <= n; ++j) {
	// dp[i][j] = Integer.MAX_VALUE;
	// for (int x = 1; x <= i; ++x) {
	// dp[i][j] = Math.min(dp[i][j], 1 + Math.max(dp[x - 1][j - 1], dp[i - x][j]));
	// }
	// }
	// }
	// return dp[k][n];
	// }

	public static int eggDrop(int eggs, int floors) {

		int T[][] = new int[eggs + 1][floors + 1];
		int c = 0;
		for (int i = 0; i <= floors; i++) {
			T[1][i] = i;
		}

		for (int e = 2; e <= eggs; e++) {
			for (int f = 1; f <= floors; f++) {
				T[e][f] = Integer.MAX_VALUE;
				for (int k = 1; k <= f; k++) {
					c = 1 + Math.max(T[e - 1][k - 1], T[e][f - k]);
					if (c < T[e][f]) {
						T[e][f] = c;
					}
				}
			}
		}
		return T[eggs][floors];
	}

	public static int superEggDrop(int K, int N) {
		int[][] dp = new int[N + 1][K + 1];
		int m = 0;
		while (dp[m][K] < N) {
			++m;
			for (int k = 1; k <= K; ++k) {
				dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1;
			}
		}
		return m;

	}

	public int f(int eggs, int floors, int[][] memo) {
		if (floors == 0 || floors == 1) {
			return floors;
		}
		if (eggs == 1) {
			return floors;
		}

		int ans = Integer.MAX_VALUE;
		if (memo[eggs][floors] != -1) {
			return memo[eggs][floors];
		}

		for (int i = 1; i <= floors; i++) {
			int min = Math.max(f(eggs - 1, i - 1, memo), f(eggs, floors - i, memo));
			if (min < ans) {
				ans = min;
			}
		}

		return memo[eggs][floors] = ans + 1;
	}

	public int twoEggDrop(int n) {
		int floors = n;
		int eggs = 2;
		int[][] memo = new int[eggs + 1][floors + 1];
		for (int[] arr : memo) {
			Arrays.fill(arr, -1);
		}
		return f(eggs, floors, memo);
	}

	public static void main(String[] args) {
		int k = 1;
		int n = 2;
		int result = eggDrop(k, n);
		superEggDrop(k, n);
		System.out.println(result);
	}

}
