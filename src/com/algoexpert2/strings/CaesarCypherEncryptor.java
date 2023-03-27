package com.algoexpert2.strings;

public class CaesarCypherEncryptor {

	public static String caesarCypherEncryptor(String str, int key) {
		// Write your code here.
		char[] newLetters = new char[str.length()];
		int newKey = key % 26;
		for (int i = 0; i < str.length(); i++) {
			newLetters[i] = getNewLetter(str.charAt(i), newKey);
		}

		return new String(newLetters);
	}

	public static char getNewLetter(char letter, int key) {
		int newLetterCode = letter + key;
		return newLetterCode <= 122 ? (char) newLetterCode : (char) (96 + newLetterCode % 122);
	}

	public static void main(String[] args) {
		caesarCypherEncryptor("xyz", 2);
		//.equals("zab"));
	}

}
