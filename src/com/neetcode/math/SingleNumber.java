package com.neetcode.math;

public class SingleNumber {

	public static int singleNumber(int[] nums) {
		int ans = nums[0];
		for (int i = 1; i < nums.length; i++) {
			ans ^= nums[i];  // XOR 1 ^ 1 = 0 XOR of same number is always zero order does not matter
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] nums = {1, 2, 2};
		singleNumber(nums);
	}

}
