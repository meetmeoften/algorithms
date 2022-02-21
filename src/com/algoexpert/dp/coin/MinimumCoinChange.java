package com.algoexpert.dp.coin;

import java.util.Arrays;

public class MinimumCoinChange {

	public static int minNumberOfCoinsForChange(int n, int[] denoms) {
		int[] numOfCoins = new int[n+1];
		Arrays.fill(numOfCoins, Integer.MAX_VALUE);
		numOfCoins[0] = 0;
		int toCompare = 0;
		for(int denom: denoms) {
			for(int amount = 1; amount < numOfCoins.length; amount++) {
				if(amount >= denom) {
					int value = numOfCoins[amount - denom];
					if(value == Integer.MAX_VALUE) {
						toCompare = value;
					} else {
						toCompare = value +1;
					}
					numOfCoins[amount] = Math.min(numOfCoins[amount], toCompare);
				}
			}
		}

		return numOfCoins[n] != Integer.MAX_VALUE ? numOfCoins[n] : -1;
	}

	public static void main(String[] args) {
		int[] input = {1, 5, 10};
		minNumberOfCoinsForChange(7, input);
	}
}
