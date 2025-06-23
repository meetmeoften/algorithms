package com.algoexpert.intervals;

import java.util.Arrays;

public class NonOverlappingIntervals {


	public static int eraseOverlapIntervals(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}

		Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
		int result = 0;

		int[] prev = intervals[0];

		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] < prev[1]) {
				++result;
				prev[1] = Math.min(prev[1], intervals[i][1]);
			} else {
				prev = intervals[i];
			}
		}

		return result;
	}


	public static void main(String[] args) {
		int[][] intervals = new int[][] { { 1, 2 }, { 1, 3 } , {2, 3} };
		eraseOverlapIntervals(intervals);
	}

}
