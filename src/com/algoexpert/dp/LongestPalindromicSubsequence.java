package com.algoexpert.dp;

import java.util.Arrays;

public class LongestPalindromicSubsequence {

	public int longestPalindromeSubseq(String s) {
		int size = s.length();
		if (size == 0 || s == null) {
			return 0;
		}
		int[][] dp = new int[size][size];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		return helper(s, 0, s.length() - 1, dp);
	}

	private int helper(String s, int left, int right, int[][] cache) {
		System.out.println("LEFT " + left + "  RIGHT " + right);
		if (left == right) {
			return 1;
		}

		if (left > right) {
			return 0;
		}

		if (cache[left][right] != -1) {
			return cache[left][right];
		}

		if (s.charAt(left) == s.charAt(right)) {
			return 2 + helper(s, left + 1, right - 1, cache);
		}

		int leftV = helper(s, left + 1, right, cache);
		int rightV = helper(s, left, right - 1, cache);

		cache[left][right] = Math.max(leftV, rightV);
		return cache[left][right];

	}

}
