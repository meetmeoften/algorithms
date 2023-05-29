package com.algoexpert.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumPerformanceOfTeam {

	private static final int MOD = (int) 1e9 + 7;

	public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
		int[][] t = new int[n][2];
		for (int i = 0; i < n; ++i) {
			t[i] = new int[] { speed[i], efficiency[i] };
		}
		Arrays.sort(t, (a, b) -> b[1] - a[1]);

		PriorityQueue<Integer> q = new PriorityQueue<>();
		long tot = 0;
		long ans = 0;
		for (var x : t) {
			int s = x[0], e = x[1];
			tot += s;
			ans = Math.max(ans, tot * e);
			q.offer(s);
			if (q.size() == k) {
				tot -= q.poll();
			}
		}
		return (int) (ans % MOD);
	}

	public static void main(String[] args) {
		int n = 6, k = 2;
		int[] speed = { 2, 10, 3, 1, 5, 8 }, efficiency = { 5, 4, 3, 9, 7, 2 };
		maxPerformance(n, speed, efficiency, k);


		// [[1, 9], [5, 7], [2, 5], [10, 4], [3, 3], [8, 2]]
	}

}
