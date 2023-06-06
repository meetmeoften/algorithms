package com.algoexpert.intervals;

import java.util.Arrays;

public class MinimumArrowsToBurstBalloons {

	public static int findMinArrowShots(int[][] points) {

		if (points == null || points.length == 0) {
			return 0;
		}

		Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

		int ans = 1;
		int bound = points[0][1];

		for (int i = 1; i < points.length; i++) {
			// checking for non-overlapping range
			if (points[i][0] > bound) {
				bound = points[i][1];
				ans++;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int[][] points = { { 10, 16 }, { 2, 8 }, { 1, 6 }, { 7, 12 } };

		findMinArrowShots(points);
	}
}
