package com.algoexpert.dp;

public class OnesAndZeros {

	public static int findMaxForm(String[] strs, int m, int n) {
		return calcFindMaxForm(strs, m, n, 0);
	}

	private static int calcFindMaxForm(String[] strs, int m, int n, int i) {
		if (i > strs.length - 1) {
			return 0;
		}

		if (m < 0 || n < 0) {
			return 0;
		}

		if (m == 0 && n == 0) {
			return 0;
		}

		int zerosCount = calcCount(strs[i], '0');
		int onesCount = strs[i].length() - zerosCount;

		if (zerosCount <= m && onesCount <= n) {
			return Math.max(1 + calcFindMaxForm(strs, m - zerosCount, n - onesCount, i + 1),
					calcFindMaxForm(strs, m, n, i + 1));
		} else {
			return calcFindMaxForm(strs, m, n, i + 1);
		}
	}

	private static int calcCount(String str, char ch) {
		int count = 0;
		for (char tempCh : str.toCharArray()) {
			if (tempCh == ch) {
				count++;
			}
		}
		return count;
	}

	//-----------------------

	public static int findMaxForm2(String[] S, int M, int N) {
		int[][] dp = new int[M+1][N+1];
		for (String str : S) {
			int zeros = 0, ones = 0;
			for (char c : str.toCharArray()) {
				if (c == '0') {
					zeros++;
				} else {
					ones++;
				}
			}
			for (int i = M; i >= zeros; i--) {
				for (int j = N; j >= ones; j--) {
					dp[i][j] = Math.max(dp[i][j], dp[i-zeros][j-ones] + 1);
				}
			}
		}
		return dp[M][N];
	}

	public static void main(String[] args) {
		String[] strs = { "10", "0001", "111001", "1", "0" };
		int m = 5, n = 3;
		findMaxForm(strs, m, n);
	}

}
