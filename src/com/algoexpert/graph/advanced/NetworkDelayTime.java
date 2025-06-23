package com.algoexpert.graph.advanced;

import java.util.Arrays;

public class NetworkDelayTime {

	public int networkDelayTime2(int[][] times, int n, int k) {
		return 0;
	}



	// This is a bell man ford version which handles negative weights
	public static int networkDelayTime(int[][] times, int n, int k) {
		// initialize an array with max value of size n
		int[] paths = new int[n];
		Arrays.fill(paths, Integer.MAX_VALUE);

		paths[k - 1] = 0;

		for (int i = 0; i < n; i++) {
			// make a copy of paths
			int[] temp = new int[n];
			temp = Arrays.copyOf(paths, paths.length);

			// loop through times
			for (int j = 0; j < times.length; j++) {
				int src = times[j][0]; // source
				int tgt = times[j][1]; // target
				int time = times[j][2]; // time

				// check Paths with temp important
				if (paths[src - 1] != Integer.MAX_VALUE && paths[src - 1] + time < temp[tgt - 1]) {
					temp[tgt - 1] = paths[src - 1] + time;
				}
			}

			// set paths to temp
			paths = temp;
		}

		int result = Integer.MIN_VALUE;

		// calculate max value
		for (int i = 0; i < n; i++) {
			if (paths[i] == Integer.MAX_VALUE) {
				return -1;
			}
			result = Math.max(result, paths[i]);
		}

		// return result
		return result;
	}

	public static void main(String[] args) {
		int[][] points = {{2,1,1},{2,3,1},{3,4,1}};
		networkDelayTime(points, 4, 2);
	}

}
