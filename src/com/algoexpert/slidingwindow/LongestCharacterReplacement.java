package com.algoexpert.slidingwindow;

public class LongestCharacterReplacement {

	public static int characterReplacement(String s, int k) {
		int[] arr = new int[26];
		int maxCharCount = 0, result = 0;
		int left = 0;

		for (int right = 0; right < s.length(); right++) {
			arr[s.charAt(right) - 'A']++;
			maxCharCount = Math.max(maxCharCount, arr[s.charAt(right) - 'A']);

			if (right - left + 1 - maxCharCount > k) {
				arr[s.charAt(left) - 'A']--;
				left++;
			}

			result = Math.max(result, right - left + 1);
		}

		return result;
	}

	public static void main(String[] args) {
		characterReplacement("ABAB", 2);
	}

}
