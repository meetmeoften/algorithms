package com.algoexpert.sort;

public class InsertionSort {
	public static int[] insertionSort(int[] array) {
		// Write your code here.
		if (array.length == 0) {
			return new int[] {};
		}
		for (int i = 1; i < array.length; i++) {
			int j = i;
			while (j > 0 && array[j] < array[j - 1]) {
				swap(j, j - 1, array);
				j -= 1;
			}
		}
		return array;
	}

	private static void swap(int i, int j, int[] array) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;

	}

	public static void main(String[] args) {
		int[] expected = {2, 3, 5, 5, 6, 8, 9};
		int[] input = {8, 5, 2, 9, 5, 6, 3};
		insertionSort(input);
	}
}
