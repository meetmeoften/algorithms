package com.algoexpert2.heap;

import java.util.ArrayList;
import java.util.List;

public class KSortedArray {

	public int[] sortKSortedArray(int[] array, int k) {
		// Write your code here.
		List<Integer> heapValues = new ArrayList<>();

		for (int i = 0; i < Math.min(k + 1, array.length); i++) {
			heapValues.add(array[i]);
		}

		MinHeap minHeapWithKElements = new MinHeap(heapValues);

		int nextIndexToInsertElement = 0;

		for (int i = k + 1; i < array.length; i++) {
			int minElement = minHeapWithKElements.remove();
			array[nextIndexToInsertElement] = minElement;
			nextIndexToInsertElement++;

			int currentElement = array[i];
			minHeapWithKElements.insert(currentElement);
		}

		while (!minHeapWithKElements.isEmpty()) {
			int minElement = minHeapWithKElements.remove();
			array[nextIndexToInsertElement] = minElement;
			nextIndexToInsertElement++;
		}

		return array;
	}

	class MinHeap {
		List<Integer> heap = new ArrayList<Integer>();

		public MinHeap(List<Integer> array) {
			heap = buildHeap(array);
		}

		public List<Integer> buildHeap(List<Integer> array) {
			// Write your code here.
			int firstParentIndex = (array.size() - 2) / 2;
			for (int currentIndex = firstParentIndex; currentIndex >= 0; currentIndex--) {
				siftDown(currentIndex, array.size() - 1, array);
			}
			return array;
		}

		public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
			// Write your code here.
			int childOneIdx = currentIdx * 2 + 1;
			while (childOneIdx <= endIdx) {
				int childTwoIdx = currentIdx * 2 + 2 <= endIdx ? currentIdx * 2 + 2 : -1;
				int idxToSwap;

				if (childTwoIdx != -1 && heap.get(childTwoIdx) < heap.get(childOneIdx)) {
					idxToSwap = childTwoIdx;
				} else {
					idxToSwap = childOneIdx;
				}

				if (heap.get(idxToSwap) < heap.get(currentIdx)) {
					swap(currentIdx, idxToSwap, heap);
					currentIdx = idxToSwap;
					childOneIdx = currentIdx * 2 + 1;
				} else {
					return;
				}
			}
		}

		public void siftUp(int currentIdx, List<Integer> heap) {
			// Write your code here.
			int parentIdx = (currentIdx - 1) / 2;
			while (currentIdx > 0 && heap.get(currentIdx) < heap.get(parentIdx)) {
				swap(currentIdx, parentIdx, heap);
				currentIdx = parentIdx;
				parentIdx = (currentIdx - 1) / 2;
			}
		}

		public int peek() {
			return heap.get(0);
		}

		public int remove() {
			// Write your code here.
			swap(0, heap.size() - 1, heap); // ideally removing head
			int valueToRemove = heap.get(heap.size() - 1);
			heap.remove(heap.size() - 1);
			siftDown(0, heap.size() - 1, heap);
			return valueToRemove;
		}

		public void insert(int value) {
			heap.add(value);
			siftUp(heap.size() - 1, heap);
		}

		public void swap(int i, int j, List<Integer> heap) {
			Integer temp = heap.get(j);
			heap.set(j, heap.get(i));
			heap.set(i, temp);
		}

		public boolean isEmpty() {
			return heap.size() == 0;
		}
	}


	public static void main(String[] args) {
		int[] input = new int[] {3, 2, 1, 5, 4, 7, 6, 5};
		int k = 3;
		int[] expected = new int[] {1, 2, 3, 4, 5, 5, 6, 7};
		var actual = new KSortedArray().sortKSortedArray(input, k);
	}

}
