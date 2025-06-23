package com.algoexpert.graph.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

	public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {


		List<List<Pair>> list = new ArrayList<>();

		for(int i= 0; i < n; i++) {
			list.add(new ArrayList<>());
		}


		for(int i= 0; i < flights.length; i++) {
			list.get(flights[i][0]).add(new Pair(flights[i][1], flights[i][2]));
		}


		Queue<Tuple> q = new LinkedList<>();
		int[] dist = new int[n];
		for(int i= 0; i < n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[src] = 0;
		q.add(new Tuple(0, src, 0));

		while(!q.isEmpty()) {
			Tuple t = q.poll();
			int stops = t.stops;
			int node = t.src;
			int cost = t.cost;

			if(stops>k) {
				continue;
			}

			for(Pair pair: list.get(node)) {
				int adjNode = pair.node;
				int travelCost = pair.distance;

				if(cost + travelCost < dist[adjNode]) {
					dist[adjNode] = cost + travelCost;
					q.add(new Tuple(stops+1, adjNode, cost+travelCost));
				}

			}


		}

		return 0;
	}



	public static void main(String[] args) {
		int[][] points = { { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600 }, { 2, 3, 200 } };
		findCheapestPrice(4, points, 0, 4, 1);
	}

}

class Tuple {

	int stops;
	int src;
	int cost;

	Tuple(int distance, int row, int col) {
		this.stops = distance;
		this.src = row;
		this.cost = col;
	}

}

class Pair {
	public int node;
	public int distance;

	Pair(int node, int distance) {
		this.node = node;
		this.distance = distance;
	}
}
