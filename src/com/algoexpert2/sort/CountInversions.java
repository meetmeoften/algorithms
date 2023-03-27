package com.algoexpert2.sort;

import java.util.ArrayList;
import java.util.List;

public class CountInversions {

	public int countInversions(int[] array) {
		// Write your code here.
		return countSubArrayInversions(array, 0, array.length);
	}

	public int countSubArrayInversions(int[] array, int start, int end) {
		if (end - start <= 1) {
			return 0;
		}

		int middle = (start + end) / 2;
		int leftInversions = countSubArrayInversions(array, start, middle);
		int rightInversions = countSubArrayInversions(array, middle, end);
		int mergedArrayInversions = mergeSortAndCountInversions(array, start, middle, end);
		return leftInversions + rightInversions + mergedArrayInversions;
	}

	public int mergeSortAndCountInversions(int[] array, int start, int middle, int end) {
		List<Integer> sortedArray = new ArrayList<Integer>();
		int left = start;
		int right = middle;

		int inversions = 0;

		while (left < middle && right < end) {
			if (array[left] <= array[right]) {
				sortedArray.add(array[left]);
				left++;
			} else {
				System.out.println("BEFORE " + inversions);
				inversions += middle - left;
				System.out.println("AFTER " + inversions);
				sortedArray.add(array[right]);
				right++;
			}
		}
		while (left < middle) {
			sortedArray.add(array[left++]);
		}

		while (right < end) {
			sortedArray.add(array[right++]);
		}

		for (int i = 0; i < sortedArray.size(); i++) {
			int num = sortedArray.get(i);
			array[start + i] = num;
		}
		return inversions;
	}

	public static void main(String[] args) {
		int[] input = new int[] { 2, 3, 3, 1, 9, 5, 6 };
		var expected = 5;
		var actual = new CountInversions().countInversions(input);
		assert (expected == actual);
	}
}
