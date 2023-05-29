package com.algoexpert2.dp.subsetsum;

import java.util.Arrays;

public class PartitionKSubsets {

	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = Arrays.stream(nums).sum();
		if (sum % k != 0) {
			return false;
		}
		Arrays.sort(nums);
		boolean[] visited = new boolean[nums.length];
		return dfs(nums, k, sum / k, 0, 0, visited);
	}

	private boolean dfs(int[] nums, int k, int target, int start, int curSum, boolean[] visited) {
		if (k == 1) {
			return true;
		}
		if (curSum > target) {
			return false;
		}
		if (curSum == target) {
			return dfs(nums, k - 1, target, 0, 0, visited);
		}
		for (int i = start; i < nums.length; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			if (dfs(nums, k, target, i + 1, curSum + nums[i], visited)) {
				return true;
			}
			visited[i] = false;
		}
		return false;
	}

}
