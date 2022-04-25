package com.algoexpert.heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class ContinousMedian2 {

	static class ContinuousMedianHandler {
		Queue<Integer> minHeap = new PriorityQueue<>();
		Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		double median = 0;

		public void insert(int number) {
			minHeap.offer(number);
			maxHeap.offer(minHeap.poll());

			if (maxHeap.size() > minHeap.size()) {
				minHeap.offer(maxHeap.poll());
			}
		}

		public double getMedian() {
			if(minHeap.size() == maxHeap.size()) {
				median = ((double) minHeap.peek() + maxHeap.peek()) / 2.0;
			} else {
				median = minHeap.peek();
			}
			return median;

		}
	}

	public static void main(String[] args) {
		ContinousMedian2.ContinuousMedianHandler handler = new ContinousMedian2.ContinuousMedianHandler();
		handler.insert(5);
		handler.insert(10);
		System.out.println(handler.getMedian());
		handler.insert(100);
		System.out.println(handler.getMedian());
		handler.insert(200);
		System.out.println(handler.getMedian());
	}



}
