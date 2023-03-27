package com.algoexpert2.arrays;

import java.util.HashSet;

public class ZeroSumSubArray {

	public static boolean zeroSumSubarray(int[] nums) {
		// Write your code here.

		HashSet<Integer> sums = new HashSet<>();
		sums.add(0);
		int currentSum = 0;

		for (int num : nums) {
			currentSum += num;
			if (sums.contains(currentSum)) {
				return true;
			}
			sums.add(currentSum);
		}

		return false;
	}

	public static void main(String[] args) {
		int[] nums = { -5, -5, 2, 3 };
		zeroSumSubarray(nums);
	}

}
