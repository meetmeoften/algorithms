package com.algoexpert.dp;

import java.util.Arrays;

public class DistinctSubsequences {

	public static int numDistinct(String s, String t) {
		int n = s.length() + 1;
		int m = t.length() + 1;
		int[][] memo = new int[n][m];

		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}

		return recursion(s, t, 0, 0, memo);
	}

	public static int recursion(String s, String t, int sIdx, int tIdx, int[][] memo) {
		if (memo[sIdx][tIdx] != -1) {
			return memo[sIdx][tIdx];
		}

		if (tIdx >= t.length()) {
			return 1;
		}

		if (sIdx >= s.length()) {
			return 0;
		}

		if (t.charAt(tIdx) == s.charAt(sIdx)) {
			memo[sIdx][tIdx] = recursion(s, t, sIdx + 1, tIdx + 1, memo) + recursion(s, t, sIdx + 1, tIdx, memo);
			return memo[sIdx][tIdx];
		}

		memo[sIdx][tIdx] = recursion(s, t, sIdx + 1, tIdx, memo);
		return memo[sIdx][tIdx];
	}

	// --------------------

	public static int numDistinct2(String s, String t) {
		int N = s.length();
		int M = t.length();
		int[][] dp = new int[N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				dp[i][j] = 0;
			}
		} // init dp
		for (int j = 1; j <= M; j++) {
			dp[0][j] = 0;
		} // base case
		for (int i = 0; i <= N; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {// IF Match
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];// take + notTake
				} else {
					dp[i][j] = dp[i - 1][j];// Not Match
				}
			}
		} // for closes

		return dp[N][M];
	}

	public static void main(String[] args) {
		numDistinct("rabbbit", "rabbit");
	}

}
