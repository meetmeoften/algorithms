package com.algoexpert.arrays;

public class MaximumProductSubArray {

	public void maxProductSubArray(int[] nums) {
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

}
