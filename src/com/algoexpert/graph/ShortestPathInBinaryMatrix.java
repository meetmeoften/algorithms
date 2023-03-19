package com.algoexpert.graph;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {

	public int[] ROWS = { 0, -1, -1, -1, 0, 1, 1, 1 };
	public int[] COLUMNS = { -1, -1, 0, 1, 1, 1, 0, -1 };

	public int shortestPathBinaryMatrix(int[][] grid) {
		int[][] visited = new int[grid.length][grid.length];
		int m = grid.length, n = grid[0].length;
		if (grid[0][0] == 1) {
			return -1;
		}
		if (grid[m - 1][n - 1] == 1) {
			return -1;
		}
		if (m - 1 == 0 && n - 1 == 0) {
			return 1;
		}
		int pathLength = 1;
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { 0, 0 });
		visited[0][0] = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int[] curr = queue.poll();
				for (int i = 0; i < 8; i++) {
					int row = curr[0] + ROWS[i];
					int col = curr[1] + COLUMNS[i];
					if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 0
							&& visited[row][col] == 0) {
						if (row == m - 1 && col == n - 1) {
							pathLength++;
							return pathLength;
						}
						queue.offer(new int[] { row, col });
						visited[row][col] = 1;
					}
				}
			}
			pathLength++;
		}

		return -1;
	}

	// ---------------------------

	class Pair {
		int row;
		int col;
		int level;

		Pair(int row, int col, int level) {
			this.row = row;
			this.col = col;
			this.level = level;
		}
	}

	public int shortestPathBinaryMatrix2(int[][] grid) {
		boolean[][] visited = new boolean[grid.length][grid.length];
		int[][] dirs = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

		Queue<Pair> Q = new ArrayDeque<>();
		if (grid[0][0] == 0) {
			Q.add(new Pair(0, 0, 1));
		}

		int shortestPath = Integer.MAX_VALUE;
		while (!Q.isEmpty()) {
			Pair remove = Q.remove();
			int row = remove.row;
			int col = remove.col;
			int level = remove.level;
			if (row == grid.length - 1 && col == grid.length - 1) {
				shortestPath = Math.min(shortestPath, level);
				continue;
			}

			for (int[] dir : dirs) {
				int newRow = row + dir[0];
				int newCol = col + dir[1];
				if (newRow >= 0 && newCol >= 0 && newRow < grid.length && newCol < grid.length
						&& !visited[newRow][newCol] && grid[newRow][newCol] == 0) {
					Q.add(new Pair(newRow, newCol, level + 1));
					visited[newRow][newCol] = true;
				}
			}
		}
		return (shortestPath == Integer.MAX_VALUE) ? -1 : shortestPath;
	}

	public static void main(String[] args) {
		int[][] grid = { { 0, 1 }, { 1, 0 } };
		ShortestPathInBinaryMatrix matrix = new ShortestPathInBinaryMatrix();
		matrix.shortestPathBinaryMatrix2(grid);
	}

}
