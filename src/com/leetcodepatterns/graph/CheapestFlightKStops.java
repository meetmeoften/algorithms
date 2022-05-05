package com.leetcodepatterns.graph;

import java.util.Arrays;

public class CheapestFlightKStops {

	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		int[] cost = new int[n];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[src] = 0;
		for (int i = 0; i <= K; i++) {
			int[] temp = Arrays.copyOf(cost, n);
			for (int[] f : flights) {
				int curr = f[0];
				int next = f[1];
				int price = f[2];
				if (cost[curr] == Integer.MAX_VALUE) {
					continue;
				}
				temp[next] = Math.min(temp[next], cost[curr] + price);
			}
			cost = temp;
		}
		return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
	}


	public static void main(String[] args) {
		//		int[][] flights = new int[][] {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
		//		int n = 4, src = 0, dst = 3, k = 1;
		int[][] flights = new int[][] {{0,1,100},{1,2,100},{0,2,500}};
		int n = 3, src = 0, dst = 2, k = 1;
		new CheapestFlightKStops().findCheapestPrice(n, flights, src, dst, k );
	}

}
