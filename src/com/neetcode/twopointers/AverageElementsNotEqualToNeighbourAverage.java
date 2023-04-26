package com.neetcode.twopointers;

import java.util.Arrays;

public class AverageElementsNotEqualToNeighbourAverage {

	public static int[] rearrangeArray(int[] A) {
		Arrays.sort(A);
		for (int i = 1; i < A.length; i += 2) {
			int tmp = A[i];
			A[i] = A[i - 1];
			A[i - 1] = tmp;
		}
		return A;
	}

	public static int[] rearrangeArray2(int[] nums) {
		for (int i = 1; i < nums.length - 1; i++) {
			int a = nums[i - 1];
			int b = nums[i];
			int c = nums[i + 1];
			if (a > b && b > c || a < b && b < c) {

				int tmp = nums[i];
				nums[i] = nums[i + 1];
				nums[i + 1] = tmp;
			}
		}
		return nums;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5 };
		//rearrangeArray(nums);
		rearrangeArray2(nums);
	}

}
