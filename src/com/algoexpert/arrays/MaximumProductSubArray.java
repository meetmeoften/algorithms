package com.algoexpert.arrays;

public class MaximumProductSubArray {

	public static void maxProductSubArray(int[] nums) {
		int result = 0;
		int currentMax = 1;
		int currentMin = 1;

		for(int n : nums) {
			if(n==0) {
				currentMin = 1;
				currentMax = 1;
				continue;
			}
			int temp = n * currentMax;
			int tempCurrentMax = Math.max(n* currentMax, n * currentMin);
			int tempCurrentMin = Math.min(n* currentMax, n * currentMin);
			currentMax = Math.max(tempCurrentMax, n);
			currentMin = Math.max(tempCurrentMax, n);

			result = Math.max(currentMax, result);
		}

	}


	public static int maxProduct(int[] A) {
		if (A.length == 0) {
			return 0;
		}
		int max = A[0];
		int min = A[0];

		int total = A[0];

		for(int i= 1; i< A.length; i++) {
			int temp = max;
			max = Math.max(A[i], Math.max(A[i] * max, A[i] * min));
			min = Math.min(A[i], Math.min(A[i] * temp, A[i] * min));

			total = Math.max(max, total);

		}
		return total;
	}

	public static void main(String[] args) {
		int nums[] = {2,3,-2,4};
		maxProduct(nums);
	}

}
