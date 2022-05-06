package com.leetcodepatterns.dp;

import java.util.HashSet;
import java.util.Set;

public class PartitionEqualSubsetSum {

	public static boolean canPartition(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}

		if (sum % 2 != 0) {
			return false;
		}

		sum /= 2;

		boolean[][] dp = new boolean[nums.length + 1][sum + 1];
		dp[0][0] = true;

		for (int i = 1; i <= nums.length; i++) {
			dp[i][0] = true;
		}

		for (int i = 1; i <= nums.length; i++) {
			for (int j = 1; j <= sum; j++) {
				if (j >= nums[i - 1]) {
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
					System.out.println(i  + " " + j + " "  +dp[i][j]);
				}
			}
		}
		return dp[nums.length][sum];
	}


	public boolean canPartition2(int[] nums) {
		int sum = 0;
		for (int n: nums) {
			sum += n;
		}
		if (sum % 2 == 1) {
			return false;
		}

		return backtracking(sum, nums, 0, 0, new HashSet<>());
	}

	public boolean backtracking(int sum, int[] nums, int cur, int i, Set<Integer> visited) {
		if (cur == sum / 2) {
			return true;
		}
		if (visited.contains(cur) || i > nums.length || cur > sum / 2) {
			return false;
		}
		visited.add(cur);
		for (int j = i; j < nums.length; j++) {
			cur += nums[j];
			if (backtracking(sum, nums, cur, j + 1, visited)) {
				return true;
			}
			cur -= nums[j];
		}
		return false;
	}

	public static void main(String[] args) {
		canPartition(new int[] { 1, 2, 5, 2 });
	}

}
