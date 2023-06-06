package com.algoexpert2.graph;

public class LargestIsland {

	public int largestIsland(int[][] matrix) {
		// Write your code here.
		int max = 0;
		boolean[][]  visited = new boolean [matrix.length][matrix[0].length];
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				if (matrix[row][col] == 1) {
					matrix[row][col] = 0;
					visited = new boolean [matrix.length][matrix[0].length];
					int value = dfs(matrix, row, col, visited);
					max = Math.max(max, value);
					matrix[row][col] = 1;
				}
			}
		}
		return max;
	}

	public int dfs(int[][] matrix, int row, int col, boolean[][] visited) {
		if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length || visited[row][col] == true || matrix[row][col] == 1) {
			return 0;
		}
		visited[row][col] = true;
		int value = 1 +
				dfs(matrix, row + 1, col, visited) +
				dfs(matrix, row - 1, col, visited) +
				dfs(matrix, row, col + 1, visited) +
				dfs(matrix, row, col - 1, visited);

		return value;

	}

	public static void main(String[] args) {
		var input =
				new int[][] {
			{0, 1, 1},
			{0, 0, 1},
			{1, 1, 0}
		};
		var expected = 5;
		var actual = new LargestIsland().largestIsland(input);
	}

}
