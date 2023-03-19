package com.algoexpert.arrays.leetcode300;

public class MoveZeros {

	public static  void moveZeroes(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}

		int curr = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[curr] = nums[i];
				curr++;
			}
		}

		for (int j = curr; j < nums.length; j++) {
			nums[j] = 0;
		}
	}


	public static void main(String[] args) {
		int[] nums = {0,1,0,3,12};
		moveZeroes(nums);

	}

}
