package com.algoexpert.arrays.leetcode300;

import java.util.HashMap;

public class WordPattern {

	public static boolean wordPattern(String pattern, String s) {
		String[] sArray = s.split("\s");
		if (sArray.length != pattern.length()) {
			return false;
		}

		HashMap<Character, String> charToWord = new HashMap<>();
		HashMap<String, Character> wordToChar = new HashMap<>();



		for (int i = 0; i < pattern.length(); i++) {

			if (charToWord.containsKey(pattern.charAt(i)) && !charToWord.get(pattern.charAt(i)).equals(sArray[i])) {
				return false;
			}

			if (wordToChar.containsKey(sArray[i]) && !wordToChar.get(sArray[i]).equals(pattern.charAt(i))) {
				return false;
			}

			charToWord.put(pattern.charAt(i), sArray[i]);
			wordToChar.put(sArray[i], pattern.charAt(i));
		}
		return true;
	}


	public static void main(String[] args) {
		String pattern = "abba";
		String s = "dog cat cat dog";

		wordPattern(pattern, s);
	}

}
