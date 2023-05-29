package com.algoexpert.arrays;

public class MonotoneIncreasing {

	public static int minimumDeletions(String s) {
		// One pass with O(N)
		int bcount = 0;
		int deletions = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'b') {
				bcount++;
			} else {
				deletions++;
				deletions = Math.min(deletions, bcount);
			}
		}
		return deletions;
	}

	public static void main(String[] args) {
		String s = "aababbab";
		minimumDeletions(s);
	}

}
