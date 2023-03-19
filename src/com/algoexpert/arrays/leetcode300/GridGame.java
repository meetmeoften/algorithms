package com.algoexpert.arrays.leetcode300;

import java.util.Arrays;

public class GridGame {

	public static long gridGame(int[][] g) {
		long top = Arrays.stream(g[0]).asLongStream().sum();
		long bottom = 0;
		long res = Long.MAX_VALUE;

		for (int i = 0; i < g[0].length; ++i) {
			top -= g[0][i];
			res = Math.min(res, Math.max(top, bottom));
			bottom += g[1][i];
		}
		return res;
	}

	public static long gridGame2(int[][] grid) {

		if (grid[0].length == 1) {
			return 0;
		}

		long[] postfix = new long[grid[0].length];
		long[] prefix = new long[grid[1].length];

		postfix[grid[0].length - 1] = grid[0][grid[0].length - 1];

		for (int i = grid[0].length - 2; i >= 0; i--) {
			postfix[i] = postfix[i + 1] + grid[0][i];
		}

		prefix[0] = grid[1][0];

		for (int i = 1; i < grid[0].length; i++) {
			prefix[i] = prefix[i - 1] + grid[1][i];
		}

		int index = 0;

		for (int i = 0; i < grid[0].length; i++) {
			if (i == grid[0].length - 1) {
				index = i;
				break;
			}
			if (postfix[i + 1] < prefix[i]) {
				index = i;
				break;
			}
		}

		if (index == grid[0].length - 1) {
			return prefix[index - 1];
		}

		if (index == 0) {
			return postfix[index + 1];
		}

		return Math.max(postfix[index + 1], prefix[index - 1]);
	}

	public static void main(String[] args) {
		int[][] grid = {{2,5,4},{1,5,1}};
		gridGame(grid);
		gridGame2(grid);

	}

}
