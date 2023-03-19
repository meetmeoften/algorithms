package com.algoexpert.dp;

public class MaximalSquare {

	public static int maximalSquare(char[][] matrix) {
		int[][] dp = new int[matrix.length][matrix[0].length];

		int max = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if ((i == 0 || j == 0) && matrix[i][j] == '1') {
					dp[i][j] = 1;
					max = Math.max(max, dp[i][j]);
				} else if (matrix[i][j] == '0') {
					dp[i][j] = 0;
				} else if (matrix[i][j] == '1') {
					dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
					max = Math.max(max, dp[i][j]);
				}
			}
		}

		return max * max;

	}

	public static void main(String[] args) {

		char[][] edges = new char[][] { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' },
			{ '1', '1', '1', '1', '1' }, { '1', '0', '0', '1', '0' } };
			maximalSquare(edges);
	}

}
