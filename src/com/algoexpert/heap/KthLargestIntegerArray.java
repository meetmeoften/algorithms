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

}
