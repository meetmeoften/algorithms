package com.algoexpert2.strings;

import java.util.HashMap;

public class GenerateDocument {

	public boolean generateDocument(String characters, String document) {
		// Write your code here.
		HashMap<Character, Integer> characterCounts = new HashMap<>();

		for (int i = 0; i < characters.length(); i++) {
			char character = characters.charAt(i);
			characterCounts.put(character, characterCounts.getOrDefault(character, 0) + 1);
		}

		for (int i = 0; i < document.length(); i++) {
			char character = document.charAt(i);
			if (!characterCounts.containsKey(character)) {
				return false;
			}
			if (characterCounts.get(character) == 0) {
				return false;
			}
			characterCounts.put(character, characterCounts.get(character) - 1);
		}
		return true;
	}

	public static void main(String[] args) {
		String characters = "Bste!hetsi ogEAxpelrt x ";
		String document = "AlgoExpert is the Best!";
		boolean expected = true;
		var actual = new GenerateDocument().generateDocument(characters, document);
		// Utils.assertTrue(expected == actual);
	}

}
