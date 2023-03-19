package com.algoexpert.dp;

public class LastStone2 {

	public static int lastStoneWeightII(int[] stones) {
		int sumStWt = 0;
		for (int stone : stones) {
			sumStWt += stone;
		}
		Integer[][] dp = new Integer[stones.length][sumStWt];
		return helper(stones, 0, 0, 0, dp);
	}

	private static int helper(int[] stones, int index, int sumL, int sumR, Integer[][] dp) {
		if (index == stones.length) {
			return Math.abs(sumL - sumR);
		}

		if (dp[index][sumL] != null) {
			return dp[index][sumL];
		}

		dp[index][sumL] = Math.min(helper(stones, index + 1, sumL + stones[index], sumR, dp),
				helper(stones, index + 1, sumL, sumR + stones[index], dp));
		return dp[index][sumL];
	}

	public static int lastStoneWeightII2(int[] stones) {
		int S = 0, S2 = 0;
		for (int s : stones) {
			S += s;
		}
		int n = stones.length;
		boolean[][] dp = new boolean[S + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			dp[0][i] = true;
		}
		for (int i = 1; i <= n; i++) {
			for (int s = 1; s <= S / 2; s++) {
				if (dp[s][i - 1] || (s >= stones[i - 1] && dp[s - stones[i - 1]][i - 1])) {
					dp[s][i] = true;
					S2 = Math.max(S2, s);
				}
			}
		}
		return S - 2 * S2;
	}

	public static int lastStoneWeightII3(int[] stones) {
		int sumArr = 0;

		for (int i = 0; i < stones.length; i++) {
			sumArr += stones[i];
		}

		// our goal is to separate the stones to 2 heaps that
		// the difference between total weight of the 2 heaps is the smallest
		// So assume a bag with sumArr(totalWeight) / 2 capacity
		// we want to find the combination of stones that maximize
		// the total weight for the bag, and not exceeding the capacity
		int sum = sumArr / 2;

		// dp[j]
		// j here is the capacity of the bag
		int[] dp = new int[sum + 1];

		// only thing we care here is for the stone stones[i], do we want to put it in
		// the bag or not?
		for (int i = 0; i < stones.length; i++) {
			for (int j = sum; j >= stones[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
			}
		}

		return sumArr - dp[sum] - dp[sum];
	}

	public static void main(String[] args) {
		int[] stones = { 2, 7, 4, 1, 8, 1 };
		lastStoneWeightII2(stones);
	}

}
