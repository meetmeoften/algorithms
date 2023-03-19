package com.algoexpert.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestSubstringWithoutRepeating {

	public static int lengthOfLongestSubstring2(String str) {
		if (str == null ||str.length() == 0) {
			return 0;
		}

		int[] longest = {0, 1};
		int startIndex = 0;

		Map<Character, Integer> map = new HashMap<>();
		for(int i= 0; i < str.length(); i++) {
			Character c = str.charAt(i);
			if(map.containsKey(c)) {
				startIndex = Math.max(startIndex, map.get(c)+1);
			}
			if(i+1 - startIndex > longest[1] - longest[0]) {
				longest = new int[] { startIndex, i+1};
			}
			map.put(c, i);
		}


		String result = str.substring(longest[0], longest[1]);
		return result.length();
	}


	public static int lengthOfLongestSubstring(String s) {
		if (s.length() == 0) {
			return s.length();
		}

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
		lengthOfLongestSubstring("abcabcdbb");
	}

}
