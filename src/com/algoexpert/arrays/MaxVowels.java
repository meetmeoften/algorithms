package com.algoexpert.arrays;

public class MaxVowels {

	public static int maxVowels(String s, int k) {

		String vowels = "aeiou";
		int left = 0;
		int count = 0;
		int max = 0;

		for(int right = 0; right < s.length(); right++) {
			if(vowels.indexOf(s.charAt(right)) != -1) {
				count++;
			}

			if(right - left + 1 >= k) {
				max = Math.max(count, max);
				if(vowels.indexOf(s.charAt(left)) != -1) {
					count--;
				}
				left++;
			}
		}

		return max;
	}

}
