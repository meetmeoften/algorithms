package com.algoexpert.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestContigousArray {

	public static int getKthLargest(List<Integer> arr, int k) {

		int n = arr.size();

		// To store the subarray sums.
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

		for (int i = 0; i < n; i++) {
			// To store the current subarray sum.
			int sum = 0;
			for (int j = i; j < n; j++) {
				sum = sum + arr.get(j);

				if (minHeap.size() < k) {
					minHeap.add(sum);
				} else {
					if (minHeap.peek() < sum) {
						// Pop the minimum element.
						minHeap.poll();
						minHeap.add(sum);
					}
				}
			}
		}
		// Top-most element is the k-th largest subarray sum.
		return minHeap.peek();
	}

	public static void main(String[] args) {
		int a[] = { 20, -5, -1 };
		int K = 3;

		List<Integer> list = new ArrayList<>();
		list.add(20);
		list.add(-5);
		list.add(-1);

		getKthLargest(list, K);
	}

}
