package com.algoexpert.dp;

public class DeleteOperationsOnStrings {


	public static int minDistance(String word1, String word2) {
		return helper(word1, word2, word1.length()-1, word2.length()-1);
	}


	public static int helper(String s1, String s2, int m, int n) {
		if(m == 0 || n == 0) {
			return m+n;
		}

		if(s1.charAt(m) == s2.charAt(n)) {
			return helper(s1, s2, m-1, n-1);
		}

		int deleteIns1 = helper(s1, s2, m-1, n);
		int deleteIns2 = helper(s1, s2, m, n-1);

		return Math.min(deleteIns1, deleteIns2) + 1;

	}

	public static void main(String[] args) {
		String word1 = "sea", word2 = "eat";
		minDistance(word1, word2);
	}


	/**
	 * sea  eat
	 *
	 *se eat
	 *
	 *s eat se ea
	 *
	 *
	 *	 s ea  se e
	 *
	 *
	 *
	 *
	 *
	 */

}
