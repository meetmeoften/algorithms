package com.neetcode.arrays;

public class WiggleSort {

	/**
	 * 280 Wiggle Sort
	 *
	 * Given an unsorted array nums, reorder it in-place such that nums[0] <=
	 * nums[1] >= nums[2] <= nums[3]....
	 *
	 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6,
	 * 2, 5, 3, 4].
	 *
	 * @tag-array
	 */

	/**
	 * When i is odd, nums[i] >= nums[i-1]
	 *
	 * When i is an even number, nums[i] <= nums[i-1]
	 *
	 * According to its parity, compare it with its corresponding condition, and if
	 * it doesn’t match, just swap the position with the previous number.
	 *
	 * @param nums
	 */

	public static void wiggleSort(int[] nums) {
		if (nums.length <= 1) {
			return;
		}

		for (int i = 1; i < nums.length; ++i) { // starts at i=1, to ensure initial uphill
			if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {

				swap(nums, i, i - 1);
			}
		}
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args) {
		int[] nums = { 3, 5, 2, 1, 6, 4 };
		wiggleSort(nums);
	}
}
