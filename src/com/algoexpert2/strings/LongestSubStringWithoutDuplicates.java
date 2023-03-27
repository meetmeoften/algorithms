package com.algoexpert2.strings;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStringWithoutDuplicates {

	//Longest Substrings without duplicates
	public static String longestSubstringWithoutDuplication(String str) {
		Map<Character, Integer> map = new HashMap<>();
		int[] longest = {0, 1};
		int start = 0;

		for(int i= 0; i < str.length(); i++) {
			Character c = str.charAt(i);
			if(map.containsKey(c)) {
				start = Math.max(start, map.get(c) +1);
			}
			if(longest[1] - longest[0] < i+1 - start) {
				longest = new int[]{start, i+1};
				System.out.println(str.substring(longest[0], longest[1]));
			}

			map.put(c, i);
		}
		return str.substring(longest[0], longest[1]);
	}

	public static void main(String[] args) {
		longestSubstringWithoutDuplication("clementisacap").equals("mentisac");
	}

}
