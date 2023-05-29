package com.algoexpert.dp;

public class DeleteAndEarn {

	// https://leetcode.com/problems/delete-and-earn/solutions/2886360/java-4-solutions/
	// See this

	public static int deleteAndEarn(int[] nums) {
		int a[] = new int[10001];
		for (int i : nums) {
			a[i] += i;
		}
		int dp[] = new int[10001];
		dp[0] = a[0];
		dp[1] = Math.max(a[0], a[1]);
		for (int i = 2; i <= 10000; i++) {
			dp[i] = Math.max(dp[i - 2] + a[i], dp[i - 1]);
		}
		return dp[10000];
	}

	public int deleteAndEarn2(int[] nums) {
		int[] freq = new int[10001];
		for (int i = 0; i < nums.length; i++) {
			freq[nums[i]]++;
		}
		return findMax(freq, 0);
	}

	public int findMax(int[] freq, int i) {
		if (i >= 10001) {
			return 0;
		}
		return Math.max((i * freq[i]) + findMax(freq, i + 2), findMax(freq, i + 1));
	}

	public static void main(String[] args) {
		int[] nums = { 2, 2, 3, 3, 3, 4 };
		deleteAndEarn(nums);
	}

}
