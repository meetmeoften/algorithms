package com.algoexpert.intuit;

public class LeftSmallerAndRightGreater {

	public static int findElement(int arr[], int n){
		int[] left = new int[n];
		int[] right = new int[n];

		left[0] = Integer.MIN_VALUE;
		right[n - 1] = Integer.MAX_VALUE;

		for (int i = 0; i < n - 1; i++) {
			left[i + 1] = Math.max(left[i], arr[i]);
		}

		for (int i = n - 1; i > 0; i--) {
			right[i - 1] = Math.min(right[i], arr[i]);
		}

		for (int i = 1; i < n - 1; i++) {
			if (left[i] <= arr[i] && right[i] >= arr[i]) {
				return arr[i];
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] arr = new int[]{4, 2, 5, 7};
		findElement(arr, arr.length);
	}

}
