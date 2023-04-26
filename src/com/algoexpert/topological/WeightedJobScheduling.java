package com.algoexpert.topological;

import java.util.Arrays;
import java.util.TreeMap;

public class WeightedJobScheduling {

	public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int n = startTime.length;
		int[][] jobs = new int[n][3];

		for (int i = 0; i < n; i++) {
			jobs[i] = new int[] { startTime[i], endTime[i], profit[i] };
		}

		// sorting according to endTime -
		Arrays.sort(jobs, (a, b) -> a[1] - b[1]);

		// storing( endTime, maxProfit till this endTime);
		TreeMap<Integer, Integer> dp = new TreeMap<>();
		dp.put(0, 0);

		for (int[] job : jobs) {

			// calculating profit from curr job startTime and adding with
			// max profit of that of prev valid time schedule using floorEntry:
			int cur = dp.floorEntry(job[0]).getValue() + job[2];

			// if curr ie. current profit is max then maxProfit(lastEntry().getValue()) till
			// now
			// then put cur endTme and profit - else skip :)
			if (cur > dp.lastEntry().getValue()) {
				dp.put(job[1], cur);
			}
		}

		// as a result we have max profit at end of our DP .')
		return dp.lastEntry().getValue();
	}

	public static void main(String[] args) {
		int[] startTime = { 1, 2, 3, 3 }, endTime = { 3, 4, 5, 6 }, profit = { 50, 10, 40, 70 };
		jobScheduling(startTime, endTime, profit);
	}

}
