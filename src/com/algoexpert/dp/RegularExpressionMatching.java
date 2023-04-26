package com.algoexpert.dp;

public class RegularExpressionMatching {

	public boolean isMatch(String s, String p) {
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;

		for (int j = 2; j <= p.length(); j++) {
			dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
		}

		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= p.length(); j++) {
				if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
					dp[i][j] = dp[i - 1][j - 1];
				} else if (p.charAt(j - 1) == '*') {
					dp[i][j] = dp[i][j - 2]
							|| (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j];
				}
			}
		}

		return dp[s.length()][p.length()];
	}

	public boolean isMatch2(String s, String p) {
		boolean[][] cache = new boolean[s.length() + 1][p.length() + 1];

		return dfs(cache, s, p, 0, 0);
	}

	private boolean dfs(boolean[][] cache, String s, String p, int i, int j) {
		if (cache[i][j] != false) {
			return cache[i][j];
		}

		if (i >= s.length() && j >= p.length()) {
			return true;
		}

		if (j >= p.length()) {
			return false;
		}

		boolean match = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');

		if (p.charAt(j) == '*') {
			cache[i][j] = dfs(cache, s, p, i, j + 1) || (dfs(cache, s, p, i + 1, j));
		} else {
			cache[i][j] = match && dfs(cache, s, p, i + 1, j + 1);
		}

		return cache[i][j];
	}

	public static void main(String[] args) {
		RegularExpressionMatching match = new RegularExpressionMatching();
		String s = "aba", p = "*";
		//match.isMatch(s, p);
		match.isMatch2(s, p);
	}

}
