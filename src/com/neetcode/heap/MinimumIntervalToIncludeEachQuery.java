package com.neetcode.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MinimumIntervalToIncludeEachQuery {

	public class Interval {

		int left;
		int right;
		int len;

		public Interval(int[] interval) {
			this.left = interval[0];
			this.right = interval[1];
			this.len = right - left + 1;
		}
	}

	public int[] minInterval(int[][] intervals, int[] queries) {
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		int[] queriesClone = queries.clone();
		Arrays.sort(queriesClone);

		HashMap<Integer, Integer> hm = new HashMap<>();
		PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.len - b.len);

		int index = 0;

		for (int query : queriesClone) {

			while (index < intervals.length && query >= intervals[index][0]) {
				pq.offer(new Interval(intervals[index])); // these are our potential intervaLs that can encomapss our query
				index++;
			}
			while (!pq.isEmpty() && query > pq.peek().right ) {
				pq.poll();
			}

			if (!pq.isEmpty()) {
				hm.put(query, pq.peek().len);
			}
		}

		int[] res = new int[queries.length];
		int i = 0;
		for (int query : queries) {
			res[i++] = hm.getOrDefault(query, -1);
		}

		return res;
	}

	public static void main(String[] args) {
		int[][] intervals = { { 1, 4 }, { 2, 4 }, { 3, 6 }, { 4, 5 } };
		int[] queries = { 2, 3, 4, 5 };

		MinimumIntervalToIncludeEachQuery q = new MinimumIntervalToIncludeEachQuery();
		q.minInterval(intervals, queries);
	}

}
