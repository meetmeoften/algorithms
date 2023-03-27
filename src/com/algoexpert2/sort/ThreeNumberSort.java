package com.algoexpert2.sort;

public class ThreeNumberSort {
	public int[] threeNumberSort(int[] array, int[] order) {
		// Write your code here.
		int low = 0;
		int mid = 0;
		int high = array.length - 1;

		int firstValue = order[0];
		int secondValue = order[1];

		while (mid <= high) {
			int value = array[mid];
			if (value == firstValue) {
				swap(low, mid, array);
				low++;
				mid++;
			} else if (value == secondValue) {
				mid++;
			} else {
				swap(mid, high, array);
				high--;
			}
		}
		return array;
	}

	private void swap(int i, int j, int[] arr) {
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;

	}

	public static void main(String[] args) {
		var array = new int[] {1, 0, 0, -1, -1, 0, 1, 1};
		var order = new int[] {0, 1, -1};
		var actual = new ThreeNumberSort().threeNumberSort(array, order);
	}
}
