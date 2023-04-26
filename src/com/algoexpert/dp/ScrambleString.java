package com.algoexpert.dp;

import java.util.HashMap;
import java.util.Map;

public class ScrambleString {

	static Map<String, Boolean> memo;// map for caching

	public boolean f(String s1, String s2) {
		if (s1.equals(s2)) {
			return true;
		}

		String key = s1 + s2;// using this as memoization key

		// already cached return the value

		if (memo.containsKey(key)) {
			return memo.get(key);
		}

		// if the frequencies of characters in s1 and s2 are not same,
		// we can simply return false,this will improve our algorithm TC

		//		int[] f1 = new int[26];
		//		int[] f2 = new int[26];
		//
		//		for (int i = 0; i < s1.length(); i++) {
		//			f1[s1.charAt(i) - 'a']++;
		//			f2[s2.charAt(i) - 'a']++;
		//		}
		//		if (!Arrays.equals(f1, f2)) {
		//			memo.put(key, false);
		//			return false;
		//		}

		int n = s1.length();

		for (int i = 1; i < n; i++) {
			// consider s1 = x + y,s2 = x' + y'
			// then we need to check either xx' + yy' is a scramble string or
			// flipping one of the strings i.e xp' + yq' is a scramble string
			if (f(s1.substring(0, i), s2.substring(0, i)) && f(s1.substring(i), s2.substring(i))
					|| f(s1.substring(0, i), s2.substring(n - i)) && f(s1.substring(i), s2.substring(0, n - i))) {
				// if any of them is true make sure to memoize the result and return the same
				memo.put(key, true);
				return true;
			}

		}

		memo.put(key, false);
		// else return false
		return false;

	}

	public boolean isScramble(String s1, String s2) {
		memo = new HashMap<>();
		return f(s1, s2);
	}

}
