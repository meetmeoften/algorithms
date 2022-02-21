package com.algoexpert.arrays;

import java.util.Arrays;

public class MinimumIncrementToMakeArrayUnique {

	public static int method(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}

		Arrays.sort(A);

		int result = 0;

		for (int i = 1; i < A.length; i++) {
			if (A[i] <= A[i - 1]) {
				result += A[i - 1] - A[i] + 1;
				A[i] = A[i - 1] + 1;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		int[] arr = new int[] {1, 2, 2};
		method(arr);
	}


}
