package com.algoexpert.arrays.leetcode300;

public class PivotIndex {

	public static int pivotIndex(int[] nums) {

		int totalSum = 0;
		for (int i = 0; i < nums.length; i++) {
			totalSum += nums[i];
		}
		int leftSum = 0;
		for (int i = 0; i < nums.length; i++) {
			int rightSum = totalSum - leftSum - nums[i];
			if (leftSum == rightSum) {
				return i;
			}
			leftSum += nums[i];
		}
		return -1;

	}

	public static void main(String[] args) {
		int[] nums1 = { 1, 7, 3, 6, 5, 6 };
		pivotIndex(nums1);
	}

}
