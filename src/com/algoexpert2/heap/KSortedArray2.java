package com.algoexpert2.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class KSortedArray2 {

	public int[] sortKSortedArray(int[] array, int k) {

		Queue<Integer> minHeapWithKElements = new PriorityQueue<>();

		for(int i= 0; i < Math.min(k+1, array.length); i++) {
			minHeapWithKElements.add(array[i]);
		}

		int nextIndex = 0;
		for(int i= k+1; i < array.length; i++) {
			array[nextIndex++] = minHeapWithKElements.remove();
			minHeapWithKElements.add(array[i]);
		}

		while(!minHeapWithKElements.isEmpty()) {
			array[nextIndex++] = minHeapWithKElements.remove();
		}

		return array;
	}


	public static void main(String[] args) {
		int[] input = new int[] {3, 2, 1, 5, 4, 7, 6, 5};
		int k = 3;
		int[] expected = new int[] {1, 2, 3, 4, 5, 5, 6, 7};
		var actual = new KSortedArray2().sortKSortedArray(input, k);

	}

}
