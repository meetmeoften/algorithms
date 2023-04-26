package com.algoexpert.arrays.leetcode300;

import java.util.Arrays;

public class WiggleSort {

	public static void wiggleSort(int[] nums) {
		int[] copy = Arrays.copyOf(nums, nums.length);
		Arrays.sort(copy);

		int left = (nums.length + 1) / 2 - 1, right = nums.length - 1;

		for (int i = 0; i < nums.length; i++) {
			if (i % 2 == 1) {
				nums[i] = copy[right];
				--right;
			} else {
				nums[i] = copy[left];
				--left;
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = { 1, 5, 1, 1, 6, 4 };
		wiggleSort(nums);
	}

}
