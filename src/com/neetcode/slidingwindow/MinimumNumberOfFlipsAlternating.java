package com.neetcode.slidingwindow;

public class MinimumNumberOfFlipsAlternating {

	public static int minFlips(String s) {
		int n = s.length();
		String target = "01";
		int cnt = 0;
		for (int i = 0; i < n; ++i) {
			cnt += (s.charAt(i) == target.charAt(i & 1) ? 0 : 1);
		}
		int res = Math.min(cnt, n - cnt);
		for (int i = 0; i < n; ++i) {
			cnt -= (s.charAt(i) == target.charAt(i & 1) ? 0 : 1);
			cnt += (s.charAt(i) == target.charAt((i + n) & 1) ? 0 : 1);
			res = Math.min(res, Math.min(cnt, n - cnt));
		}
		return res;
	}

	public static int minFlips2(String s) {
		int flipsStartingWith0 = 0; // "010101..."
		int flipsStartingWith1 = 0; // "101010..."

		for (int i = 0; i < s.length(); i++) {
			char expectedCharFor0 = (i % 2 == 0) ? '0' : '1';
			char expectedCharFor1 = (i % 2 == 0) ? '1' : '0';

			if (s.charAt(i) != expectedCharFor0) flipsStartingWith0++;
			if (s.charAt(i) != expectedCharFor1) flipsStartingWith1++;
		}

		return Math.min(flipsStartingWith0, flipsStartingWith1);
	}

	public static void main(String[] args) {
		String s = "111000";
		minFlips2(s);
	}

}
