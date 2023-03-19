package com.algoexpert.recursion;

public class InterweavingStrings {

	public static boolean interweavingStrings(String one, String two, String three) {
		// Write your code here.
		if (three.length() != one.length() + two.length()) {
			return false;
		}

		Boolean[][] cache = new Boolean[one.length() + 1][two.length() + 1];
		areInterwoven(one, two, three, 0, 0);
		boolean result =  areInterwoven(one, two, three, 0, 0, cache);
		return result;
	}


	public static boolean areInterwoven(String one, String two, String three, int i, int j) {
		int k = i + j;
		System.out.println(i + " " + j);
		if(k == three.length()) {
			return true;
		}

		if(i < one.length() && one.charAt(i) == three.charAt(k)) {
			if(areInterwoven(one, two, three, i+1, j)) {
				return true;
			}
		}
		System.out.println("-");

		if(j< two.length() && two.charAt(j) == three.charAt(k)) {
			return areInterwoven(one, two , three, i, j+1);
		}

		return false;

	}

	public static boolean areInterwoven(String one, String two, String three, int i, int j, Boolean[][] cache) {

		if (cache[i][j] != null) {
			return cache[i][j];
		}

		int k = i + j;
		if (k == three.length()) {
			return true;
		}

		if (i < one.length() && one.charAt(i) == three.charAt(k)) {
			cache[i][j] = areInterwoven(one, two, three, i + 1, j, cache);
			if (cache[i][j]) {
				return true;
			}
		}

		if (j < two.length() && two.charAt(j) == three.charAt(k)) {
			var result = areInterwoven(one, two, three, i, j + 1, cache);
			cache[i][j] = result;
			return result;
		}

		cache[i][j] = false;
		return false;
	}


	public static boolean isInterleave(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}

		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		dp[s1.length()][s2.length()] = true;

		for (int i = dp.length - 1; i >= 0; i--) {
			for (int j = dp[0].length - 1; j >= 0; j--) {
				if (i < s1.length() && s1.charAt(i) == s3.charAt(i + j) && dp[i + 1][j]) {
					dp[i][j] = true;
				}
				if (j < s2.length() && s2.charAt(j) == s3.charAt(i + j) && dp[i][j + 1]) {
					dp[i][j] = true;
				}
			}
		}

		return dp[0][0];
	}

	public static void main(String[] args) {
		//		String one = "algoexpert";
		//		String two = "your-dream-job";
		//		String three = "your-algodream-expertjob";

		String one = "aaa";
		String two = "aaaf";
		String three = "aaafaaa";

		interweavingStrings(one, two, three);
		isInterleave(one, two, three);

	}

}
