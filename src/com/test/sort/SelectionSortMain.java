package com.test.sort;

import java.util.Arrays;

public class SelectionSortMain {

	public static int[] selectionSort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[index]) {
					index = j;
				}
			}

			swap(arr, i, index);
		}
		return arr;
	}

	private static void swap(int[] arr, int i, int index) {
		int smallerNumber = arr[index];
		arr[index] = arr[i];
		arr[i] = smallerNumber;
	}

	public static void main(String a[]) {

		int[] arr = { 40, 10, -30, 45, 39, 32 };
		System.out.println("Before Sorting : ");
		System.out.println(Arrays.toString(arr));
		arr = selectionSort(arr);
		System.out.println("===================");
		System.out.println("After Sorting : ");
		System.out.println(Arrays.toString(arr));
	}
}
