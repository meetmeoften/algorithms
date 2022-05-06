package com.algoexpert.intuit;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualsK {

	public static int subarraySum(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int result = 0, currSum = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);

		for (int num : nums) {
			currSum += num;

			int value = currSum - k;
			if (map.containsKey(value)) {
				result += map.get(value);
			}

			map.put(currSum, map.getOrDefault(currSum, 0) + 1);
		}

		return result;
	}

	public static void main(String[] args) {
		int[] arr = new int[]{1,2,3};
		subarraySum(arr, 3);
	}

}
