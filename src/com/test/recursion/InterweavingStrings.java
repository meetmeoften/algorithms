package com.test.recursion;

public class InterweavingStrings {
	public static boolean interweavingStrings(String one, String two, String three) {
		// Write your code here.
		if(three.length() != one.length() + two.length()) {
			return false;
		}

		Boolean[][] cache = new Boolean[one.length() +1][two.length()+1];
		var result = areInterwoven(one, two, three, 0, 0, cache);
		return result;
	}

	public static boolean areInterwoven(String one, String two, String three,
			int i, int j, Boolean[][] cache) {
		if(cache[i][j] != null) {
			return cache[i][j];
		}
		int k = i + j;
		if(k == three.length()) {
			return true;
		}

		if(i < one.length() && one.charAt(i) == three.charAt(k)) {
			cache[i][j] = areInterwoven(one, two, three, i+1, j, cache);
			if(cache[i][j]) {
				return true;
			}
		}


		if(j< two.length() && two.charAt(j) == three.charAt(k)) {
			var result =  areInterwoven(one, two , three, i, j+1, cache);
			cache[i][j] = result;
			return result;
		}

		cache[i][j] = false;
		return false;

	}

	public static void main(String[] args) {
		//		String one = "algoexpert";
		//		String two = "your-dream-job";
		//		String three = "your-algodream-expertjob";
		String one = "aaa";
		String two = "aaaf";
		String three = "aaafaaa";
		interweavingStrings(one, two, three);
	}
}
