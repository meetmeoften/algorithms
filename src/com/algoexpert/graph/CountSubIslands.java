package com.algoexpert.graph;

import java.util.LinkedList;
import java.util.Queue;

public class CountSubIslands {

	public static int countSubIslands(int[][] grid1, int[][] grid2) {
		int row = grid1.length;
		int col = grid1[0].length;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid1[i][j] == 0) {
					dfs(grid2, i, j);
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid2[i][j] == 1) {
					ans++;
					dfs(grid2, i, j);
				}
			}
		}

		return ans;
	}

	private static void dfs(int[][] g2, int r, int c) {
		if (r < 0 || c < 0 || r >= g2.length || c >= g2[0].length || g2[r][c] == 0) {
			return;
		}
		g2[r][c] = 0;
		dfs(g2, r + 1, c);
		dfs(g2, r - 1, c);
		dfs(g2, r, c + 1);
		dfs(g2, r, c - 1);
	}

	// -----------------------------

	public static int countSubIslands2(int[][] grid1, int[][] grid2) {
		int m = grid1.length;
		int n = grid1[0].length;
		boolean[][] vis = new boolean[m][n];
		int count = 0;
		int[] dir = { 1, 0, -1, 0, 1 };

		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (grid2[i][j] == 0 || vis[i][j]) {
					continue;
				}

				Queue<int[]> queue = new LinkedList<>();
				boolean flag = true;
				vis[i][j] = true;

				queue.add(new int[] { i, j });

				while (!queue.isEmpty()) {
					int[] vtx = queue.remove();

					if (grid1[vtx[0]][vtx[1]] == 0) {
						flag = false;
					}

					for (int k = 0; k < 4; ++k) {
						int x = vtx[0] + dir[k];
						int y = vtx[1] + dir[k + 1];

						if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1 && !vis[x][y]) {
							vis[x][y] = true;

							queue.add(new int[] { x, y });
						}
					}
				}

				if (flag) {
					++count;
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 1, 1, 1, 0, 0 }, { 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0 },
				{ 1, 1, 0, 1, 1 } };
		int[][] grid2 = { { 1, 1, 1, 0, 0 }, { 0, 0, 1, 1, 1 }, { 0, 1, 0, 0, 0 }, { 1, 0, 1, 1, 0 },
				{ 0, 1, 0, 1, 0 } };

		countSubIslands(grid1, grid2);
	}

}
