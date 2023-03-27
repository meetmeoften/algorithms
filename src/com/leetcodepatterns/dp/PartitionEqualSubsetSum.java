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

		// https://www.youtube.com/watch?v=34l1kTIQCIA&list=RDCMUCnxhETjJtTPs37hOZ7vQ88g&index=2&ab_channel=TECHDOSE

		for (int i = 1; i <= nums.length; i++) {
			for (int j = 1; j <= sum; j++) {
				if (j >= nums[i - 1]) {
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
					// System.out.println(i + " " + j + " " +dp[i][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[nums.length][sum];
	}

	public static boolean subsetSum(int[] nums, int n, int sum) {
		// return true if the sum becomes 0 (subset found)
		if (sum == 0) {
			return true;
		}

		// base case: no items left or sum becomes negative
		if (n < 0 || sum < 0) {
			return false;
		}

		// Case 1. Include the current item `nums[n]` in the subset and recur
		// for remaining items `n-1` with the remaining total `sum-nums[n]`
		boolean include = subsetSum(nums, n - 1, sum - nums[n]);

		// return true if we get subset by including the current item
		if (include) {
			return true;
		}

		// Case 2. Exclude the current item `nums[n]` from the subset and recur for
		// remaining items `n-1`
		boolean exclude = subsetSum(nums, n - 1, sum);

		// return true if we get subset by excluding the current item
		return exclude;
	}

	public static boolean canPartition2(int[] nums) {
		int sum = 0;
		for (int n : nums) {
			sum += n;
		}
		if (sum % 2 == 1) {
			return false;
		}

		return backtracking(sum, nums, 0, 0, new HashSet<>());
	}

	public static boolean backtracking(int sum, int[] nums, int cur, int i, Set<Integer> visited) {
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
		// canPartition(new int[] { 1, 5, 10, 6 });
		canPartition(new int[] { 1, 2, 3, 4 });
	}

}
