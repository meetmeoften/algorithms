package com.leetcodepatterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MaximumOccurencesOfSubstring {

	public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {

		int left = 0;
		int right = 0;

		Map<String, Integer> map = new HashMap<>();
		Map<Character, Integer> cMap = new HashMap<>();
		int result = 0;


		//"aababcaab"
		while (right < s.length()) {
			cMap.put(s.charAt(right), cMap.getOrDefault(s.charAt(right), 0) + 1);
			right++;
			if (right - left > minSize) {
				cMap.put(s.charAt(left), cMap.getOrDefault(s.charAt(left), 0) - 1);
				if (cMap.get(s.charAt(left)) == 0) {
					cMap.remove(s.charAt(left));
				}
				left++;
			}

			if (right - left >= minSize && cMap.size() == maxLetters) {
				String key = s.substring(left, right);
				map.put(key, map.getOrDefault(key, 0) + 1);
				result = Math.max(result, map.get(key));
			}
		}

		return result;
	}



}
