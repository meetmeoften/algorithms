package com.algoexpert.arrays.leetcode300;

import java.util.HashMap;

public class ContinousSubArray {

	public static boolean checkSubarraySum(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		int sum = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			int rem = sum % k;

			if (map.containsKey(rem)) {
				if (i - map.get(rem) >= 2) {
					return true;
				}
			}
			if (!map.containsKey(rem)) {
				map.put(rem, i);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] nums = { 23, 2, 4, 6, 7 };
		checkSubarraySum(nums, 6);
	}

}
