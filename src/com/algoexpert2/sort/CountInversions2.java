package com.algoexpert2.sort;

public class CountInversions2 {

	// Function to find inversion count of a given array
	public static int findInversionCount(int[] arr) {
		int inversionCount = 0;

		// consider `arr[j]` as the middle element of a triplet, and find `arr[i]`
		// and `arr[k]` such that `(arr[i], arr[j], arr[k])` form an inversion
		for (int j = 1; j < arr.length - 1; j++) {
			// count number of elements greater than `arr[j]` in the `range[0,j-1]`
			int greater = 0;
			for (int i = 0; i < j; i++) {
				if (arr[i] > arr[j]) {
					greater++;
				}
			}

			// count number of elements smaller than `arr[j]` in range `[j+1,n-1]`
			int smaller = 0;
			for (int k = j + 1; k < arr.length; k++) {
				if (arr[k] < arr[j]) {
					smaller++;
				}
			}

			// the total number of inversions with `arr[j]` as the middle element
			// is `greater × smaller`
			inversionCount += (greater * smaller);
		}

		return inversionCount;
	}

	public static void main(String[] args) {
		//int[] arr = { 9, 4, 3, 5, 1 };
		int[] arr = { 2, 3, 3, 1, 9, 5, 6 };

		System.out.println("The inversion count is " + findInversionCount(arr));
	}

}
