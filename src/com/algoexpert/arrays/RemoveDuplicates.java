package com.algoexpert.arrays;

public class RemoveDuplicates {

	public static int removeDuplicates(int[] nums) {
		if (nums.length <= 1) {
			return nums.length;
		}
		int curr = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i-1]) {
				nums[curr] = nums[i];
				curr++;
			}
		}
		return curr;
	}

	public static void main(String[] args) {
		removeDuplicates(new int[] {1, 2, 2, 4});
	}
}
