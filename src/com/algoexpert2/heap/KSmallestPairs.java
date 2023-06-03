package com.algoexpert2.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KSmallestPairs {

	public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

		List<List<Integer>> pairs = new ArrayList<>();
		if (nums1.length == 0 || nums2.length == 0 || k == 0) {
			return pairs;
		}

		PriorityQueue<int[]> minHeap = new PriorityQueue<>((arr1, arr2) -> arr1[0] + arr1[1] - arr2[0] - arr2[1]);

		for (int i = 0; i < nums1.length && i < k; i++) {
			minHeap.offer(new int[] { nums1[i], nums2[0], 0 });
		}


		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a1, a2) -> a1[0] + a1[1] - a2[0] - a2[1] );



		while (k-- != 0 && !minHeap.isEmpty()) {
			int[] curr = minHeap.poll();
			pairs.add(List.of(curr[0], curr[1]));
			if (curr[2] + 1 < nums2.length) {
				minHeap.offer(new int[] { curr[0], nums2[curr[2] + 1], curr[2] + 1 });
			}
		}

		return pairs;
	}


	public static void main(String[] args) {
		int[] nums1 = { 1, 7, 11 }, nums2 = { 2, 4, 6 };
		int k = 3;

		kSmallestPairs(nums1, nums2, k);

	}

	private static class pair implements Comparable<pair> {
		int num1;
		int num2;
		int sum;
		int idx;

		pair(int num1, int num2, int sum, int idx) {
			this.num1 = num1;
			this.num2 = num2;
			this.sum = sum;
			this.idx = idx;
		}

		@Override
		public int compareTo(pair p) {
			return this.sum - p.sum;
		}
	}

	public static List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
		PriorityQueue<pair> pq = new PriorityQueue<>();
		for (int i = 0; i < Math.min(k, nums1.length); i++) {
			pq.add(new pair(nums1[i], nums2[0], nums1[i] + nums2[0], 0));
		}

		List<List<Integer>> list = new ArrayList<>();
		while (k-- > 0 && pq.size() > 0) {
			List<Integer> l = new ArrayList<>();
			pair p = pq.remove();
			l.add(p.num1);
			l.add(p.num2);
			list.add(l);
			int idx = p.idx;
			if (idx < nums2.length - 1) {
				pq.add(new pair(p.num1, nums2[idx + 1], p.num1 + nums2[idx + 1], p.idx + 1));
			}
		}
		return list;
	}

}
