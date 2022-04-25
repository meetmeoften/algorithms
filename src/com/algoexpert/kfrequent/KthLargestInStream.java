package com.algoexpert.kfrequent;

import java.util.PriorityQueue;

public class KthLargestInStream {

	private int k;
	private PriorityQueue<Integer> minHeap;

	public KthLargestInStream(int k, int[] nums) {
		this.k = k;
		minHeap = new PriorityQueue<>((a , b) -> a - b);
		for(int num: nums) {
			minHeap.add(num);
		}
		while (minHeap.size() > k) {
			minHeap.poll();
		}
	}

	public int add(int val) {
		minHeap.add(val);
		if (minHeap.size() > k) {
			minHeap.poll();
		}
		return minHeap.peek();
	}

	public static void main(String[] args) {

		int[] nums = new int[] {4, 5, 8, 2};
		KthLargestInStream stream = new KthLargestInStream(2, nums);
		System.out.println(stream.add(3));
		System.out.println(stream.add(5));
		System.out.println(stream.add(10));

	}

}
