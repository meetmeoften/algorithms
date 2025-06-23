package com.algoexpert.arrays;

public class PatchingArray {


	public static int minPatches(int[] nums, int n) {

		int i= 0;
		int patches = 0;

		long currRange = 1L;

		while(currRange <= n) {
			System.out.println(nums[i] + " " + currRange);
			if(i < nums.length && nums[i] <= currRange) {
				currRange += nums[i];
				i++;
			} else {
				patches++;
				currRange += currRange;
			}
		}

		return patches;

	}

	public static void main(String[] args) {
		int[] array = { 1, 5, 10 };
		System.out.println("Max sum subarray:" + minPatches(array, 20));
	}

}
