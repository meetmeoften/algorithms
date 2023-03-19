package com.algoexpert.binarysearch;

public class MaximumRemovableCharacters {

	public static int maximumRemovals(String s, String p, int[] removable) {
		char[] sChar = s.toCharArray();
		char[] pChar = p.toCharArray();
		int l = 0;
		int r = removable.length - 1;

		while (l <= r) {
			int mid = l + (r - l) / 2;

			// Let's check till the mid point of removable array and find whether after
			// removing all those indices from s, p remains a subsequence of s or not? I
			// used '* to indicate that I removed those indices.'
			for (int i = l; i <= mid; i++) {
				sChar[removable[i]] = '*';
			}

			// If it is a subsequence then this might be possible that the value of K is
			// more but we are only checking till mid so we need to move right, hence l =
			// mid+1;
			if (isSubsequence(sChar, pChar)) {
				l = mid + 1;
			}

			// If we remove the indices till mid point but our K is less i.e, we can't
			// remove mid+1(we start our iteration considering 0 indexed) numbers of
			// elements then it might be possible that our K is less than mid+1. so we need
			// to move left. Since we have already checked till mid now we need to set our r
			// = mid-1.
			else {
				for (int i = l; i <= mid; i++) {
					sChar[removable[i]] = s.charAt(removable[i]);
				}
				r = mid - 1;
			}
		}

		// Since we have programmed based on 0 indexed our r might go to -1 or our r
		// value will stay at the end index of removable array so to find out toal
		// number of K we need to do r+1;
		return r + 1;

	}

	public static boolean isSubsequence(char[] s, char[] p) {
		int i = 0, j = 0;
		while (i < s.length && j < p.length) {
			if (s[i] == p[j]) {
				i++;
				j++;
			} else {
				i++;
			}
		}

		// If our j pointer reaches the end of P string or P char[] then P is a
		// subsequence of S so return TRUE otherwise return FALSE.
		return j == p.length;
	}

	public static void main(String[] args) {
		String s = "abcacb", p = "ab";
		int[] removable = { 3, 1, 0 };

		maximumRemovals(s, p, removable);
	}

}
