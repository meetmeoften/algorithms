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
		int[] input = {2, 5, 10};
		minNumberOfCoinsForChange(7, input);
	}


	public static int levenshteinDistance(String str1, String str2) {
		int[][] edits = new int[str1.length()+1][str2.length()+1];

		for(int i= 0; i < str1.length()+1; i++) {
			for(int j= 0; j < str2.length()+1; j++) {
				edits[i][j] = j;
			}
			edits[i][0] = i;
		}

		for(int i= 1; i < str1.length()+1; i++) {
			for(int j= 1; j < str2.length()+1; j++) {
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					edits[i][j] =edits[i-1][j-1];
				} else {
					edits[i][j] = 1+ 1+ Math.min(edits[i-1][j], Math.min(edits[i-1][j-1], edits[i][j-1]));
				}
			}
		}


		return edits[str1.length()][str2.length()];

	}
}
