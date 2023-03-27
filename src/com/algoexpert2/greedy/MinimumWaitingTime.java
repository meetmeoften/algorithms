package com.algoexpert2.greedy;

import java.util.Arrays;

public class MinimumWaitingTime {

	public static int minimumWaitingTime(int[] queries) {
		// Write your code here.
		Arrays.sort(queries);
		int totalWaitingTime = 0;
		for (int i = 0; i < queries.length; i++) {
			int duration = queries[i];
			int queriesLeft = queries.length - (i + 1);
			totalWaitingTime += duration * queriesLeft;
			System.out.println(totalWaitingTime);

		}
		// System.out.println(totalWaitingTime);
		return totalWaitingTime;
	}

	public static void main(String[] args) {

		int[] array = new int[] { 3, 2, 1, 2, 6 };
		minimumWaitingTime(array);
	}

}
