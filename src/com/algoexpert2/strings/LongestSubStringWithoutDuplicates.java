package com.algoexpert2.strings;

import java.util.HashMap;
import java.util.HashSet;
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

	public static int lengthOfLongestSubstring(String s) {
		if (s.length() == 0) return s.length();

		int left = 0, right = 0;
		int longest = 0;
		int n = s.length();
		HashSet<Character> window = new HashSet<>();
		while (right < n) {
			if (!window.contains(s.charAt(right))) {
				window.add(s.charAt(right));
				right++;
			} else {
				window.remove(s.charAt(left));
				left++;
			}
			longest = Math.max(longest, right - left);
		}

		return longest;
	}

	public static void main(String[] args) {
		longestSubstringWithoutDuplication("clementisacap").equals("mentisac");
		lengthOfLongestSubstring("clementisacap");
	}

}
