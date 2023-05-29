package com.algoexpert.arrays.leetcode300;

import java.util.HashMap;
import java.util.Map;

public class LargestContigousSubarrayOf0And1 {

	public static int findMaxLength(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		int maxlen = 0, count = 0;

		for (int i = 0; i < nums.length; i++) {
			count = count + (nums[i] == 1 ? 1 : -1);
			if (map.containsKey(count)) {
				maxlen = Math.max(maxlen, i - map.get(count));
			} else {
				map.put(count, i);
			}
		}
		return maxlen;
	}

	public static void main(String[] args) {
		int A[] = { 1, 0, 1, 1, 1, 0, 0 };
		findMaxLength(A);
	}

}
