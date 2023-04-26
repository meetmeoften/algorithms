package com.algoexpert.intervals;

import java.util.Arrays;

public class RemoveCoveredIntervals {

	public static int removeCoveredIntervals(int[][] intervals) {

		Arrays.sort(intervals, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));

		int count = 0, cur = 0;
		for (int interval[] : intervals) {
			if (cur < interval[1]) {
				cur = interval[1];
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		// int[][] intervals = { { 1, 4 }, { 3, 6 }, { 2, 8 } };
		int[][] intervals = { { 1, 4 }, { 2, 3 } };
		removeCoveredIntervals(intervals);
	}

}
