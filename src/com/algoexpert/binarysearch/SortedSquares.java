package com.algoexpert.binarysearch;

public class SortedSquares {

	public static int[] sortedSquares(int[] nums) {
		if (nums == null || nums.length == 0) {
			return new int[] {};
		}

		int[] result = new int[nums.length];
		int start = 0, end = result.length - 1, idx = end;

		while (start <= end) {
			if (Math.abs(nums[start]) < Math.abs(nums[end])) {
				result[idx] = nums[end] * nums[end];
				end--;
			} else {
				result[idx] = nums[start] * nums[start];
				start++;
			}

			idx--;
		}

		return result;
	}

	public static void main(String[] args) {
		int[] nums = { -4, -1, 0, 3, 10 };
		sortedSquares(nums);
	}

}
