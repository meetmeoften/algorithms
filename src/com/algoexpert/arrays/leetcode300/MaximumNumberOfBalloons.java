package com.algoexpert.arrays.leetcode300;

import java.util.HashMap;

public class MaximumNumberOfBalloons {

	public static int maxNumberOfBalloons(String text) {
		HashMap<Character, Integer> balloon = new HashMap<>();
		HashMap<Character, Integer> countText = new HashMap<>();

		char[] balloonArray = "balloon".toCharArray();

		for (char c : balloonArray) {
			if (balloon.containsKey(c)) {
				balloon.put(c, balloon.get(c) + 1);
			} else {
				balloon.put(c, 1);
			}
		}

		char[] countTextArray = text.toCharArray();

		for (char c : countTextArray) {
			if (countText.containsKey(c)) {
				countText.put(c, countText.get(c) + 1);
			} else {
				countText.put(c, 1);
			}
		}

		int res = text.length();
		for(char c : balloonArray) {
			res = Math.min(res,  countText.getOrDefault(c, 0)/balloon.get(c));
		}

		return res;
	}

	public static void main(String[] args) {
		maxNumberOfBalloons("nlaebolko");
	}

}
