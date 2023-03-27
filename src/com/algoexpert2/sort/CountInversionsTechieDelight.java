package com.algoexpert2.sort;

import java.util.Arrays;

public class CountInversionsTechieDelight {

	public static int merge(int[] arr, int[] aux, int low, int mid, int high) {
		int k = low, i = low, j = mid + 1;
		int inversionCount = 0;

		// while there are elements in the left and right runs
		while (i <= mid && j <= high) {
			if (aux[i] <= aux[j]) {
				arr[k++] = aux[i++];
			} else {
				arr[k++] = aux[j++];
				System.out.println("BEFORE " + inversionCount);
				inversionCount += (mid - i + 1); // NOTE
				System.out.println("AFTER " + inversionCount);
			}
		}

		// copy remaining elements
		while (i <= mid) {
			aux[k++] = arr[i++];
		}

		while (j <= high) {
			aux[k++] = arr[j++];
		}

		/*
		 * no need to copy the second half (since the remaining items are already in
		 * their correct position in the temporary array)
		 */

		// copy back to the original array to reflect sorted order
		//		for (i = low; i <= high; i++) {
		//			arr[i] = aux[i];
		//		}

		return inversionCount;
	}

	// Sort array `arr[low…high]` using auxiliary array `aux`
	public static int mergesort(int[] arr, int[] aux, int low, int high) {
		// base case
		if (high <= low) { // if run size <= 1
			return 0;
		}
		int inversionCount = 0;
		int mid = (low + high)/2;
		inversionCount += mergesort(aux, arr, low, mid );
		inversionCount += mergesort(aux, arr, mid+1, high);
		inversionCount += merge(arr, aux, low, mid, high);

		//		// find midpoint
		//		int mid = (low + ((high - low) >> 1));
		//		int inversionCount = 0;
		//
		//		// recursively split runs into two halves until run size <= 1,
		//		// then merges them and return up the call chain
		//
		//
		//
		//		// split/merge left half
		//		inversionCount += mergesort(arr, aux, low, mid);
		//
		//		// split/merge right half
		//		inversionCount += mergesort(arr, aux, mid + 1, high);
		//
		//		// merge the two half runs
		//		inversionCount += merge(arr, aux, low, mid, high);

		return inversionCount;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 3, 3, 1, 9, 5, 6 };
		int[] aux = Arrays.copyOf(arr, arr.length);

		// get the inversion count by performing merge sort on arr
		System.out.println("The inversion count is " + mergesort(arr, aux, 0, arr.length - 1));
	}

}
