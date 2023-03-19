package com.algoexpert.slidingwindow;

public class MinimumNoOfFlipsToMakeBinaryAlternating {

	public static int minFlips(String s) {
		/*
		 * Sliding Window Approach
		 */

		int n = s.length();

		int mininumFlip = Integer.MAX_VALUE;

		int misMatchCount = 0;
		for (int i = 0; i < (2 * n); i++) {

			int r = i % n;

			// add mis watch count in current window
			if ((s.charAt(r) - '0') != (i % 2 == 0 ? 1 : 0)) {
				misMatchCount++;
			}

			// remove mismatch count which are not relvent for current window
			if (i >= n && (s.charAt(r) - '0') != (r % 2 == 0 ? 1 : 0)) {
				misMatchCount--;
			}

			// misMatchCount : when valid binary string start from 1
			// n - misMatchCount : when valid binary string start from 0
			if (i >= n - 1) {
				mininumFlip = Math.min(mininumFlip, Math.min(misMatchCount, n - misMatchCount));
			}
		}

		return mininumFlip;
	}

	public static int minFlips2(String s) {
		StringBuilder sb = new StringBuilder(s).append(s);

		StringBuilder alt1 = new StringBuilder();
		StringBuilder alt2 = new StringBuilder();

		for (int i = 0; i < sb.length(); i++) {
			if (i % 2 == 0) {
				alt1.append(0);
				alt2.append(1);
			}
			else {
				alt1.append(1);
				alt2.append(0);
			}
		}

		int diff1 = 0, diff2 = 0;
		int l = 0;

		int res = sb.length();

		for (int r = 0; r < sb.length(); r++) {
			if (alt1.charAt(r) != sb.charAt(r)) {
				diff1++;
			}
			if (alt2.charAt(r) != sb.charAt(r)) {
				diff2++;
			}

			if (r - l + 1 > s.length()) {
				if (alt1.charAt(l) != sb.charAt(l)) {
					diff1--;
				}
				if (alt2.charAt(l) != sb.charAt(l)) {
					diff2--;
				}
				l++;
			}

			if (r - l + 1 == s.length()) {
				res = Math.min(res, Math.min(diff1, diff2));
			}
		}

		return res;
	}

	public static void main(String[] args) {
		minFlips2("111000");
	}

}
