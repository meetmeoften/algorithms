package com.algoexpert2.sort;

public class HeapSort2 {

	public static int[] heapSort(int[] array) {
		// Write your code here.
		buildMaxHeap(array);
		// MaxHeaped Array = [9, 8, 6, 5, 5, 2, 3];
		for (int endIndex = array.length - 1; endIndex > 0; endIndex--) {
			swap(0, endIndex, array);
			siftDown(0, endIndex - 1, array);
		}
		return array;
	}

	public static void buildMaxHeap(int[] array) {
		int firstParentIndex = (array.length - 2) / 2;
		for (int currentIndex = firstParentIndex; currentIndex >= 0; currentIndex--) {
			siftDown(currentIndex, array.length - 1, array);
		}
	}

	public static void siftDown(int currentIndex, int endIndex, int[] heap) {
		int childOneIndex = currentIndex * 2 + 1;
		while (childOneIndex <= endIndex) {
			int childTwoIndex = currentIndex * 2 + 2 <= endIndex ? currentIndex * 2 + 2 : -1;
			int indexToSwap;
			if (childTwoIndex != -1 && heap[childTwoIndex] > heap[childOneIndex]) {
				indexToSwap = childTwoIndex;
			} else {
				indexToSwap = childOneIndex;
			}

			if (heap[indexToSwap] > heap[currentIndex]) {
				swap(currentIndex, indexToSwap, heap);
				currentIndex = indexToSwap;
				childOneIndex = currentIndex * 2 + 1;
			} else {
				return;
			}
		}
	}

	public static void swap(int i, int j, int[] array) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

	public static void main(String[] args) {
		int[] expected = {2, 3, 5, 5, 6, 8, 9};
		int[] input = {8, 5, 2, 9, 5, 6, 3};
		heapSort(input);
	}

	public boolean compare(int[] arr1, int[] arr2) {
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
}

