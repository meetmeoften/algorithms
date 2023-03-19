package com.algoexpert.dp;

import java.util.Arrays;

public class IntegerBreak {

	// https://leetcode.com/problems/integer-break/solutions/3146388/java-easy-solution-with-2-approaches/

	public static int breakInteger(int n) {

		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;


		for (int num = 2; num <= n; num++) {
			if(num == n) {
				dp[num] = 0;
			} else {
				dp[num] = num;
			}
			for (int i = 1; i < num; i++) {
				int val = dp[i] * dp[num - i];
				dp[num] = Math.max(dp[num],  val);
			}
		}

		return dp[n];
	}

	//-----------------------------------

	public int integerBreak2(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, -1);
		return helper(n, n, dp);
	}

	private int helper(int n, int q, int[] dp) {
		if (n == 1) {
			return 1;
		}
		if (dp[n] != -1) {
			return dp[n];
		}

		int ans = (n == q) ? 0 : n;
		for (int i = 1; i < n; i++) {

			int c = helper(i, n, dp) * helper(n - i, n, dp);
			ans = Math.max(c, ans);
		}
		dp[n] = ans;
		return ans;
	}

	//-----------------------------------

	public int integerBreak3(int n) {
		return n < 4 ? n - 1 : helper(n, n);
	}

	private int helper(int a, int i) {
		if (i == 0 || a == 0) {
			return 1;
		}
		if (a < i) {
			return helper(a, i - 1);
		}
		return Math.max(helper(a, i - 1), helper(a - i, i) * (i));
	}

	public static void main(String[] args) {
		breakInteger(5);
	}

}
