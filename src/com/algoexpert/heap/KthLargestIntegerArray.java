package com.algoexpert.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargestIntegerArray {

	public String kthLargestNumber(String[] nums, int k) {
		PriorityQueue<String> minHeap = new PriorityQueue<>((o1, o2) -> {
			if (o1.length() == o2.length()) { // If the same length then compare by their string
				return o1.compareTo(o2);
			}
			return Integer.compare(o1.length(), o2.length()); // Compare by their length
		});

		for (String num : nums) {
			minHeap.offer(num);
			if (minHeap.size() > k) {
				minHeap.poll(); // pop the minimum value in the heap
			}
		}
		return minHeap.poll();
	}

	class StringNumberComparartor implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			if (s1.length() != s2.length()) {
				return (s1.length() - s2.length());
			}
			int len = s1.length();
			for (int i = 0; i < len; i++) {
				char c1 = s1.charAt(i), c2 = s2.charAt(i);
				if (c1 == c2) {
					continue;
				}
				return (c1 - c2);
			}
			return 0;
		}
	}


	public int findKthLargest(int[] nums, int k) {
		k = nums.length - k;
		return quickSelect(nums, 0, nums.length - 1, k);
	}

	private int quickSelect(int[] nums, int low, int high, int k) {
		int idx = low, pivot = high;

		for (int i = low; i < high; i++) {
			if (nums[i] <= nums[pivot]) {
				swap(nums, i, idx);
				++idx;
			}
		}

		swap(nums, idx, pivot);

		if (idx == k) {
			return nums[idx];
		} else if (idx < k) {
			return quickSelect(nums, idx + 1, high, k);
		} else {
			return quickSelect(nums, low, idx - 1, k);
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}


	public static void main(String[] args) {
		int[] input = new int[] {2,3,1,5,4};
		int k = 3;
		var actual = new KthLargestIntegerArray().findKthLargest(input, k);
	}

}
