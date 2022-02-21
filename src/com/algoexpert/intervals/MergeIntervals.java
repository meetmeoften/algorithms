package com.algoexpert.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
	public int[][] merge(int[][] intervals) {
		if (intervals == null || intervals.length <= 1) {
			return intervals;
		}

		Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

		List<int[]> result = new ArrayList<>();
		int[] interval = intervals[0];

		for (int i = 1; i < intervals.length; i++) {
			if (interval[1] >= intervals[i][0]) {
				interval[1] = Math.max(interval[1], intervals[i][1]);
			} else {
				result.add(interval);
				interval = intervals[i];
			}
		}

		result.add(interval);

		return result.toArray(new int[result.size()][]);
	}

	public int[][] mergeOverlappingIntervals(int[][] intervals) {
		// Write your code here.
		int[][] sortedIntervals = intervals.clone();
		Arrays.sort(sortedIntervals, (a, b) -> Integer.compare(a[0], b[0]));

		List<int[]> mergedIntervals = new ArrayList<>();
		int[] currentInterval = sortedIntervals[0];
		mergedIntervals.add(currentInterval);

		for(int i= 1; i < sortedIntervals.length; i++) {

			int currentIntervalEnd = currentInterval[1];
			int nextIntervalStart = sortedIntervals[i][0];
			int nextIntervalEnd = sortedIntervals[i][1];

			if(currentIntervalEnd >= nextIntervalStart) {
				currentInterval[1] = Math.max(currentIntervalEnd, nextIntervalEnd);
			} else {
				currentInterval = sortedIntervals[i];
				mergedIntervals.add(currentInterval);
			}
		}
		return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
	}

	public static void main(String[] args) {
		int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};

		new MergeIntervals().mergeOverlappingIntervals(intervals);
	}
}
