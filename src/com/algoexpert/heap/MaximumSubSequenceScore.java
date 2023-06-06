package com.algoexpert.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumSubSequenceScore {

	public static long maxScore(int[] nums1, int[] nums2, int k) {
		int n = nums1.length;
		int[][] pairs = new int[n][2];

		for (int i = 0; i < n; i++) {
			pairs[i] = new int[] { nums2[i], nums1[i] };
		}

		Arrays.sort(pairs, (a, b) -> b[0] - a[0]);
		PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> a - b);

		long res = 0;
		long totalSum = 0;

		for (int[] pair : pairs) {
			pq.add(pair[1]);
			totalSum += pair[1];

			if (pq.size() > k) {
				totalSum -= pq.poll();
			}

			if (pq.size() == k) {
				res = Math.max(res, totalSum * pair[0]);
			}
		}

		return res;
	}

	public static void main(String[] args) {
		int[] nums1 = { 1, 3, 3, 2 };
		int[] nums2 = { 2, 1, 3, 4 };
		int k = 3;

		maxScore(nums1, nums2, k);
	}

}
