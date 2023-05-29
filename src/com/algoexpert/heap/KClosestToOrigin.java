package com.algoexpert.heap;

import java.util.PriorityQueue;

public class KClosestToOrigin {

	public static int[][] kClosest(int[][] points, int K) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
				(p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);

		for (int[] p : points) {
			pq.offer(p);
			if (pq.size() > K) {
				pq.poll();
			}
		}
		int[][] res = new int[K][2];
		while (K > 0) {
			res[--K] = pq.poll();
		}
		return res;
	}

	public static void main(String[] args) {
		//int[][] points = { { 1, 3 }, { -2, 2 } };
		int[][] points = { { 3, 1 }, { 2, 2 } };
		int k = 1;

		kClosest(points, k);
	}

	// a[1, 1]
	// b[2, 2]  -- b[0]^2 + b[1]^2 - a[0

}
