package com.algoexpert.binarysearch;

import java.util.Arrays;

public class SplitArrayLargestSum {

	public static int splitArray(int[] nums, int k) {

		int left = nums[0];
		int right = nums[0];
		for (int num : nums) {
			left = Math.max(left, num);
			right += num;
		}

		while (left < right) {
			int mid = (left + right) / 2;
			if (canSplit(nums, mid, k)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public static boolean canSplit(int[] nums, int amount, int m) {
		int count = 1;
		int sum = 0;

		for (int num : nums) {
			if (sum + num <= amount) {
				sum += num;
				continue;
			}

			if (++count > m) {
				return false;
			}

			sum = num;
		}

		return true;
	}


	public static int splitArray2(int[] nums, int m) {
		int[][] memo = new int[nums.length][m+1];

		for (int i = 0; i < memo.length; i++) {
			Arrays.fill(memo[i], -1);
		}

		return walk(nums, memo, 0, m);
	}

	private static int walk(int[] nums, int[][] memo, int start, int rem) {
		// base case
		if (rem == 0 && start == nums.length) {
			return 0;
		}
		if (rem == 0 || start == nums.length) {
			// if we reach the end and have not used up all patitions
			// or have used up all partitions and have not reached the end,
			// we do not want to count the current way of partitioning.
			// Return MAX_VALUE so that we don't contribute to the return value.
			return Integer.MAX_VALUE;
		}

		if (memo[start][rem] != -1) {
			return memo[start][rem];
		}

		int n = nums.length;
		int ret = Integer.MAX_VALUE;
		int curSum = 0;

		// try all positions to end the current partition.
		for (int i = start; i < n; i++) {
			curSum += nums[i];

			// answer for partitioning the subarray to the right of the current partition,
			// with one less partition number allowance, because we already used one
			// for the current partition. i.e. (rem - 1).
			int futureSum = walk(nums, memo, i + 1, rem - 1);

			// we want to minimum of the largest sum of the partitions.
			ret = Math.min(ret, Math.max(curSum, futureSum));
		}

		memo[start][rem] = ret;
		return ret;
	}

	public static void main(String[] args) {
		int[] nums = {7,2,5,10,8};
		int k = 2;
		splitArray(nums, k);
	}

}
