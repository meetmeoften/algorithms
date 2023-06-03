package com.algoexpert.greedy;

public class MaximumSumCircularSubArrayDoubleKadane {

	public static int maxSubarraySumCircular(int[] nums) {
		int globalMin = nums[0];
		int globalMax = nums[0];
		int localMax = 0;
		int localMin = 0;
		int sum = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			localMax += nums[i];
			localMin += nums[i];
			globalMax = Math.max(globalMax, localMax);
			globalMin = Math.min(globalMin, localMin);
			localMax = Math.max(0, localMax);
			localMin = Math.min(0, localMin);

		}
		if (globalMin == sum) {
			return globalMax;
		}
		return Math.max(globalMax, sum - globalMin);
	}

	public static int maxSubarraySumCircular2(int[] nums) {
		int n = nums.length;
		int runningSum = 0;
		int bestSum = nums[0];

		// That's for kadane subarray
		for(int i = 0; i < n; i++) {
			runningSum += nums[i];
			bestSum = Math.max(bestSum, runningSum);
			runningSum = Math.max(0, runningSum);
		}

		int total = 0;
		int smallestSubarray = 0;
		int bestSmallest = nums[0];
		// Negative kadane for subtraction
		for(int i = 0; i < n; i++) {
			total += nums[i];
			smallestSubarray += nums[i];
			bestSmallest = Math.min(bestSmallest, smallestSubarray);
			smallestSubarray = Math.min(0, smallestSubarray);
		}

		// Will cause an empty set
		if(total == bestSmallest) {
			return bestSum;
		}

		return Math.max(bestSum, total - bestSmallest);
	}

	public static void main(String[] args) {
		//int[] nums = { 1, -2, 3, -2 };
		int[] nums = {5, -3, 5 };
		maxSubarraySumCircular(nums);
		maxSubarraySumCircular2(nums);
	}

}
