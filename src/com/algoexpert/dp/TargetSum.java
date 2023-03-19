package com.algoexpert.dp;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {

	public static int findTargetSumWays(int[] nums, int target) {
		Map<String, Integer> memo = new HashMap<>();
		return helper(nums, target, 0, memo);
	}

	private static int helper(int[] nums, int sum, int idx, Map<String, Integer> memo) {
		if (idx == nums.length) {
			return sum == 0 ? 1 : 0;
		}

		String key = idx + "," + sum;
		if (memo.containsKey(key)) {
			return memo.get(key);
		}

		int result = 0;

		result += helper(nums, sum + nums[idx], idx + 1, memo);
		result += helper(nums, sum - nums[idx], idx + 1, memo);

		System.out.println(key + " - " + result);
		memo.put(key, result);
		return result;

	}

	public static int findTargetSumWays2(int[] nums, int target) {
		Map<String, Integer> map = new HashMap<>();
		return find(nums.length - 1, target, nums, map);
	}

	static int find(int n, int target, int nums[], Map<String, Integer> map) {

		if (n == -1 && target == 0) {
			return 1;
		}

		if (n <= -1) {
			return 0;
		}

		if (map.containsKey(n + " " + target)) {
			return map.get(n + " " + target);
		}

		int ans1 = find(n - 1, target - nums[n], nums, map);
		int ans2 = find(n - 1, target + nums[n], nums, map);

		map.put(n + " " + target, (ans1 + ans2));

		return (ans1 + ans2);

	}

	public static void main(String[] args) {
		System.out.println(findTargetSumWays(new int[] { 1, 1, 1, 1, 1 }, 3));
	}

}
