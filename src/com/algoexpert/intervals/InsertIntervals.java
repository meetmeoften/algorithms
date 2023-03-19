package com.algoexpert.intervals;

import java.util.ArrayList;
import java.util.List;

public class InsertIntervals {

	public static int[][] insert(int[][] intervals, int[] newInterval) {
		List<int[]> result = new ArrayList<>();
		int idx = 0;

		while(idx < intervals.length && intervals[idx][1] < newInterval[0]) {
			result.add(intervals[idx]);
			idx++;
		}

		while (idx < intervals.length &&  intervals[idx][0] <= newInterval[1]) {
			newInterval[0] = Math.min(newInterval[0], intervals[idx][0]);
			newInterval[1] = Math.max(newInterval[1], intervals[idx][1]);
			idx++;
		}
		result.add(newInterval);

		while (idx < intervals.length) {
			result.add(intervals[idx]);
			idx++;
		}

		return result.toArray(new int[0][]);
	}


	public static void main(String[] args) {
		int intervals[][] = {{3,4},{6,9}}, newInterval[] = {2,5};
		insert(intervals, newInterval);

	}
}
