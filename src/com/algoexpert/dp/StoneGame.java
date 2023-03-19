package com.algoexpert.dp;

public class StoneGame {

	Integer[][] dp = new Integer[501][501];

	public boolean stoneGame(int[] piles) {
		return recursion(0, piles.length - 1, piles) > 0;
	}

	public int recursion(int start, int end, int[] piles) {
		if (start > end) {
			return 0;
		}
		if (dp[start][end] != null) {
			return dp[start][end];
		}

		// Pick start, and continue with the rest of the array or pick end and continue with the rest of the array

		int option1 = piles[start] - recursion(start + 1, end, piles);
		int option2 = piles[end] - recursion(start, end - 1, piles);

		return dp[start][end] = Math.max(option1, option2);
	}

	public static void main(String[] args) {
		int[] piles = {5, 3, 4, 5};
		StoneGame game = new StoneGame();
		game.stoneGame(piles);
	}

}
