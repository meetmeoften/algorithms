package com.algoexpert.slidingwindow;

import java.util.ArrayDeque;

public class SlidingWindowMaximum {

	public static int[] maxSlidingWindow(int[] nums, int k) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		int[] result = new int[nums.length - k + 1];

		int idx = 0;

		for (int i = 0; i < nums.length; i++) {
			if (!q.isEmpty() && i - k + 1 > q.getFirst()) {
				q.removeFirst();
			}

			while (!q.isEmpty() && nums[i] >= nums[q.getLast()]) {
				q.removeLast();
			}

			q.offerLast(i);

			if (i + 1 >= k) {
				result[idx] = nums[q.getFirst()];
				++idx;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// maxSlidingWindow(new int[] {3, 8, 5, 6, 7, 11}, 4);
		maxSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3);
	}

}
