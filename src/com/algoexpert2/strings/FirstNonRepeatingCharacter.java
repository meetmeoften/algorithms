package com.algoexpert2.strings;

import java.util.HashMap;

public class FirstNonRepeatingCharacter {

	public int firstNonRepeatingCharacter(String string) {
		// Write your code here.

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0; i < string.length(); i++) {
			Character c = string.charAt(i);
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		for (int i = 0; i < string.length(); i++) {
			Character c = string.charAt(i);
			if (map.get(c) == 1) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		String input = "abcdcaf";
		int expected = 1;
		var actual = new FirstNonRepeatingCharacter().firstNonRepeatingCharacter(input);
		// Utils.assertTrue(expected == actual);
	}

}
