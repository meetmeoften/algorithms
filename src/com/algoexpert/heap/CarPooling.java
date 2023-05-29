package com.algoexpert.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CarPooling {

	public static boolean carPooling(int[][] trips, int capacity) {

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		Arrays.sort(trips, (a, b) -> a[1] - b[1]);
		int totalPersonsInTheCar = 0;
		for (int[] trip : trips) {
			while (!pq.isEmpty() && pq.peek()[2] <= trip[1]) {
				int[] detail = pq.poll();
				totalPersonsInTheCar -= detail[0];
			}

			totalPersonsInTheCar += trip[0];

			if (totalPersonsInTheCar > capacity) {
				return false;
			}

			pq.offer(trip);
		}

		return true;
	}

	// ---------------------

	public static boolean carPooling2(int[][] trips, int cap) {
		int[] count = new int[1001];
		for (int i = 0; i < trips.length; i++) {
			count[trips[i][1]] += trips[i][0];
			count[trips[i][2]] -= trips[i][0];
		}

		int curr = 0;
		for (int i : count) {
			curr += i;
			if (curr > cap) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int[][] trips = { { 2, 1, 5 }, {1, 2, 3}, { 2, 3, 7 } };
		carPooling(trips, 4);
	}

}
