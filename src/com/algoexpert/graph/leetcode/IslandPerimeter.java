package com.algoexpert.graph.leetcode;

public class IslandPerimeter {

	public int islandPerimeter2(int[][] grid) {
		int islands = 0, neighbours = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					islands++; // count islands
					if (i < grid.length - 1 && grid[i + 1][j] == 1) {
						neighbours++; // count down neighbours
					}
					if (j < grid[i].length - 1 && grid[i][j + 1] == 1) {
						neighbours++; // count right neighbours
					}
				}
			}
		}
		return islands * 4 - neighbours * 2;

	}

	// -----------------------------------------------

	public static int islandPerimeter(int[][] grid) {
		if (grid == null) {
			return 0;
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					return getPerimeter(grid, i, j);
				}
			}
		}
		return 0;
	}

	public static int getPerimeter(int[][] grid, int i, int j) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
			return 1;
		}
		if (grid[i][j] == 0) {
			return 1;
		}
		if (grid[i][j] == -1) {
			return 0;
		}

		int count = 0;
		grid[i][j] = -1;

		count += getPerimeter(grid, i - 1, j);
		count += getPerimeter(grid, i, j - 1);
		count += getPerimeter(grid, i, j + 1);
		count += getPerimeter(grid, i + 1, j);

		return count;

	}

	public static void main(String[] args) {
		int[][] points = { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 } };
		islandPerimeter(points);
	}

}
