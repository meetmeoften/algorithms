package com.algoexpert2.dp;

import java.util.HashMap;
import java.util.Map;

public class KnapsackRecursion {

	public static int knapsack(int[] v, int[] w, int n, int W) {
		// base case: Negative capacity
		if (W < 0) {
			return Integer.MIN_VALUE;
		}

		// base case: no items left or capacity becomes 0
		if (n < 0 || W == 0) {
			return 0;
		}

		// Case 1. Include current item `v[n]` in the knapsack and recur for
		// remaining items `n-1` with decreased capacity `W-w[n]`

		int include = v[n] + knapsack(v, w, n - 1, W - w[n]);

		// Case 2. Exclude current item `v[n]` from the knapsack and recur for
		// remaining items `n-1`

		int exclude = knapsack(v, w, n - 1, W);

		// return maximum value we get by including or excluding the current item
		return Integer.max(include, exclude);
	}

	public static int knapsack(int[] v, int[] w, int n, int W, Map<String, Integer> lookup) {
		// base case: Negative capacity
		if (W < 0) {
			return Integer.MIN_VALUE;
		}

		// base case: no items left or capacity becomes 0
		if (n < 0 || W == 0) {
			return 0;
		}

		// construct a unique map key from dynamic elements of the input
		String key = n + "|" + W;

		// if the subproblem is seen for the first time, solve it and
		// store its result in a map
		if (!lookup.containsKey(key)) {
			// Case 1. Include current item `n` in knapsack (v[n]) and recur
			// for remaining items `n-1` with decreased capacity `W-w[n]`
			int include = v[n] + knapsack(v, w, n - 1, W - w[n], lookup);

			// Case 2. Exclude current item `v[n]` from the knapsack and recur for
			// remaining items `n-1`
			int exclude = knapsack(v, w, n - 1, W, lookup);

			// assign the max value we get by including or excluding the current item
			lookup.put(key, Integer.max(include, exclude));
		}

		// return solution to the current subproblem
		return lookup.get(key);
	}

	public static void main(String[] args) {
		// input: a set of items, each with a weight and a value
		// int[] v = { 20, 5, 10, 40, 15, 25 };
		// int[] w = { 1, 2, 3, 8, 7, 4 };

		int[] v = { 1, 4 };
		int[] w = { 2, 3 };

		// knapsack capacity
		int W = 5;

		Map<String, Integer> lookup = new HashMap<>();
		System.out.println("Knapsack value is " + knapsack(v, w, v.length - 1, W, lookup));
		System.out.println("Knapsack value is " + knapsack(v, w, v.length - 1, W));
	}

}
