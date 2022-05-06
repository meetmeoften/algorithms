package com.algoexpert.intuit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestSubstring {

	public int lengthOfLongestSubstring(String s) {
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


	public int lengthOfLongestSubstring2(String str) {
		if (str == null ||str.length() == 0) {
			return 0;
		}

		Map<Character, Integer> lastSeen = new HashMap<Character, Integer>();
		int[] longest = {0, 1};
		int startIndex = 0;

		for(int i= 0; i< str.length(); i++) {
			char c = str.charAt(i);
			if(lastSeen.containsKey(c)) {
				startIndex = Math.max(startIndex, lastSeen.get(c) +1);
			}
			if(longest[1] - longest[0] < i+1 - startIndex) {
				longest = new int[]{startIndex, i+1};
			}
			lastSeen.put(c, i);
		}

		String result = str.substring(longest[0], longest[1]);
		return result.length();

	}

}
