package com.algoexpert2.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LongestCommonSubsequence {

	public static List<Character> longestCommonSubsequence(String str1, String str2) {
		int[][] lengths = new int[str1.length() + 1][str2.length() + 1];

		for (int i = 1; i < str1.length() + 1; i++) {
			for (int j = 1; j < str2.length() + 1; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					lengths[i][j] = lengths[i - 1][j - 1] + 1;
				} else {
					lengths[i][j] = Math.max(lengths[i - 1][j], lengths[i][j - 1]);
				}
			}
		}

		return buildSequence(lengths, str1);
	}

	public static List<Character> buildSequence(int[][] lengths, String str) {
		List<Character> sequence = new ArrayList<>();
		int i = lengths.length - 1;
		int j = lengths[0].length - 1;
		while (i != 0 && j != 0) {
			if (lengths[i][j] == lengths[i - 1][j]) {
				i--;
			} else if (lengths[i][j] == lengths[i][j - 1]) {
				j--;
			} else {
				sequence.add(0, str.charAt(i - 1));
				i--;
				j--;
			}
		}

		return sequence;

	}

	// CHECK IN TECHIE DELIGHT

	public static int LCSLength2(String X, String Y, int m, int n) {
		if (m == 0 || n == 0) {
			return 0;
		}
		if (X.charAt(m - 1) == Y.charAt(n - 1)) {
			return 1 + LCSLength2(X, Y, m - 1, n - 1);
		}

		return Integer.max(LCSLength2(X, Y, m - 1, n), LCSLength2(X, Y, m, n - 1));
	}

	public static int LCSLength(String X, String Y, int m, int n, Map<String, Integer> lookup) {
		if (m == 0 || n == 0) {
			return 0;
		}
		String key = m + "|" + n;
		if (!lookup.containsKey(key)) {
			if (X.charAt(m - 1) == Y.charAt(n - 1)) {
				lookup.put(key, 1 + LCSLength(X, Y, m - 1, n - 1, lookup));
			} else {
				lookup.put(key, Integer.max(LCSLength(X, Y, m - 1, n, lookup), LCSLength(X, Y, m, n - 1, lookup)));
			}
		}
		return lookup.get(key);
	}

	public static void main(String[] args) {
		longestCommonSubsequence("ZXVVYZW", "XKYKZPW");
	}

}
