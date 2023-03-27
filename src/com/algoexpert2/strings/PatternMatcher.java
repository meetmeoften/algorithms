package com.algoexpert2.strings;

import java.util.HashMap;
import java.util.Map;

public class PatternMatcher {

	public static String[] patternMatcher(String pattern, String str) {
		// Write your code here.
		if (pattern.length() > str.length()) {
			return new String[] {};
		}
		char[] newPattern = getNewPattern(pattern);
		boolean didSwitch = newPattern[0] != pattern.charAt(0);

		Map<Character, Integer> counts = new HashMap<>();
		counts.put('x', 0);
		counts.put('y', 0);

		int firstYPos = getCountAndFirstYPos(newPattern, counts);
		if (counts.get('y') != 0) {
			for (int lenOfX = 1; lenOfX < str.length(); lenOfX++) {
				double lenOfY = (str.length() - lenOfX * (double) counts.get('x')) / (double) counts.get('y');

				if (lenOfY <= 0 || lenOfY % 1 != 0) {
					continue;
				}
				// System.out.println(lenOfY + " " + lenOfX);
				int yIdx = firstYPos * lenOfX;
				String x = str.substring(0, lenOfX);
				String y = str.substring(yIdx, yIdx + (int) lenOfY);
				// System.out.println(x + " " + y);
				String potentialMatch = buildPotentialMatch(newPattern, x, y);
				if (str.equals(potentialMatch)) {
					return didSwitch ? new String[] { y, x } : new String[] { x, y };
				}
			}
		} else {
			double lenOfX = str.length() / counts.get('x');
			if (lenOfX % 1 == 0) {
				String x = str.substring(0, (int) lenOfX);
				String potentialMatch = buildPotentialMatch(newPattern, x, "");
				if (str.equals(potentialMatch)) {
					return didSwitch ? new String[] { "", x } : new String[] { x, "" };
				}
			}
		}
		return new String[] {};
	}

	public static char[] getNewPattern(String pattern) {
		char[] patternLetters = pattern.toCharArray();
		if (pattern.charAt(0) == 'x') {
			return patternLetters;
		}

		for (int i = 0; i < patternLetters.length; i++) {
			if (patternLetters[i] == 'x') {
				patternLetters[i] = 'y';
			} else {
				patternLetters[i] = 'x';
			}
		}
		return patternLetters;
	}

	public static int getCountAndFirstYPos(char[] pattern, Map<Character, Integer> counts) {
		int firstYPos = -1;
		for (int i = 0; i < pattern.length; i++) {
			char c = pattern[i];
			counts.put(c, counts.get(c) + 1);
			if (c == 'y' && firstYPos == -1) {
				firstYPos = i;
			}
		}
		return firstYPos;
	}

	public static String buildPotentialMatch(char[] pattern, String x, String y) {
		StringBuilder potentialMatch = new StringBuilder();
		for (char c : pattern) {
			if (c == 'x') {
				potentialMatch.append(x);
			} else {
				potentialMatch.append(y);
			}
		}
		return potentialMatch.toString();
	}

	// ------------------------

	public static boolean isMatch(String word, int i, String pattern, int j, Map<Character, String> map) {
		// invalid word
		if (word == null || pattern == null || word.length() < pattern.length()) {
			return false;
		}

		int n = word.length(), m = pattern.length();

		// base condition
		if (n < m) {
			return false;
		}

		// if both pattern and the string reaches the end
		if (i == n && j == m) {
			return true;
		}

		// if either string or pattern reaches the end
		if (i == n || j == m) {
			return false;
		}

		// consider the next character from the pattern
		char curr = pattern.charAt(j);

		// if the character is seen before
		if (map.containsKey(curr)) {
			String s = map.get(curr);
			int k = s.length();

			// `ss` stores next `k` characters of the given string
			String ss;
			if (i + k < word.length()) {
				ss = word.substring(i, i + k);
			} else {
				ss = word.substring(i);
			}

			// return false if the next `k` characters don't match with `s`
			if (ss.compareTo(s) != 0) {
				return false;
			}

			// recur for remaining characters if the next `k` characters match
			return isMatch(word, i + k, pattern, j + 1, map);
		}

		// process all remaining characters in the string if the current
		// character is never seen before
		for (int k = 1; k <= n - i; k++) {
			// insert substring formed by next `k` characters of the string
			// into the map
			map.put(curr, word.substring(i, i + k));

			// check if it leads to the solution
			if (isMatch(word, i + k, pattern, j + 1, map)) {
				return true;
			}

			// otherwise, backtrack – remove the current character from the map
			map.remove(curr);
		}

		return false;
	}

	public static void main(String[] args) {
		String[] expected = { "go", "powerranger" };
		String inputPattern = "xxyxxy";
		String inputString = "gogopowerrangergogopowerranger";
		// patternMatcher(inputPattern, inputString);

		String word = "codesleepcode";
		String pattern = "XYX";

		Map<Character, String> mapping = new HashMap<>();
		isMatch(word, 0, pattern, 0, mapping);

	}

	public boolean compare(String[] arr1, String[] arr2) {
		if (arr1.length != arr2.length) {
			return false;
		}
		if (arr1.length == 0 && arr2.length == 0) {
			return true;
		}
		return arr1[0].equals(arr2[0]) && arr1[1].equals(arr2[1]);
	}

}
