package com.algoexpert.graph.advanced;

import java.util.Arrays;

public class CheapestFlights {

	public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		// initialize an array with max value of size n
		int[] prices = new int[n];
		Arrays.fill(prices, Integer.MAX_VALUE);

		// price from source to source is always 0
		prices[src] = 0;

		for (int i = 0; i <= k; i++) {
			// make a copy of prices
			int[] temp = new int[n];
			temp = Arrays.copyOf(prices, prices.length);

			// loop through flights
			for (int j = 0; j < flights.length; j++) {
				int s = flights[j][0]; // from
				int d = flights[j][1]; // to
				int p = flights[j][2]; // price

				if (prices[s] == Integer.MAX_VALUE) {
					continue;
				}

				if (prices[s] + p < temp[d]) {
					temp[d] = prices[s] + p;
				}
			}

			// set prices to temp
			prices = temp;
		}

		if (prices[dst] != Integer.MAX_VALUE) {
			return prices[dst];
		}

		return -1;
	}

	public static void main(String[] args) {
		int[][] points = { { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600 }, { 2, 3, 200 } };
		findCheapestPrice(4, points, 0, 4, 1);
	}

}
