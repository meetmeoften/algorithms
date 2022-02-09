package com.algoexpert.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeOverlappingIntervals {


	public static int[][] mergeOverlappingIntervals(int[][] intervals) {

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
		int[][] intervals =
				new int[][] {
			{100, 105},
			{1, 104}
		};
		int[][] expected =
				new int[][] {
			{1, 2},
			{3, 8},
			{9, 10}
		};
		int[][] actual = mergeOverlappingIntervals(intervals);
	}
}
