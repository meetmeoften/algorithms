package com.algoexpert.arrays.leetcode300;

import java.util.HashMap;

public class SubArraySumEqualsK {

	public static int subarraySum(int[] nums, int k) {
		int res = 0, sum = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 1);

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			int diff = sum - k;
			if (map.containsKey(diff)) {
				res += map.get(diff);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return res;
	}

	public static int bruteForcesubarraySum(int[] nums, int k) {
		int count = 0;

		for (int i = 0; i < nums.length; i++) {
			int sum = 0;
			for (int j = i; j < nums.length; j++) {
				sum += nums[j];
				if (sum == k) {
					count++;
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		int[] num = { 1, 2, 3 };
		subarraySum(num, 3);
	}

}
