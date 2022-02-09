package com.algoexpert.veryhard;

import java.util.Arrays;

public class VeryHardProblems {

	public static boolean knuthMorrisPrattAlgorithm(String string, String substring) {
		int[] pattern = buildPattern(substring);
		return doesMatch(string, substring, pattern);
	}

	private static boolean doesMatch(String string, String substring, int[] pattern) {
		// TODO Auto-generated method stub
		return false;
	}

	private static int[] buildPattern(String substring) {
		int[] pattern = new int[substring.length()];
		Arrays.fill(pattern, -1);
		int i= 1;
		int j= 0;

		while(i < substring.length()) {
			if(substring.charAt(i) == substring.charAt(j)) {
				pattern[i] = j;
				i++;
				j--;
			} else if(j > 0) {
				j = pattern[j-1]+1;
			} else {
				i++;
			}
		}
		return pattern;
	}
}
