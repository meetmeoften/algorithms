package com.algoexpert.arrays.leetcode300;

public class RangeSum {

	private int[] prefixSum;

	public RangeSum(int[] nums) {
		int sum = 0;
		prefixSum = new int[nums.length];

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			prefixSum[i] = sum;
		}
	}

	public int sumRange(int left, int right) {
		if (left == 0) {
			return prefixSum[right];
		}
		return prefixSum[right] - prefixSum[left - 1];
	}
}

