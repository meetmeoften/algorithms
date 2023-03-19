package com.algoexpert.dp;

public class DecodeWays {

	public static int count(char[] seq, int n) {
		if (n == 0 || n == 1) {
			return 1;
		}

		int sum = 0;

		if (seq[n - 1] >= '1' && seq[n - 1] <= '9') {
			sum += count(seq, n - 1);
		}

		if (seq[n - 2] == '1' || (seq[n - 2] == '2' && seq[n - 1] <= '6')) {
			sum += count(seq, n - 2);
		}

		return sum;
	}

	public static int numDecodings(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int[] dp = new int[s.length() + 1];

		if (s.charAt(0) != '0') {
			dp[0] = 1;
		}
		if (dp[0] == 1) {
			dp[1] = 1;
		}

		for (int i = 2; i <= s.length(); i++) {
			if (s.charAt(i - 1) != '0') {
				dp[i] += dp[i - 1];
			}
			int twoDigits = Integer.valueOf(s.substring(i - 2, i));

			if (twoDigits >= 10 && twoDigits <= 26) {
				dp[i] += dp[i - 2];
			}
		}

		return dp[s.length()];

	}

	public static void main(String[] args) {
		int x = 123;

		char[] chars = String.valueOf(x).toCharArray();
		System.out.println("The total number of decodings are " + count(chars, chars.length));
		System.out.println("The total number of decodings are " + numDecodings(String.valueOf(x)));
	}

}
