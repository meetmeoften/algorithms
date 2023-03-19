package com.algoexpert.binarytree;

public class GenerateNoOfBinarySearchTrees {


	/**
	 *
	 * numTrees[4] = 	numTree[0] * numTrees[3] +
	 * 					numTree[1] * numTrees[2] +
	 * 					numTree[2] * numTrees[1] +
	 * 					numTree[3] * numTrees[0] +
	 *
	 * @param n
	 * @return
	 */

	public static int numTrees(int n) {
		int dp[] = new int[n + 1];
		dp[0] = dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j] * dp[i - j - 1];
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		numTrees(4);
	}

}
