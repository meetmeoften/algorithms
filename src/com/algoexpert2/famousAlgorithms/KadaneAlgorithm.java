package com.algoexpert2.famousAlgorithms;

public class KadaneAlgorithm {

	public int kandaneForMaxSubArray(int[] arr) {
		int maxEndHere = 0;
		int maxSoFar = 0;
		for (int element : arr) {
			maxEndHere += element;
			if (maxEndHere < 0) {
				maxEndHere = 0;
			}
			maxSoFar = maxEndHere;
		}
		if (maxSoFar < maxEndHere) {

		}
		return maxSoFar;
	}

	/*
	 * Modified Kadane's algorithm If you make small modification to above algorithm
	 * It will work for negative numbers too
	 */
	public int kandaneForMaxSubArrayForNegativ(int[] arr) {
		int maxEndHere = arr[0];
		int maxSoFar = arr[0];
		for (int i = 1; i < arr.length; i++) {
			maxEndHere = Math.max(arr[i], maxEndHere + arr[i]);
			maxSoFar = Math.max(maxSoFar, maxEndHere);
		}
		return maxSoFar;
	}

	public static long maxSubarraySum(int[] arr, int n) {
		long maxi = Long.MIN_VALUE; // maximum sum
		long sum = 0;

		int start = 0;
		int ansStart = -1, ansEnd = -1;
		for (int i = 0; i < n; i++) {

			if (sum == 0) {
				start = i; // starting index
			}

			sum += arr[i];

			if (sum > maxi) {
				maxi = sum;

				ansStart = start;
				ansEnd = i;
			}

			// If sum < 0: discard the sum calculated
			if (sum < 0) {
				sum = 0;
			}
		}

		// printing the subarray:
		System.out.print("The subarray is: [");
		for (int i = ansStart; i <= ansEnd; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.print("]\n");

		// To consider the sum of the empty subarray
		// uncomment the following check:

		// if (maxi < 0) maxi = 0;

		return maxi;
	}

	public static void main(String args[]) {
		int arr[] = { 1, 8, -3, -7, 2, 7, -1, 9 };
		KadaneAlgorithm maxSum = new KadaneAlgorithm();
		System.out.println("Maximum subarray is  " + maxSum.kandaneForMaxSubArray(arr));
		maxSum.maxSubarraySum(arr, arr.length);
		int arrNeg[] = { -10, -8, -3, -7, -2, -7, -3, -9 };
		System.out.println(
				"Maximum Subarray when all elements are negative : " + maxSum.kandaneForMaxSubArrayForNegativ(arr));
	}
}
