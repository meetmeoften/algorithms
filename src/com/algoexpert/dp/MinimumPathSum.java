package com.algoexpert.dp;

import java.util.Arrays;

public class MinimumPathSum {

	public int minPathSum(int[][] grid) {
		if (grid.length == 0) {
			return 0;
		}
		int row = grid.length;
		int col = grid[0].length;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				int leftSum = (j > 0) ? grid[i][j - 1] : Integer.MAX_VALUE;
				int topSum = (i > 0) ? grid[i - 1][j] : Integer.MAX_VALUE;
				if (i == 0 && j == 0) {
					continue;
				}

				grid[i][j] += Math.min(leftSum, topSum);
			}
		}
		return grid[row - 1][col - 1];
	}

	static int[][] dp;

	public int minPathSum2(int[][] arr) {
		int m = arr.length;
		int n = arr[0].length;
		dp = new int[m][n];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		return f(m - 1, n - 1, arr);
	}

	public int f(int i, int j, int arr[][]) {
		if (i == 0 && j == 0) {
			return arr[0][0];
		}
		if (i < 0 || j < 0) {
			return 999;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		int up = arr[i][j] + f(i - 1, j, arr);
		int left = arr[i][j] + f(i, j - 1, arr);
		return dp[i][j] = Math.min(up, left);
	}

	// --------------------------

	public int minPathSum3(int[][] arr) {
		int m = arr.length;
		int n = arr[0].length;
		dp = new int[m][n];
		dp[0][0] = arr[0][0];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = arr[i][j];
				} else {
					int up = 0, left = 0;
					if (i > 0) {
						up = arr[i][j] + dp[i - 1][j];
					} else {
						up = arr[i][j] + 999;
					}
					if (j > 0) {
						left = arr[i][j] + dp[i][j - 1];
					} else {
						left = arr[i][j] + 999;
					}
					dp[i][j] = Math.min(up, left);
				}
			}
		}
		return dp[m - 1][n - 1];

	}
}
