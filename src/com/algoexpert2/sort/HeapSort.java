package com.algoexpert2.sort;

import java.util.Arrays;

public class HeapSort {

	public static void buildheap(int[] arr) {

		/*
		 * As last non leaf node will be at (arr.length-1)/2 so we will start from this
		 * location for heapifying the elements
		 */
		for (int i = (arr.length - 1) / 2; i >= 0; i--) {
			System.out.println(arr[i]);
			heapify(arr, i, arr.length - 1);
		}
	}

	public static void heapify(int[] arr, int i, int size) {
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int max;
		if (left <= size && arr[left] > arr[i]) {
			max = left;
		} else {
			max = i;
		}

		if (right <= size && arr[right] > arr[max]) {
			max = right;
		}
		// If max is not current node, exchange it with max of left and right child
		if (max != i) {
			exchange(arr, i, max);
			heapify(arr, max, size);
		}
	}

	public static void exchange(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public static int[] heapSort(int[] arr) {

		buildheap(arr);
		int sizeOfHeap = arr.length - 1;
		for (int i = sizeOfHeap; i > 0; i--) {
			exchange(arr, 0, i);
			sizeOfHeap = sizeOfHeap - 1;
			heapify(arr, 0, sizeOfHeap);
		}
		return arr;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 10, 16, 19, 21, 5 };
		System.out.println("Before Heap Sort : ");
		System.out.println(Arrays.toString(arr));
		arr = heapSort(arr);
		System.out.println("=====================");
		System.out.println("After Heap Sort : ");
		System.out.println(Arrays.toString(arr));
	}
}