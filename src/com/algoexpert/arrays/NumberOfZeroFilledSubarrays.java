package com.algoexpert.arrays;

public class NumberOfZeroFilledSubarrays {

	public static long zeroFilledSubarray(int[] nums) {
		long count = 0;
		long res = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				count++;
			} else {
				count = 0;
			}
			res += count;
		}

		return res;

	}

	public static void main(String[] args) {
		zeroFilledSubarray(new int[] { 1, 3, 0, 0, 2, 0, 0, 4 });
	}

}
