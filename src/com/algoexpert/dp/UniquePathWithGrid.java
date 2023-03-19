package com.algoexpert.dp;

public class UniquePathWithGrid {

	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;

		int[][] ways = new int[m + 1][n + 1];

		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (obstacleGrid[i - 1][j - 1] == 1) {
					ways[i][j] = 0;
				} else if (i == 1 || j == 1) {
					ways[i][j] = 1;
					System.out.println("EQUAL " + ways[i][j]);
				} else {
					ways[i][j] = ways[i - 1][j] + ways[i][j - 1];
					System.out.println("NOT EQUAL " + ways[i][j]);
				}
			}
		}
		return ways[m][n];

	}

	public static int uniquePathsWithObstacles2(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] dp = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					dp[i][j] = 0;
					continue;
				}
				if (i == 0 && j == 0) {
					dp[i][j] = 1;
					continue;
				}

				int up = 0;
				int left = 0;
				if (i > 0) {
					up = dp[i - 1][j];
				}
				if (j > 0) {
					left = dp[i][j - 1];
				}
				dp[i][j] = up + left;
			}
		}
		return dp[m - 1][n - 1];

	}

	public static void main(String[] args) {
		int[][] obstacleGrid = { { 0, 0 }, { 1, 1 }, { 0, 0 } };
		// uniquePathsWithObstacles(obstacleGrid);
		uniquePathsWithObstacles2(obstacleGrid);
	}

}
