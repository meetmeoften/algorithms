package com.algoexpert2.dp.coin;

import java.util.Arrays;

public class MinimumCoinChange {

	public static int minNumberOfCoinsForChange(int n, int[] denoms) {
		int[] numOfCoins = new int[n + 1];
		Arrays.fill(numOfCoins, Integer.MAX_VALUE);
		numOfCoins[0] = 0;
		int toCompare = 0;

		for (int denom : denoms) {
			for (int amount = 1; amount < numOfCoins.length; amount++) {
				if (amount >= denom) {
					int value = numOfCoins[amount - denom];
					if (value == Integer.MAX_VALUE) {
						toCompare = value;
					} else {
						toCompare = value + 1;
					}
					numOfCoins[amount] = Math.min(numOfCoins[amount], toCompare);
				}
			}
		}

		return numOfCoins[n] != Integer.MAX_VALUE ? numOfCoins[n] : -1;
	}

	public static int findMinCoins(int[] S, int target) {
		// if the total is 0, no coins are needed
		if (target == 0) {
			return 0;
		}

		// return infinity if total becomes negative
		if (target < 0) {
			return Integer.MAX_VALUE;
		}

		// initialize the minimum number of coins needed to infinity
		int coins = Integer.MAX_VALUE;

		// do for each coin
		for (int c : S) {
			// recur to see if total can be reached by including current coin `c`
			int result = findMinCoins(S, target - c);

			// update the minimum number of coins needed if choosing the current
			// coin resulted in a solution
			if (result != Integer.MAX_VALUE) {
				coins = Integer.min(coins, result + 1);
			}
		}

		// return the minimum number of coins needed
		return coins;
	}

	public static void main(String[] args) {
		int[] input = { 2, 5, 10 };
		minNumberOfCoinsForChange(7, input);
		findMinCoins( input, 7);
	}
}
