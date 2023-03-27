package com.algoexpert2.sort;

import java.util.Arrays;

public class MergeSortUsingNormal {

	public static int[] mergeSort(int[] array) {
		if (array.length <= 1) {
			return array;
		}
		int middleIdx = array.length / 2;
		int[] leftHalf = Arrays.copyOfRange(array, 0, middleIdx);
		int[] rightHalf = Arrays.copyOfRange(array, middleIdx, array.length);
		var fLeftHalf = mergeSort(leftHalf);
		var fRightHalf = mergeSort(rightHalf);
		return mergeSortedArrays(fLeftHalf, fRightHalf);
	}

	public static int[] mergeSortedArrays(int[] leftHalf, int[] rightHalf) {
		int[] sortedArray = new int[leftHalf.length + rightHalf.length];

		int i = 0;
		int j = 0;
		int k = 0; // acts as a pointer for Array

		while (i < leftHalf.length && j < rightHalf.length) {
			if (leftHalf[i] <= rightHalf[j]) {
				sortedArray[k++] = leftHalf[i++];
			} else {
				sortedArray[k++] = rightHalf[j++];
			}
		}

		while (i < leftHalf.length) {
			sortedArray[k++] = leftHalf[i++];
		}

		while (j < rightHalf.length) {
			sortedArray[k++] = rightHalf[j++];
		}
		return sortedArray;
	}

}
