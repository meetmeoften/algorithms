package com.algoexpert.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class KSortedArray2 {

	public int[] sortKSortedArray(int[] array, int k) {

		Queue<Integer> minHeapWithKElements = new PriorityQueue<>();
		for(int i=0; i< Math.min(k+1, array.length); i++) {
			minHeapWithKElements.offer(array[i]);
		}
		int nextIndexToInsertElement = 0;

		for(int i= k+1; i< array.length; i++) {
			int minElement = minHeapWithKElements.remove();
			array[nextIndexToInsertElement] = minElement;
			nextIndexToInsertElement++;

			int currentElement = array[i];
			minHeapWithKElements.offer(currentElement);
		}

		while(!minHeapWithKElements.isEmpty()) {
			int minElement = minHeapWithKElements.remove();
			array[nextIndexToInsertElement] = minElement;
			nextIndexToInsertElement++;
		}

		return array;
	}


	public static void main(String[] args) {
		int[] input = new int[] {3, 2, 2, 5, 4, 7, 6, 5};
		int k = 3;
		int[] expected = new int[] {1, 2, 3, 4, 5, 5, 6, 7};
		var actual = new KSortedArray2().sortKSortedArray(input, k);
	}

}
