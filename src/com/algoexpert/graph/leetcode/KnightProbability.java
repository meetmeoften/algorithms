package com.algoexpert.graph.leetcode;

import java.util.HashMap;
import java.util.Map;

public class KnightProbability {

	Map<String, Double> map = new HashMap<>();
	int[][] moves = new int[][] { { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 } };

	public double knightProbability(int n, int k, int row, int column) {
		if (row < 0 || column < 0 || row >= n || column >= n) {
			return 0.0;
		}
		if (k == 0) {
			return 1.0;
		}
		String key = k + "|" + row + "|" + column;
		if (map.containsKey(key)) {
			return map.get(key);
		}
		double prob = 0;
		for (int[] move : moves) {
			prob += knightProbability(n, k - 1, row + move[0], column + move[1]) / 8d;
		}
		map.put(key, prob);
		return prob;
	}

	private int[][] dirs = new int[][] { { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 } };

	public double knightProbability2(int N, int K, int r, int c) {
		double[][][] dp = new double[K + 1][N][N];
		dp[0][r][c] = 1;
		for (int step = 1; step <= K; step++) {

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int[] dir : dirs) {
						int x = dir[0] + i;
						int y = dir[1] + j;
						if (x < 0 || x >= N || y < 0 || y >= N) {
							continue;
						}
						double value = dp[step - 1][x][y];
						System.out.println(value);
						dp[step][i][j] += dp[step - 1][x][y] * 0.125;
					}
				}
			}

		}
		double res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				res += dp[K][i][j];
			}
		}
		return res;
	}

	/**
	 *
	 * [[[1.0, 0.0, 0.0], [0.0, 0.0, 0.0], [0.0, 0.0, 0.0]],
	 * [[0.0, 0.0, 0.0], [0.0, 0.0, 0.125], [0.0, 0.125, 0.0]],
	 * [[0.03125, 0.0, 0.015625], [0.0, 0.0, 0.0], [0.015625, 0.0, 0.0]]]
	 *
	 * @param args
	 */

	public static void main(String[] args) {
		int n = 3, k = 2, row = 0, column = 0;
		KnightProbability prob = new KnightProbability();
		prob.knightProbability(n, k, row, column);
		prob.knightProbability2(n, k, row, column);
	}

}
