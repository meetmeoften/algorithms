package com.algoexpert2.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinHeapConstruction {

	static class MinHeap {
		List<Integer> heap = new ArrayList<Integer>();

		public MinHeap(List<Integer> array) {
			heap = buildHeap(array);
		}

		public List<Integer> buildHeap(List<Integer> array) {
			// Write your code here.
			int firstParentIndex = (array.size()-2)/2;

			for(int i= firstParentIndex; i >= 0; i--) {
				siftDown(i, array.size()-1, array);
			}
			return array;
		}

		// 0  1	  2   3  4  5   6   7    8   9   10 11 12 13
		//48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41
		public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
			int childOneIdx = currentIdx * 2 + 1;

			while(childOneIdx <= endIdx ) {
				int childTwoIdx = currentIdx * 2 + 2 <= endIdx ? currentIdx * 2 + 2 : -1;

				int idxToSwap;
				if(childTwoIdx != -1 && heap.get(childTwoIdx) < heap.get(childOneIdx)) {
					idxToSwap = childTwoIdx;
				} else {
					idxToSwap = childOneIdx;
				}

				if(heap.get(idxToSwap) < heap.get(currentIdx)) {
					swap(idxToSwap, currentIdx, heap);
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
	}

	public static void main(String[] args) {
		MinHeap minHeap = new MinHeap(
				//				new ArrayList<Integer>(Arrays.asList(48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41)));
				new ArrayList<Integer>(Arrays.asList(48, 12, 24, 7, 8)));
		minHeap.insert(9);
		minHeap.peek();
		minHeap.remove();
		minHeap.peek();
		minHeap.remove();
		minHeap.peek();
		minHeap.insert(87);
	}

	static boolean isMinHeapPropertySatisfied(List<Integer> array) {
		for (int currentIdx = 1; currentIdx < array.size(); currentIdx++) {
			int parentIdx = (currentIdx - 1) / 2;
			if (parentIdx < 0) {
				return true;
			}
			if (array.get(parentIdx) > array.get(currentIdx)) {
				return false;
			}
		}

		return true;
	}
}
