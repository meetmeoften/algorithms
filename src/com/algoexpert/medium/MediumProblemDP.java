package com.algoexpert.medium;

import java.util.Arrays;

public class MediumProblemDP {

	//HouseRobber, Max Subset Sum
	public static int maxSubsetSumNoAdjacent(int[] array) {
		if(array.length == 0) {
			return 0;
		}

		if(array.length == 1) {
			return array[0];
		}

		int[] maxSums = new int[array.length];
		maxSums[0] = array[0];
		maxSums[1] = Math.max(array[0], array[1]);

		for(int i=2; i< array.length; i++) {
			maxSums[i] = Math.max(maxSums[i-1], maxSums[i-2] + array[i]);
		}
		return maxSums[array.length-1];
	}

	// Number of Ways To Make change
	public static int numberOfWaysToMakeChange(int n, int[] denoms) {
		// Write your code here.
		int[] ways = new int[n+1];
		ways[0] = 1;
		for(int denom: denoms) {
			for(int amount=1; amount < n+1; amount++) {
				if(denom <= amount) {
					ways[amount] += ways[amount-denom];
				}
			}
		}
		return ways[n];
	}

	// Minimum Number of Coins
	public static int minNumberOfCoinsForChange(int n, int[] denoms) {
		// Write your code here.
		int[] numOfCoins = new int[n+1];
		Arrays.fill(numOfCoins, Integer.MAX_VALUE);
		numOfCoins[0] = 0;
		int toCompare = 0;

		for(int denom : denoms) {
			for(int amount = 0; amount < numOfCoins.length; amount++) {
				if(amount >= denom) {
					int value = numOfCoins[amount - denom];
					if(value == Integer.MAX_VALUE) {
						toCompare = value;
					} else {
						toCompare = value+1;
					}
					numOfCoins[amount] = Math.min(numOfCoins[amount], toCompare);
				}
			}

		}
		return numOfCoins[n] != Integer.MAX_VALUE ? numOfCoins[n] : -1;
	}


	// LevenshteinDistance Edit distance
	public static int levenshteinDistance(String str1, String str2) {
		// Write your code here.

		int[][] edits = new int[str2.length()+1][str1.length()+1];

		for(int i= 0; i< str2.length()+1; i++) {
			for(int j= 0; j < str1.length()+1; j++) {
				edits[i][j] = j;
			}
			edits[i][0] = i;
		}

		for(int i= 1; i< str2.length()+1; i++) {
			for(int j= 1; j < str1.length()+1; j++) {
				if(str2.charAt(i-1) == str1.charAt(j-1)) {
					edits[i][j] = edits[i-1][j-1];
				} else {
					edits[i][j] = 1+ Math.min(edits[i-1][j], Math.min(edits[i-1][j-1], edits[i][j-1]));
				}
			}
		}

		return edits[str2.length()][str1.length()];
	}

	// NumberOfWaysToTraverseGraph
	public int numberOfWaysToTraverseGraph(int width, int height) {
		if(width == 1 || height == 1) {
			return 1;
		}
		return numberOfWaysToTraverseGraph(width-1, height) + numberOfWaysToTraverseGraph(width, height-1);
	}


	public int numberOfWaysToTraverseGraph2(int width, int height) {
		// Write your code here.
		int[][] numberOfWays = new int[height+1][width+1];

		for(int i= 1; i < width+1; i++ ) {
			for(int j=1; j < height+1; j++) {
				if(i== 1 || j == 1) {
					numberOfWays[j][i] = 1;
				} else {
					numberOfWays[j][i] = numberOfWays[j][i-1] +
							numberOfWays[j-1][i];
				}
			}
		}

		return numberOfWays[height][width];
	}
}
