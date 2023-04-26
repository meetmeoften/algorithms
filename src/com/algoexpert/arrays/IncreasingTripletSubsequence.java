package com.algoexpert.arrays;

public class IncreasingTripletSubsequence {

	public static boolean increasingTriplet(int[] nums) {

		int smallest = Integer.MAX_VALUE;
		int secondSmallest = Integer.MAX_VALUE;

		for (int num : nums) {
			if (num <= smallest) {
				smallest = num;
			} else if (num < secondSmallest) {
				secondSmallest = num;
			} else if (num > secondSmallest) {
				return true;
			}
		}

		return false;

	}

	public static void main(String[] args) {
		int[] nums = { 2, 1, 5, 0, 11, 6 };
		increasingTriplet(nums);
	}

}
