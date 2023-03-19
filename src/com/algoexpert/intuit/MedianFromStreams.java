package com.algoexpert.intuit;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFromStreams {
	Queue<Integer> maxHeap, minHeap;

	public MedianFromStreams() {
		maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		minHeap = new PriorityQueue<>();
	}

	public void addNum(int num) {
		minHeap.offer(num);
		maxHeap.offer(minHeap.poll());

		if (maxHeap.size() > minHeap.size()) {
			minHeap.offer(maxHeap.poll());
		}
	}

	public double findMedian() {
		return maxHeap.size() == minHeap.size() ? ((double) maxHeap.peek() + minHeap.peek()) / 2.0 : (double) minHeap.peek();
	}

	public static void main(String[] args) {
		MedianFromStreams streams = new MedianFromStreams();
		streams.addNum(1);
		System.out.println(streams.findMedian());
		streams.addNum(2);
		System.out.println(streams.findMedian());
		streams.addNum(3);
		System.out.println(streams.findMedian());
	}
}
