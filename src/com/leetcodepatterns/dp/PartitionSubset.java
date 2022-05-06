package com.leetcodepatterns.dp;

public class PartitionSubset {

	public static boolean bruteForce(int[] items) {
		int sum = total(items);
		if (sum % 2 == 0) {
			return helper(items, sum / 2, items.length);
		}
		return false;
	}

	private static boolean helper(int[] items, int sum, int i) {
		if (i == 0 || sum == 0) {
			return false;
		}
		int value = items[i - 1];
		if (sum == value) {
			return true;
		}
		if (sum < value) {
			return helper(items, sum, i - 1);
		}
		boolean r1 = helper(items, sum, i - 1);
		boolean r2 = helper(items, sum - value, i - 1);

		return r1 || r2;
	}

	public static boolean topDownDP(int[] items) {
		int sum = total(items);
		if (sum % 2 == 0) {
			return topDownDP(items, sum / 2, items.length, new Boolean[sum + 1][items.length + 1]);
		}
		return false;
	}

	private static boolean topDownDP(int[] items, int sum, int i, Boolean[][] memo) {
		if (i == 0 || sum == 0) {
			return false;
		}
		if (sum == items[i - 1]) {
			return true;
		}
		if (memo[sum][i] != null) {
			return memo[sum][i];
		}
		if (sum < items[i - 1]) {
			return memo[sum][i] = topDownDP(items, sum, i - 1, memo);
		}
		return memo[sum][i] = topDownDP(items, sum, i - 1, memo) || topDownDP(items, sum - items[i - 1], i - 1, memo);
	}

	public static boolean bottomupDP(int[] items) {
		int sum = total(items);
		if (sum % 2 != 0) {
			return false;
		}
		sum = sum / 2;
		boolean[][] dp = new boolean[sum + 1][items.length + 1];
		for (int i = 0; i <= items.length; i++) {
			for (int j = 0; j <= sum; j++) {
				if (i == 0 || j == 0) {
					dp[j][i] = false;
				} else if (j == items[i - 1]) {
					dp[j][i] = true;
				} else if (j < items[i - 1]) {
					dp[j][i] = dp[j][i - 1];
				} else {
					dp[j][i] = dp[j][i - 1] || dp[j - items[i - 1]][i - 1];
				}
			}
		}
		return dp[sum][items.length];
	}

	private static int total(int[] items) {
		int res = 0;
		for (int i : items) {
			res += i;
		}
		return res;
	}

	public static void method(float f) {
		System.out.println("float " + f);
	}

	public static void method(int f) {
		System.out.println("int " + f);
	}

	public static void method(double f) {
		System.out.println("double " + f);
	}

	public static void main(String[] args) {
		//		bruteForce(new int[] { 1, 2, 5, 2 });

		method(2.0);
		method(2.1);
		method(2.5);
		method(2);
	}



}

