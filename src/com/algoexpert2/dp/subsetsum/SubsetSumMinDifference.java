package com.algoexpert2.dp.subsetsum;

import java.util.HashMap;
import java.util.Map;

public class SubsetSumMinDifference {

	public static int findMinAbsDiff(int[] S, int n, int S1, int S2, Map<String, Integer> lookup) {
		// Base case: if the list becomes empty, return the absolute
		// difference between both sets
		if (n < 0) {
			return Math.abs(S1 - S2);
		}

		// Construct a unique map key from dynamic elements of the input.
		// Note that we can uniquely identify the subproblem with `n` and `S1` only,
		// as `S2` is nothing but `S-S1`, where `S` is the sum of all elements
		String key = n + "|" + S1;

		// If the subproblem is seen for the first time, solve it and
		// store its result in a map
		if (!lookup.containsKey(key)) {
			// Case 1. Include the current item in subset `S1` and recur
			// for the remaining items `n-1`
			int inc = findMinAbsDiff(S, n - 1, S1 + S[n], S2, lookup);

			// Case 2. Exclude the current item from subset `S1` and recur for
			// the remaining items `n-1`
			int exc = findMinAbsDiff(S, n - 1, S1, S2 + S[n], lookup);

			lookup.put(key, Integer.min(inc, exc));
		}

		return lookup.get(key);
	}

	public static void main(String[] args) {
		// Input: a set of items
		int[] S = { 10, 20, 15, 5, 25 };

		// create a map to store solutions to subproblems
		Map<String, Integer> lookup = new HashMap<>();

		System.out.println("The minimum difference is " + findMinAbsDiff(S, S.length - 1, 0, 0, lookup));
	}

}
