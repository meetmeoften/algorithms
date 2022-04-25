package com.algoexpert.graph.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

	public static final int ALLOWED_DIRECTIONS = 4;
	public static final int xmoves[] = { 0, 0, -1, 1 };
	public static final int ymoves[] = { -1, 1, 0, 0 };

	private static boolean isValid(int[][] grid, int i, int j) {
		return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length;
	}

	public int orangesRotting(int[][] grid) {

		Queue<int[]> q = new LinkedList<>();
		int count = -1;
		int noOfOnes = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 2) {
					q.add(new int[] { i, j });
				}
				if (grid[i][j] == 1) {
					noOfOnes++;
				}
			}
		}

		if (noOfOnes == 0) {
			return 0;
		}

		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				int coord[] = q.poll();

				for (int k = 0; k < ALLOWED_DIRECTIONS; k++) {
					int x = xmoves[k] + coord[0];
					int y = ymoves[k] + coord[1];

					if (isValid(grid, x, y) && grid[x][y] == 1) {
						grid[x][y] = 2;

						q.add(new int[] { x, y });
					}
				}
			}
			count++;
		}

		for (int row[] : grid) {
			for (int cell : row) {
				if (cell == 1) {
					return -1;
				}
			}
		}
		return count;

	}

}
