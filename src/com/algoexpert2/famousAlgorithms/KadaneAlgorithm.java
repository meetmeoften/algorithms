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
		}if (maxSoFar < maxEndHere) {

		}
		return maxSoFar;
	}

	/* Modified Kadane's algorithm
	 * If you make small modification to above algorithm
	 * It will work for negative numbers too
	 */
	public int kandaneForMaxSubArrayForNegativ(int[] arr) {
		int maxEndHere = arr[0];
		int maxSoFar = arr[0];
		for(int i=1;i<arr.length;i++){
			maxEndHere = Math.max(arr[i], maxEndHere+arr[i]);
			maxSoFar = Math.max(maxSoFar,maxEndHere);
		}
		return maxSoFar;
	}

	public static void main(String args[]) {
		int arr[] = { 1, 8, -3, -7, 2, 7, -1, 9 };
		KadaneAlgorithm maxSum = new KadaneAlgorithm();
		System.out.println("Maximum subarray is  " + maxSum.kandaneForMaxSubArray(arr));
		int arrNeg[] = { -10, -8, -3, -7, -2, -7, -3, -9 };
		System.out.println("Maximum Subarray when all elements are negative : " + maxSum.kandaneForMaxSubArrayForNegativ(arr));
	}
}
