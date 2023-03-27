package com.algoexpert2.recursion;

import java.util.Map;

public class InterweavingStringTechieDelight {

	public static boolean isInterleaving(String X, String Y, String S, Map<String, Boolean> T) {
		// return true if the end of all strings is reached
		if (X.length() == 0 && Y.length() == 0 && S.length() == 0) {
			return true;
		}

		// return false if the end of string 'S' is reached,
		// but string 'X' or 'Y' is not empty

		if (S.length() == 0) {
			return false;
		}

		// calculate a unique map key by using delimiter `|`
		String key = (X + "|" + Y + "|" + S);

		// if the subproblem is seen for the first time
		if (!T.containsKey(key)) {
			// if string 'X' is not empty and its first character matches with the
			// first character of 'S', recur for the remaining substring

			boolean x = (X.length() != 0 && S.charAt(0) == X.charAt(0))
					&& isInterleaving(X.substring(1), Y, S.substring(1), T);

			// if string 'Y' is not empty and its first character matches with the
			// first character of 'S', recur for the remaining substring

			boolean y = (Y.length() != 0 && S.charAt(0) == Y.charAt(0))
					&& isInterleaving(X, Y.substring(1), S.substring(1), T);

			T.put(key, x || y);
		}

		return T.get(key);
	}

	public static boolean isInterleaving(String X, String Y, String S) {
		int M = X.length();
		int N = Y.length();

		// base case: length of given strings doesn't match
		if (M + N != S.length()) {
			return false;
		}

		// create a lookup table T[][] to store solutions to already computed
		// subproblems. T[i][j] will be true when `S[0…i+j-1]` is an
		// interleaving of `X[0…i-1]` and `Y[0…j-1]`
		boolean[][] T = new boolean[M + 1][N + 1];

		// fill the lookup table in a bottom-up manner
		for (int i = 0; i <= M; i++) {
			for (int j = 0; j <= N; j++) {
				if (i == 0 && j == 0) { // both strings are empty
					T[i][j] = true;
				}

				// if the current char of 'S' matches the current char of both
				// 'A' and 'B'
				else if (i != 0 && j != 0 && X.charAt(i - 1) == S.charAt(i + j - 1)
						&& Y.charAt(j - 1) == S.charAt(i + j - 1)) {
					T[i][j] = T[i - 1][j] || T[i][j - 1];
				}

				// if the current char of 'X' matches with the current char of 'S'
				else if (i != 0 && X.charAt(i - 1) == S.charAt(i + j - 1)) {
					T[i][j] = T[i - 1][j];
				}

				// if the current char of 'Y' matches with the current char of 'S'
				else if (j != 0 && Y.charAt(j - 1) == S.charAt(i + j - 1)) {
					T[i][j] = T[i][j - 1];
				}
			}
		}

		// T[M][N] stores the result
		return T[M][N];
	}

	public static void main(String[] args) {
		String X = "ABC";
		String Y = "ACD";
		// String S = "ACDABC";
		String S = "ABCACD";

		if (isInterleaving(X, Y, S)) {
			System.out.println("Interleaving");
		} else {
			System.out.println("Not an Interleaving");
		}
	}

}
