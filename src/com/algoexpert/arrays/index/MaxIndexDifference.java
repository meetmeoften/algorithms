package com.algoexpert.arrays.index;

public class MaxIndexDifference {

	static int maxIndexDiff(int arr[], int n) {
		int index = 0;
		int diff = 0;
		for (int i = 0; i < n; i++) {
			int j = index + 1;
			while (j < n) {
				if (arr[i] <= arr[j]) {
					diff = Math.max(diff, j - i);
					index = j;
				}
				j++;
			}
		}
		return diff;

	}

	static int maxIndexDiff2(int A[], int n) {
		int i = 0, j = n - 1;
		while (A[0] == A[j]) {
			j--;
		}
		while (A[n - 1] == A[i]) {
			i++;
		}
		return Math.max(n - 1 - i, j);

	}

	public static void main(String[] args) {
		int[] arr = new int[]{34, 8, 10, 3, 2, 80, 30, 33, 1};

		maxIndexDiff(arr, arr.length);
	}
}
