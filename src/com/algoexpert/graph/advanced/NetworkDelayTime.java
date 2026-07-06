package com.algoexpert.graph.advanced;


import java.util.*;

public class NetworkDelayTime {

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
			int node = p.node;
			int dis = p.distance;

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

	static class Pair {
		int node;
		int distance;

		Pair(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}
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

	public static int networkDelayTime3(int[][] times, int n, int k) {
		List<int[]>[] graph = new List[n + 1];
		for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
		for (int[] time : times) graph[time[0]].add(new int[]{time[1], time[2]});

		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[k] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.offer(new int[]{k, 0});

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int node = curr[0], currDist = curr[1];
			if (currDist > dist[node]) continue;

			for (int[] edge : graph[node]) {
				int neighbor = edge[0], weight = edge[1];
				int newDist = currDist + weight;
				if (newDist < dist[neighbor]) {
					dist[neighbor] = newDist;
					pq.offer(new int[]{neighbor, newDist});
				}
			}
		}

		int maxDist = 0;
		for (int i = 1; i <= n; i++) {
			if (dist[i] == Integer.MAX_VALUE) return -1;
			maxDist = Math.max(maxDist, dist[i]);
		}
		return maxDist;
	}


public static void main(String[] args) {
		int[][] points = {{2,1,1},{2,3,1},{3,4,1}};
		networkDelayTime3(points, 4, 2);
	}

}
