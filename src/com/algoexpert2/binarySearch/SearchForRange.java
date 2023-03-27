package com.algoexpert2.binarySearch;

public class SearchForRange {

	static int findStartingIndex(int arr[], int n, int target) {
		int left = 0;
		int right = n - 1;
		int index = -1;

		while (left <= right) {
			// Calculate mid value.
			int mid = (left + right) / 2;

			// Condition 1
			if (arr[mid] >= target) {
				right = mid - 1;
			}
			// Condition 2 : When arr[mid] < target
			else {
				left = mid + 1;
			}
			// Condition 3
			if (arr[mid] == target) {
				index = mid;
			}

		}

		return index;
	}

	// finds Last occurrence of target.
	static int findEndingIndex(int arr[], int n, int target) {
		int left = 0;
		int right = n - 1;
		int index = -1;

		while (left <= right) {
			// Calculate mid value.
			int mid = (left + right) / 2;

			// Condition 1
			if (arr[mid] <= target) {
				left = mid + 1;
			}
			// Condition 2 : When arr[mid] > target
			else {
				right = mid - 1;
			}
			// Condition 3
			if (arr[mid] == target) {
				index = mid;
			}
		}

		return index;
	}

	static void findFirstAndLastPosition(int arr[], int n, int target) {
		int first = findStartingIndex(arr, n, target);
		int last = findEndingIndex(arr, n, target);
		System.out.println("The First occurrence of " + target + " at index : " + first
				+ " and the Last occurrence is at index : " + last);
	}

	public static void main(String args[]) {
		// int arr[] = new int[] { 5, 6, 6, 6, 6, 6, 7, 8, 8, 10 };
		int arr[] = new int[] { 5, 7, 7, 8, 8, 10 };
		int n = arr.length;

		int target = 8;
		findFirstAndLastPosition(arr, n, target);

	}

}
