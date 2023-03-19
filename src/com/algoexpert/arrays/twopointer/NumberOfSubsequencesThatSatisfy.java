package com.algoexpert.arrays.twopointer;

import java.util.Arrays;

public class NumberOfSubsequencesThatSatisfy {

	public static int numSubseq(int[] nums, int target) {
		Arrays.sort(nums);
		int mod = (int) 1e9 + 7;
		int ans = 0;
		int l = 0, r = nums.length - 1;

		int[] power = new int[nums.length + 1];
		power[0] = 1;

		for (int i = 1; i < power.length; i++) {
			power[i] = (power[i - 1] * 2) % mod;
		}

		while (l <= r) {
			if (nums[l] + nums[r] <= target) {
				ans = (ans + power[r - l]) % mod;
				l++;
			} else if (nums[l] + nums[r] > target) {
				r--;
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		int[] nums = { 3, 5, 6, 7 };
		int target = 9;

		numSubseq(nums, target);
	}

}
