package com.algoexpert.greedy;

import java.util.Arrays;

public class TwoCityScheduling {

	int memo[][];

	public int twoCitySchedCost(int[][] arr) {
		memo = new int[arr.length][arr.length];
		for (int j[] : memo) {
			Arrays.fill(j, Integer.MAX_VALUE);
		}

		return rec(arr, 0, 0);
	}

	public int rec(int arr[][], int x, int y) {
		if (x + y >= arr.length) {
			return 0;
		}
		if (memo[x][y] != Integer.MAX_VALUE) {
			return memo[x][y];
		}

		int take = Integer.MAX_VALUE;

		if (x < arr.length / 2) {
			take = arr[x + y][0] + rec(arr, x + 1, y);
		}

		int not = Integer.MAX_VALUE;
		if (y < arr.length / 2) {
			not = arr[x + y][1] + rec(arr, x, y + 1);
		}

		return memo[x][y] = Math.min(not, take);
	}

	// ------------------

	public int twoCitySchedCost2(int[][] costs) {
		Arrays.sort(costs, (a, b) -> {
			return (a[0] - a[1]) - (b[0] - b[1]);
		});

		int totalCost = 0;

		for (int i = 0; i < costs.length; i++) {
			if (i < costs.length / 2) {
				totalCost += costs[i][0];
			} else {
				totalCost += costs[i][1];
			}
		}

		return totalCost;
	}

	public static void main(String[] args) {
		int[][] costs = { { 10, 20 }, { 30, 200 }, { 400, 50 }, { 30, 20 } };
		TwoCityScheduling s = new TwoCityScheduling();
		s.twoCitySchedCost(costs);
		s.twoCitySchedCost2(costs);
	}
}
