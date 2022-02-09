package com.algoexpert.binarysearch;

public class SearchForRange {

	static int findStartingIndex(int arr[], int n, int target) {
		int low = 0;
		int high = n - 1;
		int index = -1;

		while (low <= high) {
			// Calculate mid value.
			int mid = (low + high) / 2;

			// Condition 1
			if (arr[mid] >= target) {
				high = mid - 1;
			}
			// Condition 2 : When arr[mid] < target
			else {
				low = mid + 1;
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
		int low = 0;
		int high = n - 1;
		int index = -1;

		while (low <= high) {
			// Calculate mid value.
			int mid = (low + high) / 2;

			// Condition 1
			if (arr[mid] <= target) {
				low = mid + 1;
			}
			// Condition 2 : When arr[mid] > target
			else {
				high = mid - 1;
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
		int arr[] = new int[] { 5, 6, 6, 6, 6, 6, 7, 8, 8, 10 };
		int n = arr.length;

		int target = 6;
		findFirstAndLastPosition(arr, n, target);

	}

}
