package com.algoexpert2.binarySearch;

public class IndexEqualsValue {

	public static int indexEqualsValue(int[] array) {
		// Write your code here.
		for (int index = 0; index < array.length; index++) {
			int value = array[index];
			if (index == value) {
				return index;
			}
		}
		return -1;
	}

	// This is done using Binary Search
	public static int indexEqualsValue2(int[] array) {

		int left = 0;
		int right = array.length - 1;

		while (left <= right) {
			int mid = (left + right) / 2;
			int midValue = array[mid];

			if (midValue < mid) {
				left = mid + 1;
			} else if (mid == midValue && mid == 0) {
				return mid;
			} else if (mid == midValue && array[mid - 1] < mid - 1) {
				return mid;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		// indexEqualsValue(new int[] { -5, -3, 0, 3, 4, 5, 9 });
		indexEqualsValue2(new int[] { -12, 1, 2, 3, 12 });
	}

}
