package com.leetcodepatterns.dp;

public class EggDropping {

	static int eggDrop(int n, int k) {
		int[][] dp = new int[k + 1][n + 1];
		for (int i = 1; i <= n; ++i) {
			dp[0][i] = 0;
			dp[1][i] = 1;
		}
		for (int i = 1; i <= k; ++i) {
			dp[i][1] = i;
		}
		for (int i = 2; i <= k; ++i) {
			for (int j = 2; j <= n; ++j) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int x = 1; x <= i; ++x) {
					dp[i][j] = Math.min(dp[i][j], 1 + Math.max(dp[x - 1][j - 1], dp[i - x][j]));
				}
			}
		}
		for (int j = 0; j <= n; ++j) {
			for (int i = 0; i <= k; ++i) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println("");
		}
		return dp[k][n];
	}


	public static void main(String[] args) {
		eggDrop(2, 5);
	}

}
