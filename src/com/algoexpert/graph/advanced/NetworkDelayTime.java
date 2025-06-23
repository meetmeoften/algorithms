package com.algoexpert.graph.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class NetworkDelayTime {

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

				if (temp[src - 1] != Integer.MAX_VALUE && temp[src - 1] + time < temp[tgt - 1]) {
					temp[tgt - 1] = temp[src - 1] + time;
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

	public static int networkDelayTime2(int[][] times, int n, int k) {
		ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (int[] time : times) {
			int u = time[0] - 1;
			int v = time[1] - 1;
			int wt = time[2];
			adj.get(u).add(new Pair(v, wt));
		}
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y) -> x.distance - y.distance);
		int dist[] = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[k - 1] = 0;
		pq.add(new Pair(0, k - 1));

		while (!pq.isEmpty()) {
			Pair p = pq.poll(); // Use poll() instead of peek() to remove the element
			int dis = p.distance;
			int node = p.node;

			for (Pair neighbor : adj.get(node)) {
				int adjnode = neighbor.node;
				int edgeWeight = neighbor.distance;

				// If current distance is smaller, push it into the queue.
				if (dis + edgeWeight < dist[adjnode]) {
					dist[adjnode] = dis + edgeWeight;
					pq.add(new Pair(dist[adjnode], adjnode));
				}
			}
		}
		int time = 0;
		for (int i : dist) {
			if (i == Integer.MAX_VALUE) {
				return -1;
			}
			time = Math.max(time, i);
		}
		return time;

	}

	public static void main(String[] args) {
		int[][] points = { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } };
		// int[][] points = { { 1, 2, 1 }, { 2, 3, 7 }, { 1, 3, 4 }, { 2, 1, 2 } };
		System.out.println(networkDelayTime2(points, 3, 2));
	}

}
