package com.algoexpert2.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RadixSort {

	/**
	 * countarray sorted array
	 *
	 * add values to the count array i + i-1 fill sorted array
	 *
	 * @param array
	 * @return
	 */

	public static ArrayList<Integer> radixSort(ArrayList<Integer> array) {
		if (array.size() == 0) {
			return array;
		}
		int maxNumber = Collections.max(array);
		int digit = 0;
		while ((maxNumber / Math.pow(10, digit)) > 0) {
			countingSort(array, digit);
			digit++;
		}
		return array;
	}

	public static void countingSort(ArrayList<Integer> array, int digit) {
		int[] sortedArray = new int[array.size()];
		int[] countArray = new int[10];

		int digitColumn = (int) Math.pow(10, digit);
		for (int num : array) {
			int countIndex = (num / digitColumn) % 10;
			countArray[countIndex]++;
		}

		for (int i = 1; i < 10; i++) {
			countArray[i] = countArray[i] + countArray[i - 1];
		}

		for (int i = array.size() - 1; i >= 0; i--) {
			int countIndex = (array.get(i) / digitColumn) % 10;
			countArray[countIndex]--;
			int sortedIndex = countArray[countIndex];
			sortedArray[sortedIndex] = array.get(i);
		}
		for (int i = 0; i < array.size(); i++) {
			array.set(i, sortedArray[i]);
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> input = new ArrayList(Arrays.asList(8762, 654, 3008, 345, 87, 65, 234, 12, 2));
		ArrayList<Integer> expected = new ArrayList(Arrays.asList(2, 12, 65, 87, 234, 345, 654, 3008, 8762));
		var actual = radixSort(input);
	}
}
