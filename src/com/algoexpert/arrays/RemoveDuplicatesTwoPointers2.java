package com.algoexpert.arrays;

public class RemoveDuplicatesTwoPointers2 {

	public static int removeDuplicates(int[] nums) {
		var n = nums.length;
		if (n < 3) {
			return n;
		}

		var i = 2;
		for (var j = i; j < n; j++) {
			if (nums[j] != nums[i - 2]) {
				nums[i++] = nums[j];
			}
		}

		return i;
	}

	// -------------------

	public int removeDuplicates(int[] nums, int k) {
		var n = nums.length;
		if (n < k + 1) {
			return n;
		}

		var i = k;
		for (var j = i; j < n; j++) {
			if (nums[i - k] != nums[j]) {
				nums[i++] = nums[j];
			}
		}

		return i;
	}

	public static void main(String[] args) {

		int[] nums = { 1, 1, 1, 1, 2, 3 }; // atmost 2 times a value is allowed
		removeDuplicates(nums);
	}

}
