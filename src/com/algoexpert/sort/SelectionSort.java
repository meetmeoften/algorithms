package com.algoexpert.sort;

public class SelectionSort {
	public static int[] selectionSort(int[] array) {
		// Write your code here.
		if (array.length == 0) {
			return new int[] {};
		}

		for (int i = 0; i < array.length; i++) {
			int smallNumber = Integer.MAX_VALUE;
			int index = 0;
			for (int j = i; j < array.length; j++) {
				if (smallNumber > array[j]) {
					smallNumber = array[j];
					index = j;
				}
			}
			if (smallNumber < array[i]) {
				var temp = array[i];
				array[i] = array[index];
				array[index] = temp;
			}
		}
		return array;
	}

	public static void main(String[] args) {
		int[] expected = {2, 3, 5, 5, 6, 8, 9};
		int[] input = {8, 5, 2, 9, 5, 6, 3};
		selectionSort(input);
	}
}
