package com.test.general;

import java.util.Arrays;

public class IsSumDivisibleBy3 {

	private static void calculateAndPrintResult(int arr[], int n, int ind1,
			int ind2) {
		for (int i = n - 1; i >= 0; i--) {
			if (i != ind1 && i != ind2) {
				System.out.println(arr[i]);
			}
		}
	}

	private static void largestArrayValue(int arr[], int n) {
		Arrays.sort(arr);

		int sum = 0;
		for (int element : arr) {
			sum = sum + element;
		}
		int remainder = sum % 3;
		if (remainder == 0) {
			calculateAndPrintResult(arr, n, -1, -1);
			return;
		}

		if (remainder == 1) {
			int[] remainder2 = new int[2];
			//			computeAndCalculateRemainder(remainder2, arr, n, remainder, 2);
			//			return;
			remainder2[0] = -1;
			remainder2[1] = -1;

			for (int i = 0; i < n; i++) {
				if (arr[i] % 3 == 1) {
					calculateAndPrintResult(arr, n, i, -1);
					return;
				}

				if (arr[i] % 3 == 2) {
					if (remainder2[0] == -1) {
						remainder2[0] = i;
					} else if (remainder2[1] == -1) {
						remainder2[1] = i;
					}
				}
			}

			if (remainder2[0] != -1 && remainder2[1] != -1) {
				calculateAndPrintResult(arr, n, remainder2[0], remainder2[1]);
				return;
			}
		}

		else if (remainder == 2) {
			int[] remainder1 = new int[2];
			//			computeAndCalculateRemainder(remainder1, arr, n, remainder, 1);
			//			return;
			remainder1[0] = -1;
			remainder1[1] = -1;

			for (int i = 0; i < n; i++) {
				if (arr[i] % 3 == 2) {
					calculateAndPrintResult(arr, n, i, -1);
					return;
				}

				if (arr[i] % 3 == 1) {
					if (remainder1[0] == -1) {
						remainder1[0] = i;
					} else if (remainder1[1] == -1) {
						remainder1[1] = i;
					}
				}
			}

			if (remainder1[0] != -1 && remainder1[1] != -1) {
				calculateAndPrintResult(arr, n, remainder1[0], remainder1[1]);
				return;
			}
		}
		return;
	}


	private static void computeAndCalculateRemainder(int[]remainderArray, int[] arr, int n, int remainderValue, int otherValue) {
		remainderArray[0] = -1;
		remainderArray[1] = -1;

		for (int i = 0; i < n; i++) {
			if (arr[i] % 3 == remainderValue) {
				calculateAndPrintResult(arr, n, i, -1);
				return;
			}

			if (arr[i] % 3 == otherValue) {
				if (remainderArray[0] == -1) {
					remainderArray[0] = i;
				} else if (remainderArray[1] == -1) {
					remainderArray[1] = i;
				}
			}
		}

		if (remainderArray[0] != -1 && remainderArray[1] != -1) {
			calculateAndPrintResult(arr, n, remainderArray[0], remainderArray[1]);
			return;
		}
	}


	public static void main(String[] args) {
		//		int arr[] = new int[] {3, 1, 4, 1, 5, 9};
		int arr[] = new int[] {3, 1, 1};
		largestArrayValue(arr, arr.length);

	}

}
